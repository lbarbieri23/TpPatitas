package com.dds.mascotas.service;

import com.dds.mascotas.dao.*;
import com.dds.mascotas.model.*;
import com.dds.mascotas.model.security.Usuario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PublicacionService {


    private PublicacionDao publicacionDao;
    private PersonaDao personaDao;
    private MascotaDao mascotaDao;
    private CaracteristicaDao caracteristicaDao;
    private OpcionCaracteristicaDao opcionCaracteristicaDao;
    private CommonDao commonDao; //FIXME COMMONDAO!!!!
    private RecomendacionesService recomendacionesService;

    @Autowired
    public PublicacionService(PublicacionDao publicacionDao, PersonaDao personaDao, MascotaDao mascotaDao, CaracteristicaDao caracteristicaDao, OpcionCaracteristicaDao opcionCaracteristicaDao, CommonDao commonDao, RecomendacionesService recomendacionesService) {
        this.publicacionDao = publicacionDao;
        this.personaDao = personaDao;
        this.mascotaDao = mascotaDao;
        this.caracteristicaDao = caracteristicaDao;
        this.opcionCaracteristicaDao = opcionCaracteristicaDao;
        this.commonDao = commonDao;
        this.recomendacionesService =  recomendacionesService;
    }

    //FIXME VER SI ESTA BIEN EL TIPO DE PUB EN LA BASE
    //FIXME VER SI USAR UN CRITERIA BUILDER
    //FIXME DELEGAR BUSQUEDA A LA BASE
    @Transactional
    public List<Publicacion> findBy(TipoPublicacion tipo, String searchCriteria, Integer page, Integer itemsPerPage, Persona persona) {
        List<Publicacion> results = publicacionDao.findBy(tipo, searchCriteria, page, itemsPerPage);
        List<Publicacion> finalResults = null;
        if (searchCriteria != null && !"".equals(searchCriteria)) {
            String[] tokens = searchCriteria.toLowerCase(Locale.ROOT).split(" ");

            for (String token : tokens) {
                finalResults = new ArrayList<>();
                List<Publicacion> first = results
                        .stream()
                        .filter(x -> !(x instanceof PublicacionQuieroAdoptar))
                        .filter(x ->
                                x.getMascota().getSexo().getDescripcion().toLowerCase(Locale.ROOT).contains(token) ||
                                        x.getMascota().getTipoMascota().getDescripcion().toLowerCase(Locale.ROOT).contains(token) ||
                                        x.getDescripcion().toLowerCase(Locale.ROOT).contains(token) ||
                                        x.getDetalle().toLowerCase(Locale.ROOT).contains(token) ||
                                        x.getMascota().getCaracteristicas().stream().map(c -> c.getValor().getDescripcion().toLowerCase(Locale.ROOT)).collect(Collectors.toList()).contains(token)
                        )
                        .collect(Collectors.toList());


                List<Publicacion> seconds = results
                        .stream()
                        .filter(x -> (x instanceof PublicacionQuieroAdoptar))
                        .filter(x ->
                                x.getDescripcion().toLowerCase(Locale.ROOT).contains(token) ||
                                        x.getDetalle().toLowerCase(Locale.ROOT).contains(token) ||
                                        ((PublicacionQuieroAdoptar) x).getCaracteristicasDeMascotaBuscada().stream().map(c -> c.getOpcion().getDescripcion().toLowerCase(Locale.ROOT)).collect(Collectors.toList()).contains(token)
                        )
                        .collect(Collectors.toList());

                finalResults.addAll(first);
                finalResults.addAll(seconds);
            }

            finalResults = finalResults.stream().distinct().collect(Collectors.toList());
        } else {
            finalResults = results;
        }


        // RECOMENDACIONES
        return this.marcarRecomendadas(finalResults, persona);

    }

    private List<Publicacion> marcarRecomendadas(List<Publicacion> finalResults, Persona persona) {
        // BUSCAR RECOMENDACIONES POR ID
        if(persona != null){
            List<Integer> idsPublicacionesRecomendadas = recomendacionesService.findRecomendacionesByPerson(persona.getId()).stream().map(e -> e.getIdPublicacion()).collect(Collectors.toList());
            // iterar y marcar la lista original
            if(idsPublicacionesRecomendadas != null ) {
                for (int i=0;i<idsPublicacionesRecomendadas.size();i++) {
                    for (int j=0;j<finalResults.size();j++) {
                        this.marcarComoRecomendadaSiLoEs(finalResults.get(j), idsPublicacionesRecomendadas.get(i));
                    }
                }
            }
        }
        return finalResults;
    }

    private Publicacion marcarComoRecomendadaSiLoEs(Publicacion publicacion, Integer idPublicacionRecomendada) {
        if(publicacion.getId().intValue() == idPublicacionRecomendada.intValue()){
            publicacion.setRecomendada(true);
            return publicacion;
        } else {
            return publicacion;
        }
    }

    @Transactional
    public List<PublicacionMascotaEnAdopcion> findEnAdopcion() {
        return publicacionDao.findEnAdopcion();
    }


    @Transactional
    public void reclamarMascota(Usuario loggedUser, Integer idPublicacion) throws Exception {
        Persona persona = personaDao.findBy(loggedUser);
        PublicacionMascotaEncontrada publicacion = publicacionDao.findPublicacionMascotaEncontradaBy(idPublicacion);
        if (persona == null) {
            throw new Exception("El usuario no tiene una persona asociada");
        }
        if (publicacion == null) {
            throw new Exception("La publicacion no existe");
        }
        publicacion.reclamar(persona);
        publicacionDao.update(publicacion);
    }

    @Transactional
    public void informarHallazgoMascota(Usuario loggedUser, Integer idPublicacion) throws Exception {
        Persona persona = personaDao.findBy(loggedUser);
        PublicacionMascotaPerdida publicacion = publicacionDao.findPublicacionMascotaPerdidaBy(idPublicacion);

        if (persona == null) {
            throw new Exception("El usuario no tiene una persona asociada");
        }
        if (publicacion == null) {
            throw new Exception("La publicacion no existe");
        }

        publicacion.informarHallazgo(persona);
        publicacionDao.update(publicacion);
    }

    @Transactional
    public void adoptar(Usuario loggedUser, Integer idPublicacion) throws Exception {
        Persona persona = personaDao.findBy(loggedUser);
        PublicacionMascotaEnAdopcion publicacion = publicacionDao.findPublicacionAdopcionBy(idPublicacion);

        if (persona == null) {
            throw new Exception("El usuario no tiene una persona asociada");
        }
        if (publicacion == null) {
            throw new Exception("La publicacion no existe");
        }

        publicacion.adoptar(persona);
        publicacionDao.update(publicacion);
    }

    @Transactional
    public List<PublicacionQuieroAdoptar> findPublicacionesQuieroAdoptarByPerson(Integer idPersona) throws Exception {

        List<PublicacionQuieroAdoptar> listaPublicacionesQuieroAdoptar = publicacionDao.findPublicacionQuieroAdoptarByIdPersona(idPersona);
        listaPublicacionesQuieroAdoptar.forEach(x -> x.getCaracteristicasDeMascotaBuscada().size());
        listaPublicacionesQuieroAdoptar.forEach(x -> x.getCaracteristicasDeMascotaBuscada().forEach(c-> c.getOpcion()));
        return listaPublicacionesQuieroAdoptar;
    }

    @Transactional
    public void ofrecer(Usuario loggedUser, Integer idPublicacion, Mascota mascota) throws Exception {
        Persona persona = personaDao.findBy(loggedUser);
        PublicacionQuieroAdoptar publicacion = publicacionDao.findPublicacionQuieroAdoptarBy(idPublicacion);
        mascota = mascotaDao.findBy(mascota.getId());

        if (persona == null) {
            throw new Exception("El usuario no tiene una persona asociada");
        }
        if (publicacion == null) {
            throw new Exception("La publicacion no existe");
        }

        publicacion.adoptar(persona, mascota);
        publicacionDao.update(publicacion);
    }

    @Transactional
    public Publicacion findPublicacionPublicada(Integer id) throws Exception {
        Publicacion publicacion = publicacionDao.findBy(id, EstadoPublicacion.PUBLICADA);
        if (publicacion == null) {
            throw new Exception("404"); //FIXME NOTFOUD (HANDLE EXCEPTION WITH SPRING)
        }
        if (publicacion.getRespuestasContestadas() != null)
            publicacion.getRespuestasContestadas().size();
        return publicacion;
    }

    @Transactional
    public List<Publicacion> findMisPublicaciones(Usuario loggedUser) {
        Persona persona = personaDao.findBy(loggedUser);
        return publicacionDao.findBy(persona);
    }

    @Transactional
    public List<Publicacion> findPendientes(Usuario loggedUser) {
        Persona persona = personaDao.findBy(loggedUser);
        //FIXME CHANGE NAME METHOD
        List<Publicacion> result = new ArrayList<>();
        List<PublicacionMascotaEncontrada> mascotasEncontradas = publicacionDao.findMascotaEncontradaBy(persona);
        List<PublicacionQuieroAdoptar> quieroAdoptar = publicacionDao.findQuieroAdoptarBy(persona);

        result.addAll(mascotasEncontradas);
        result.addAll(quieroAdoptar);
        return  result;
    }


    @Transactional
    public Publicacion findPublicacion(Integer id, Usuario loggedUser) throws Exception {
        Persona persona = personaDao.findBy(loggedUser);
        Publicacion publicacion = publicacionDao.findBy(id, persona);
        if (publicacion == null) {
            throw new Exception("404"); //FIXME NOTFOUD (HANDLE EXCEPTION WITH SPRING)
        }
        if (publicacion.getRespuestasContestadas() != null)
            publicacion.getRespuestasContestadas().size();
        return publicacion;

    }

    @Transactional
    public void rechazarSolicitud(Integer id, Usuario loggedUser) throws Exception {
        Persona persona = personaDao.findBy(loggedUser);
        Publicacion publicacion = publicacionDao.findBy(id, persona);

        if (publicacion == null) {
            throw new Exception("404"); //FIXME NOTFOUD (HANDLE EXCEPTION WITH SPRING)
        }

        publicacion.rechazarSolicitud();
        publicacionDao.update(publicacion);
    }

    @Transactional
    public void entregar(Integer id, Usuario loggedUser) throws Exception {
        Persona persona = personaDao.findBy(loggedUser);
        Publicacion publicacion = publicacionDao.findBy(id, persona);

        if (publicacion == null) {
            throw new Exception("404"); //FIXME NOTFOUD (HANDLE EXCEPTION WITH SPRING)
        }

        publicacion.entregar();
        publicacionDao.update(publicacion);
    }

    @Transactional
    public void cancelar(Integer id, Usuario loggedUser) throws Exception {
        Persona persona = personaDao.findBy(loggedUser);
        Publicacion publicacion = publicacionDao.findBy(id, persona);

        if (publicacion == null) {
            throw new Exception("404"); //FIXME NOTFOUD (HANDLE EXCEPTION WITH SPRING)
        }

        publicacion.cancelar();
        publicacionDao.update(publicacion);
    }

    @Transactional
    public void finalizar(Integer id, Usuario loggedUser) throws Exception {
        Persona persona = personaDao.findBy(loggedUser);
        //FIXME OJO ACA QUE CUALQUIER PUEDE TERMINAR CUALQUIER PUBLICACION
        Publicacion publicacion = publicacionDao.findBy(id);

        if (publicacion == null) {
            throw new Exception("404"); //FIXME NOTFOUD (HANDLE EXCEPTION WITH SPRING)
        }

        publicacion.finalizar();
        publicacionDao.update(publicacion);
    }

    //FIXME HACER BUILDER PARA NUEVAS PUBLICACIONES ASI SE ASIGNA EN EL MODELO TODO ESTO
    @Transactional
    public void nueva(PublicacionMascotaEncontrada publicacion, Usuario loggedUser) throws Exception {
        Persona persona = personaDao.findBy(loggedUser);
        publicacion.setPublicadaPor(persona);
        publicacion.getMascota().setEstado(EstadoMascota.RESCATADA);
        publicacion.setOrganizacion(persona.getOrganizacion());

        Mascota mascota = publicacion.getMascota();
        for (CaracteristicaMascota cv : mascota.getCaracteristicas()) {
            cv.setCaracteristica(caracteristicaDao.findBy(cv.getCaracteristica().getId()));
            cv.setValor(opcionCaracteristicaDao.findBy(cv.getValor().getId()));
            cv.setMascota(publicacion.getMascota());
        }

        publicacionDao.save(publicacion);
    }

    @Transactional
    public void nueva(PublicacionMascotaPerdida publicacion, Usuario loggedUser) throws Exception {
        Persona persona = personaDao.findBy(loggedUser);
        publicacion.setMascota(mascotaDao.findBy(publicacion.getMascota().getId()));
        publicacion.setPublicadaPor(persona);
        publicacion.getMascota().setEstado(EstadoMascota.PERDIDA);
        publicacion.setOrganizacion(persona.getOrganizacion());
        publicacionDao.save(publicacion);
    }

    @Transactional
    public void nueva(PublicacionMascotaEnAdopcion publicacion, Usuario loggedUser) throws Exception {
        Persona persona = personaDao.findBy(loggedUser);
        publicacion.setMascota(mascotaDao.findBy(publicacion.getMascota().getId()));
        publicacion.setPublicadaPor(persona);
        publicacion.getMascota().setEstado(EstadoMascota.EN_ADOPCION);
        publicacion.setOrganizacion(persona.getOrganizacion());

        for (RespuestaAdopcion respuesta : publicacion.getRespuestas()) {
            respuesta.setPublicacion(publicacion);
            respuesta.setPregunta(commonDao.findBy(PreguntaAdopcion.class, respuesta.getPregunta().getId()));
            respuesta.setRespuesta(commonDao.findBy(OpcionPreguntaAdopcion.class, respuesta.getRespuesta().getId()));
        }

        publicacionDao.save(publicacion);
    }


    @Transactional
    public void nueva(PublicacionQuieroAdoptar publicacion, Usuario loggedUser) throws Exception {
        Persona persona = personaDao.findBy(loggedUser);
        PublicacionQuieroAdoptar pubAuxx = publicacion;
        publicacion.setPublicadaPor(persona);
        publicacion.setOrganizacion(persona.getOrganizacion());

        //FIXME -1 EN COMBOS?
        if (publicacion.getSexo().getId() == -1) {
            publicacion.setSexo(null);
        }

        for (RespuestaQuieroAdoptar respuesta : publicacion.getRespuestas()) {
            respuesta.setPublicacion(publicacion);
            respuesta.setPregunta(commonDao.findBy(PreguntaQuieroAdoptar.class, respuesta.getPregunta().getId()));
            respuesta.setRespuesta(commonDao.findBy(OpcionPreguntaQuieroAdoptar.class, respuesta.getRespuesta().getId()));

        }


        PublicacionQuieroAdoptar pubAux = publicacion;
        List<CaracteristicaQuieroAdoptar>  caracteristicasDelafuckingpublicaion = publicacion.getCaracteristicasDeMascotaBuscada();

        //FIXME -1 EN COMBOS?
       //publicacion.getCaracteristicasDeMascotaBuscada().removeIf(x-> x.getOpcion().getId() == -1);
        for (CaracteristicaQuieroAdoptar caracteristica : publicacion.getCaracteristicasDeMascotaBuscada()) {
            caracteristica.setPublicacion(publicacion);

            Integer caractId = caracteristica.getCaracteristica().getId();
            Caracteristica caractaux = commonDao.findBy(Caracteristica.class, caracteristica.getCaracteristica().getId());
            caracteristica.setCaracteristica(commonDao.findBy(Caracteristica.class, caracteristica.getCaracteristica().getId()));

            Integer opcionId = caracteristica.getOpcion().getId();
            OpcionCaracteristica opAux = commonDao.findBy(OpcionCaracteristica.class, caracteristica.getOpcion().getId());
            caracteristica.setOpcion(commonDao.findBy(OpcionCaracteristica.class, caracteristica.getOpcion().getId()));

        }
        publicacionDao.save(publicacion);
    }

    @Transactional
    public Publicacion findPublicacionParaEditar(Integer id, Usuario loggedUser) throws Exception {
        Persona persona = personaDao.findBy(loggedUser);
        Publicacion publicacion = publicacionDao.findBy(id, persona);
        if (publicacion == null) {
            throw new Exception("404"); //FIXME NOTFOUD (HANDLE EXCEPTION WITH SPRING)
        }
        if (!publicacion.getSePuedeEditar()) {
            throw new Exception("404"); //FIXME NOTFOUD (HANDLE EXCEPTION WITH SPRING)
        }

        if (publicacion.getMascota() != null) {
            publicacion.getMascota().getCaracteristicas().forEach(x -> x.getCaracteristica().getOpciones().size());
        }


        if (publicacion.getRespuestasContestadas() != null)
            publicacion.getRespuestasContestadas().size();

        return publicacion;
    }



    //FIXME
    @Transactional
    public void actualizar(PublicacionMascotaPerdida publicacion, Usuario loggedUser) throws Exception {
        Persona persona = personaDao.findBy(loggedUser);
        PublicacionMascotaPerdida update = (PublicacionMascotaPerdida) findPublicacion(publicacion.getId(), loggedUser);

        update.setDetalle(publicacion.getDetalle());
        update.setDescripcion(publicacion.getDescripcion());
        update.setUbicacion(publicacion.getUbicacion());

        publicacionDao.update(update);
    }

    @Transactional
    public void actualizar(PublicacionMascotaEncontrada publicacion, Usuario loggedUser) throws Exception {
        Persona persona = personaDao.findBy(loggedUser);
        PublicacionMascotaEncontrada update = (PublicacionMascotaEncontrada) findPublicacion(publicacion.getId(), loggedUser);

        update.setDetalle(publicacion.getDetalle());
        update.setDescripcion(publicacion.getDescripcion());
        update.setUbicacion(publicacion.getUbicacion());
        Mascota mascota = publicacion.getMascota();

        update.getMascota().setTipoMascota(mascota.getTipoMascota());
        update.getMascota().setNombre(mascota.getNombre());
        update.getMascota().setApodo(mascota.getApodo());
        update.getMascota().setEdad(mascota.getEdad());
        update.getMascota().setSexo(mascota.getSexo());
        update.getMascota().setDescripcionFisica(mascota.getDescripcionFisica());

        List<CaracteristicaMascota> nuevas = publicacion.getMascota().getCaracteristicas();

        for (CaracteristicaMascota cv : update.getMascota().getCaracteristicas()) {
            Optional<CaracteristicaMascota> item =  nuevas.stream().filter(x -> x.getCaracteristica().getId().intValue() == cv.getCaracteristica().getId().intValue()).findAny();
            if (item.isPresent()) {

                cv.setValor(commonDao.findBy(OpcionCaracteristica.class, item.get().getValor().getId()));
            }
        }


        publicacionDao.update(update);
    }

}
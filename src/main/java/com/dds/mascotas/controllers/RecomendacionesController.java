package com.dds.mascotas.controllers;

import com.dds.helpers.PublicacionReglasHelper;
import com.dds.helpers.UbicacionHelper;
import com.dds.mascotas.forms.PublicacionMascotaEnAdopcionForm;
import com.dds.mascotas.forms.PublicacionQuieroAdoptarForm;
import com.dds.mascotas.model.*;
import com.dds.mascotas.model.Recomendacion;
import com.dds.mascotas.model.PublicacionQuieroAdoptar;
import com.dds.mascotas.model.Persona;
import com.dds.mascotas.model.rules.Puntaje;
import com.dds.mascotas.model.rules.Regla;
import com.dds.mascotas.model.rules.ReglasEngine;
import com.dds.mascotas.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;


import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.Assert.assertTrue;


@Controller
@RequestMapping("Api")
public class RecomendacionesController extends CommonController {

    private PublicacionService publicacionService;
    private RecomendacionesService recomendacionesService;
    private RespuestasService respuestasService;
    private MongoService mongoService;

    @Autowired
    private RecomendacionesController(PublicacionService publicacionService, MongoService mongoService, RecomendacionesService recomendacionesService, RespuestasService respuestasService) {
        this.publicacionService = publicacionService;
        this.recomendacionesService = recomendacionesService;
        this.respuestasService = respuestasService;
        this.mongoService = mongoService;
    }

    public List<PublicacionMascotaEnAdopcion> filtrarPorCondiciones(List<PublicacionQuieroAdoptar> listaDePublicacionesQuieroAdoptar, List<PublicacionMascotaEnAdopcion> listaTodasLasPublicacionesEnAdopcion){

        List<PublicacionMascotaEnAdopcion> publicacionesFiltradasPorCondiciones = listaTodasLasPublicacionesEnAdopcion;

        for ( PublicacionQuieroAdoptar publicacionQuieroAdoptar: listaDePublicacionesQuieroAdoptar) {
            publicacionesFiltradasPorCondiciones = publicacionesFiltradasPorCondiciones.stream().filter( publicacionEnAdopcion -> this.cumpleCondiciones(publicacionEnAdopcion, publicacionQuieroAdoptar)).collect(Collectors.toList());
        }

        return publicacionesFiltradasPorCondiciones;
    }

    public Boolean cumpleCondiciones(PublicacionMascotaEnAdopcion publicacionEnAdopcion, PublicacionQuieroAdoptar publicacionQuieroAdoptar){
       List<RespuestaQuieroAdoptar> respuestasQuieroAdoptar = respuestasService.findRespuestasPublicacionQuieroAdoptar(publicacionQuieroAdoptar.getId());
       List<RespuestaAdopcion> respuestasAdopcion = respuestasService.findRespuestasPublicacionAdopcion(publicacionEnAdopcion.getId());

        // filtrar las respuestas que corresponden a condiciones
        List<RespuestaQuieroAdoptar> respuestasCondicionesQuieroAdoptar = respuestasQuieroAdoptar.stream().filter( respuesta -> this.cumpleCondicionNoEsNull(respuesta)).collect(Collectors.toList());
        List<RespuestaAdopcion> respuestasCondicionesAdopcion = respuestasAdopcion.stream().filter( respuesta -> respuesta.cumpleCondicion != null).collect(Collectors.toList());

       //    [ { idPregunta, cumpleCondicion}, 1 (TIENE PATIO?), true
       //     {idPregunta, cumpleCondicion}], 2(necesitas que sea tranquila?), false]

        // Contemplamos los casos que la otra opcion no sea excluyente. por ej: si la persoan que quiere adoptar tiene patio, y hay una mascota en adopcion que no lo necesite.
       for ( RespuestaQuieroAdoptar respuesta: respuestasCondicionesQuieroAdoptar) {
           //excluyentes
           if( respuesta.getPregunta().esExcluyente) {
               // si contesto SI
               if(respuesta.cumpleCondicion){
                   // tengo que buscar que matchee si o si con un SI(es decir una combinacion identica (id, TRUE))
                   if( ! this.contieneRespuesta(respuestasCondicionesAdopcion, respuesta)){
                       return false;
                   } // y si no, acepta tanto uno como otro, un SI o un NO (me da lo mismo) no ponemos el else
               }
           } else {
               // si NO ES excluyente
               if( !respuesta.getPregunta().esExcluyente) {
                   // Vamos a buscar si contesto por NO
                   if(!respuesta.cumpleCondicion){
                       //si contesto por NO, tenemos entonces que buscar que matchee si o si con una combinacion de respuesta con NO = { idPregunta, false}
                       if( ! this.contieneRespuesta(respuestasCondicionesAdopcion, respuesta)){
                           // si no la contiene- no cumple con la condicion necesaria y rompe
                           return false;
                       }
                   }
               }
           }
        }
       return true;
    }

    public Boolean cumpleCondicionNoEsNull(RespuestaQuieroAdoptar respuesta ) {
        return !respuesta.cumpleCondicion.equals(null);
    }

    public Boolean contieneRespuesta(List<RespuestaAdopcion> respuestasCondicionesAdopcion, RespuestaQuieroAdoptar respuestaQuieroAdoptar ){
        List<RespuestaAdopcion> respuestaAcertada = respuestasCondicionesAdopcion.stream().filter( rtaAdopcion -> this.hayMatcheoDeRespuestas(rtaAdopcion, respuestaQuieroAdoptar)).collect(Collectors.toList());
        return respuestaAcertada.size() > 0;
    }

    public Boolean hayMatcheoDeRespuestas(RespuestaAdopcion rtaAdopcion, RespuestaQuieroAdoptar rtaQuieroAdoptar){
        RespuestaAdopcion adopAux = rtaAdopcion;
        RespuestaQuieroAdoptar quierAux = rtaQuieroAdoptar;
        return (rtaAdopcion.getPregunta().getId() == rtaQuieroAdoptar.getPregunta().getId()) && (rtaAdopcion.cumpleCondicion == rtaQuieroAdoptar.cumpleCondicion);
    }

    /*  Aclaracion condiciones:
    quiero adoptar
      no excluyente  tengo patio? si -> si | no
                     no -> no (filtrar las que no tienen patio)

      excluyente   necesitas que sea tranquilo ? si -> si (filtrar las mascotas que si o si sean tranquilas)
                                       no -> si | no
      excluyente    tenes otras mascotas?  si -> SI (filtrar las mascotas que si o si puedan convivir con otras mascotas)
                                no -> si | no
    */

    public List<Puntaje<PublicacionMascotaEnAdopcion>> puntuarPublicaciones(List<PublicacionMascotaEnAdopcion> publicacionesEnAdopcionFiltradasPorCondiciones, List<PublicacionQuieroAdoptar> listaDePublicacionesQuieroAdoptar){

        List<Puntaje<PublicacionMascotaEnAdopcion>> publicacionesPuntuadas = new ArrayList<Puntaje<PublicacionMascotaEnAdopcion>>();

        for ( PublicacionQuieroAdoptar publicacionQuieroAdoptar: listaDePublicacionesQuieroAdoptar) {
            publicacionesPuntuadas = Stream.concat(publicacionesPuntuadas.stream(),  publicacionesEnAdopcionFiltradasPorCondiciones.stream().map(publicacion -> this.puntuarPublicacion(publicacion, publicacionQuieroAdoptar))).collect(Collectors.toList());
        }
        return publicacionesPuntuadas;
    }


    public Puntaje<PublicacionMascotaEnAdopcion> puntuarPublicacion(PublicacionMascotaEnAdopcion publicacionMascotaEnAdopcion, PublicacionQuieroAdoptar publicacionQuieroAdoptar){
        // Agregar la publicacion quiero adoptar que origina las recomendaciones
        Puntaje<PublicacionMascotaEnAdopcion> publicacionPuntuada = new Puntaje<PublicacionMascotaEnAdopcion>(publicacionMascotaEnAdopcion, 0);
        publicacionPuntuada.setPublicacionOrigen(publicacionQuieroAdoptar);

        //Puntuar
            // obtener caracteristicas de esa mascota deseada:
                List<CaracteristicaQuieroAdoptar> caracteristicasDeMascotaBuscada = publicacionQuieroAdoptar.getCaracteristicasDeMascotaBuscada();
            // obtener caracteristicas de la Publicacion adopcion
                List<CaracteristicaMascota> caracteristicasDeLaMascotaEnAdopcion = publicacionMascotaEnAdopcion.getMascota().getCaracteristicas();


                Integer puntos = 0;
                for (CaracteristicaQuieroAdoptar caracteristica : caracteristicasDeMascotaBuscada){
                    // Caso indistinto
                    if(caracteristica.getOpcion().getDescripcion() == "INDISTINTO"){
                        puntos += 1;
                    }else if(this.contieneCaracteristica(caracteristicasDeLaMascotaEnAdopcion, caracteristica)) {
                        if(caracteristica.getCaracteristica().getNombre().equals("Raza")) {
                            puntos += 2;
                        } else {
                            puntos += 1;
                        }
                    }
                }
                publicacionPuntuada.setPuntaje(puntos);

                return publicacionPuntuada;
    }

    public Boolean contieneCaracteristica(List<CaracteristicaMascota> caracteristicasDeLaMascotaEnAdopcion,CaracteristicaQuieroAdoptar caracteristica){
        List<CaracteristicaMascota> listaConCoincidencia = caracteristicasDeLaMascotaEnAdopcion.stream().filter(x -> (x.getCaracteristica().getId() == caracteristica.getCaracteristica().getId()) && (x.getValor().getId() == caracteristica.getOpcion().getId())).collect(Collectors.toList());
        return listaConCoincidencia.size() > 0;
    }
    //MAIN VIEW
    @GetMapping(value = "/recomendaciones")
    public @ResponseBody
    String generarRecomendaciones(@RequestParam("id") Integer personaID) throws Exception {

        //1) buscar preferencias, es decir, buscamos las publicaciones de quiero adoptar de esa persona [OJO TIENE QUE SER UNA LISTA DE PUBLICACIONES QUIERO ADOPTAR]
        List<PublicacionQuieroAdoptar> listaDePublicacionesQuieroAdoptar = publicacionService.findPublicacionesQuieroAdoptarByPerson(personaID); // es mas de una publicacion

        //2) buscar todas las publicaciones EN ADOPCION y en estado 2 (estado publicacion ACTIVA)
        List<PublicacionMascotaEnAdopcion> listaTodasLasPublicacionesEnAdopcion = publicacionService.findEnAdopcion();

        // 3)  filtrar segun condiciones:
        List<PublicacionMascotaEnAdopcion> publicacionesEnAdopcionFiltradasPorCondiciones = this.filtrarPorCondiciones(listaDePublicacionesQuieroAdoptar, listaTodasLasPublicacionesEnAdopcion);


        // 3) Obtener puntos de cada publicacion filtrada por condicion:
         List<Puntaje<PublicacionMascotaEnAdopcion>> publicacionesPuntuadas = this.puntuarPublicaciones(publicacionesEnAdopcionFiltradasPorCondiciones, listaDePublicacionesQuieroAdoptar); //[ {idPublicacion,puntaje}, {idPublicacion,puntaje}, ... ]

        //4) filtra las publicaciones segun los puntos:
        List<Puntaje<PublicacionMascotaEnAdopcion>> publicacionesPuntuadasFiltradas = publicacionesPuntuadas.stream().filter(publicacion -> publicacion.getPuntaje()>=3).collect(Collectors.toList());

        // 5) guardar en la bbdd
        //recomendacionesService.setRecomendaciones(publicacionesPuntuadasFiltradas, personaID);
        this.mongoService.guardarRecomendacionesMongo(publicacionesPuntuadasFiltradas, personaID);

        return "{resultado  :  \"Ok\". Publicaciones recomendadas: " + publicacionesPuntuadasFiltradas + ". total:" + publicacionesPuntuadasFiltradas.size() ;
    }

}

// HACER EL POST MAPPING (POR SI LA PERSONA CARGA MAS RECOMENDACIONES)
// ESCENARIO: dinamismo: publicaciones se dan de alta y baja o cambian de estado constantemente.
// ESCENARIO: contemple la edicion de preferencias.
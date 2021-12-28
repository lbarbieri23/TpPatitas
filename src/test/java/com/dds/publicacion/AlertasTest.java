package com.dds.publicacion;

import com.dds.helpers.PublicacionReglasHelper;
import com.dds.mascotas.model.*;
import com.dds.mascotas.model.rules.*;
import com.dds.mascotas.model.security.Rol;
import com.dds.mascotas.model.security.Usuario;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class AlertasTest {

    private final TipoMascota tipoPerro = new TipoMascota(1, "PERRO");
    private Rol rol = new Rol(4, "Usuario");
    private Organizacion organizacion;
    private List<PublicacionMascotaEnAdopcion> enAdopcion = new ArrayList<>();



    private Caracteristica tamanio =
            new Caracteristica(
                    organizacion,
                    1,
                    "Tamaño",
                    Arrays.asList(new OpcionCaracteristica[] {
                            new OpcionCaracteristica(1, "GRANDE"),
                            new OpcionCaracteristica(2, "MEDIANO"),
                            new OpcionCaracteristica(3, "CHICO")
                    }));

    private Caracteristica color =
            new Caracteristica(
                    organizacion,
                    2,
                    "Color",
                    Arrays.asList(new OpcionCaracteristica[] {
                            new OpcionCaracteristica(4, "Blanco"),
                            new OpcionCaracteristica(5, "Negro"),
                            new OpcionCaracteristica(6, "Marron")
                    }));

    private Caracteristica pelo =
            new Caracteristica(
                    organizacion,
                    3,
                    "Pelo",
                    Arrays.asList(new OpcionCaracteristica[] {
                            new OpcionCaracteristica(7, "Largo"),
                            new OpcionCaracteristica(8, "Medio"),
                            new OpcionCaracteristica(9, "Corto")
                    }));

    private PreguntaQuieroAdoptar preguntaBalcon =
            new PreguntaQuieroAdoptar(
                    1,
                    "¿Tenes balcon?",
                    Arrays.asList(new OpcionPreguntaQuieroAdoptar[] {
                            new OpcionPreguntaQuieroAdoptar(1, "Si"),
                            new OpcionPreguntaQuieroAdoptar(2, "No"),
                    }));

    private PreguntaQuieroAdoptar preguntaPatio =
            new PreguntaQuieroAdoptar(
                    2,
                    "¿Tenes patio?",
                    Arrays.asList(new OpcionPreguntaQuieroAdoptar[] {
                            new OpcionPreguntaQuieroAdoptar(3, "Si"),
                            new OpcionPreguntaQuieroAdoptar(4, "No"),
                    }));

    private PreguntaQuieroAdoptar preguntaTenesOtrasMascotas =
            new PreguntaQuieroAdoptar(
                    3,
                    "¿Tenes otras mascotas?",
                    Arrays.asList(new OpcionPreguntaQuieroAdoptar[] {
                            new OpcionPreguntaQuieroAdoptar(5, "Si"),
                            new OpcionPreguntaQuieroAdoptar(6, "No"),
                    }));


    private PreguntaAdopcion preguntaEsTranquilo =
            new PreguntaAdopcion(
                    1,
                    "¿Es tranquilo?",
                    Arrays.asList(new OpcionPreguntaAdopcion[] {
                            new OpcionPreguntaAdopcion(1, "Si"),
                            new OpcionPreguntaAdopcion(2, "No"),
                    }));

    private void generarOrganizacion() {
        Ubicacion ubicacionOrganicacion = new Ubicacion(38.50,39.50);
        organizacion = new Organizacion(1, "Asociación 1",ubicacionOrganicacion);

    }


    @Before
    public void generarEscenario() {
        generarOrganizacion();
        Persona diego = new Persona(new Usuario("diego", rol), new Contacto(), organizacion);
        Mascota m = new Mascota(tipoPerro);
        m.setNombre("Perro 1");
        m.setSexo(Sexo.MASCULINO);
        m.setEdad(2);
        m.setApodo("Grande negro pelo largo tranquilo");
        m.agregarCaracteristica(new CaracteristicaMascota(tamanio, new OpcionCaracteristica(1, "GRANDE")));
        m.agregarCaracteristica(new CaracteristicaMascota(color, new OpcionCaracteristica(5, "NEGRO")));
        m.agregarCaracteristica(new CaracteristicaMascota(pelo, new OpcionCaracteristica(7, "LARGO")));
        diego.agregarMascota(m);
        m = new Mascota(tipoPerro);
        m.setNombre("Perro 2");
        m.setSexo(Sexo.MASCULINO);
        m.setEdad(2);
        m.setApodo("Chico blanco corto");
        m.agregarCaracteristica(new CaracteristicaMascota(tamanio, new OpcionCaracteristica(3, "CHICO")));
        m.agregarCaracteristica(new CaracteristicaMascota(color, new OpcionCaracteristica(4, "BLANCO")));
        m.agregarCaracteristica(new CaracteristicaMascota(pelo, new OpcionCaracteristica(9, "CORTO")));
        diego.agregarMascota(m);
        m = new Mascota(tipoPerro);
        m.setNombre("Perro 3");
        m.setSexo(Sexo.MASCULINO);
        m.setEdad(2);
        m.setApodo("Chico blanco largo");
        m.agregarCaracteristica(new CaracteristicaMascota(tamanio, new OpcionCaracteristica(3, "CHICO")));
        m.agregarCaracteristica(new CaracteristicaMascota(color, new OpcionCaracteristica(6, "MARRON")));
        m.agregarCaracteristica(new CaracteristicaMascota(pelo, new OpcionCaracteristica(7, "LARGO")));
        diego.agregarMascota(m);
        m = new Mascota(tipoPerro);
        m.setNombre("Perro 4");
        m.setSexo(Sexo.MASCULINO);
        m.setEdad(2);
        m.setApodo("Grande blanco largo");
        m.agregarCaracteristica(new CaracteristicaMascota(tamanio, new OpcionCaracteristica(1, "GRANDE")));
        m.agregarCaracteristica(new CaracteristicaMascota(color, new OpcionCaracteristica(4, "BLANCO")));
        m.agregarCaracteristica(new CaracteristicaMascota(pelo, new OpcionCaracteristica(7, "LARGO")));
        diego.agregarMascota(m);


        PublicacionMascotaEnAdopcion publicacion =new PublicacionMascotaEnAdopcion(diego, null, diego.getMisMascotas().get(0).getApodo(), new Ubicacion(0d,0d), diego.getMisMascotas().get(0));
        publicacion.agregarRespuesta(new RespuestaAdopcion(preguntaEsTranquilo, new OpcionPreguntaAdopcion(1,"Si")));
        enAdopcion.add(publicacion);
        publicacion =new PublicacionMascotaEnAdopcion(diego, null, diego.getMisMascotas().get(1).getApodo(), new Ubicacion(0d,0d), diego.getMisMascotas().get(1));
        publicacion.agregarRespuesta(new RespuestaAdopcion(preguntaEsTranquilo, new OpcionPreguntaAdopcion(2,"No")));
        enAdopcion.add(publicacion);
        publicacion =new PublicacionMascotaEnAdopcion(diego, null, diego.getMisMascotas().get(2).getApodo(), new Ubicacion(0d,0d), diego.getMisMascotas().get(2));
        publicacion.agregarRespuesta(new RespuestaAdopcion(preguntaEsTranquilo, new OpcionPreguntaAdopcion(2,"No")));
        enAdopcion.add(publicacion);
        publicacion =new PublicacionMascotaEnAdopcion(diego, null, diego.getMisMascotas().get(3).getApodo(), new Ubicacion(0d,0d), diego.getMisMascotas().get(3));
        publicacion.agregarRespuesta(new RespuestaAdopcion(preguntaEsTranquilo, new OpcionPreguntaAdopcion(2,"No")));
        enAdopcion.add(publicacion);
    }

    @Test
    public void TestCaracteristicaSinPreguntas(){
       /* Persona pedro = new Persona(new Usuario("pedro", rol), new Contacto(), organizacion);
        PublicacionQuieroAdoptar quieroAdoptar = new PublicacionQuieroAdoptar(pedro, null, "Quiero un perro", new Ubicacion(0d,0d), tipoPerro, Sexo.MASCULINO);
        quieroAdoptar.agregarCaracteristica(new CaracteristicaQuieroAdoptar(pelo, new OpcionCaracteristica(7, "Largo")));
        quieroAdoptar.agregarCaracteristica(new CaracteristicaQuieroAdoptar(tamanio, new OpcionCaracteristica(3, "Chico")));


        List<Regla> reglas = PublicacionReglasHelper.getInstance().getReglasFor(quieroAdoptar, null);

        ReglasEngine engine = new ReglasEngine<PublicacionMascotaEnAdopcion>();
        List<Puntaje<PublicacionMascotaEnAdopcion>> results = engine.puntuar(enAdopcion, reglas);

        assertTrue(results.get(0).getPuntaje() == 30);
        assertTrue(results.get(1).getPuntaje() == 25);
        assertTrue(results.get(2).getPuntaje() == 25);
        assertTrue(results.get(3).getPuntaje() == 25);*/
    }


    @Test
    public void TestReglasSinCaracterisitcas(){
        Persona pedro = new Persona(new Usuario("pedro", rol), new Contacto(), organizacion);
        PublicacionQuieroAdoptar quieroAdoptar = new PublicacionQuieroAdoptar(pedro, null, "Quiero un perro", new Ubicacion(0d,0d), tipoPerro, Sexo.MASCULINO);

        quieroAdoptar.agregarRespuesta(new RespuestaQuieroAdoptar(preguntaBalcon, new OpcionPreguntaQuieroAdoptar(1, "Si")));
        quieroAdoptar.agregarRespuesta(new RespuestaQuieroAdoptar(preguntaPatio, new OpcionPreguntaQuieroAdoptar(4, "No")));
        quieroAdoptar.agregarRespuesta(new RespuestaQuieroAdoptar(preguntaTenesOtrasMascotas, new OpcionPreguntaQuieroAdoptar(5, "Si")));


        List<ReglaConCondicionTipoOr> customRules = new ArrayList<>();
        customRules.add(new ReglaConCondicionTipoOr(
                new Condicion(new CaracteristicaMascota(tamanio, new OpcionCaracteristica(1, "GRANDE"))),
                Arrays.asList(
                        new RespuestaQuieroAdoptar[]{
                                new RespuestaQuieroAdoptar(preguntaPatio, new OpcionPreguntaQuieroAdoptar(3, "Si"))
                        }),
                1,
                -3,
                0
        ));
        customRules.add(new ReglaConCondicionTipoOr(
                new Condicion(new RespuestaAdopcion(preguntaEsTranquilo, new OpcionPreguntaAdopcion(1, "Si"))),
                Arrays.asList(
                        new RespuestaQuieroAdoptar[]{
                                new RespuestaQuieroAdoptar(preguntaTenesOtrasMascotas, new OpcionPreguntaQuieroAdoptar(5, "Si")),
                                new RespuestaQuieroAdoptar(preguntaTenesOtrasMascotas, new OpcionPreguntaQuieroAdoptar(6, "No"))
                        }),
                1,
                -3,
                0
        ));

        List<Regla> reglas = PublicacionReglasHelper.getInstance().getReglasFor(quieroAdoptar, customRules);

        ReglasEngine engine = new ReglasEngine<PublicacionMascotaEnAdopcion>();
        List<Puntaje<PublicacionMascotaEnAdopcion>> results = engine.puntuar(enAdopcion, reglas);

        assertTrue(results.get(0).getPuntaje() == 20);
        assertTrue(results.get(1).getPuntaje() == 20);
        assertTrue(results.get(2).getPuntaje() == 18);
        assertTrue(results.get(3).getPuntaje() == 17);
    }


    @Test
    public void TestReglasYCaracterisitcas(){
       /* Persona pedro = new Persona(new Usuario("pedro", rol), new Contacto(), organizacion);
        PublicacionQuieroAdoptar quieroAdoptar = new PublicacionQuieroAdoptar(pedro, null, "Quiero un perro", new Ubicacion(0d,0d), tipoPerro, Sexo.MASCULINO);
        quieroAdoptar.agregarCaracteristica(new CaracteristicaQuieroAdoptar(pelo, new OpcionCaracteristica(7, "Largo")));
        quieroAdoptar.agregarCaracteristica(new CaracteristicaQuieroAdoptar(tamanio, new OpcionCaracteristica(3, "Chico")));


        quieroAdoptar.agregarRespuesta(new RespuestaQuieroAdoptar(preguntaBalcon, new OpcionPreguntaQuieroAdoptar(1, "Si")));
        quieroAdoptar.agregarRespuesta(new RespuestaQuieroAdoptar(preguntaPatio, new OpcionPreguntaQuieroAdoptar(4, "No")));
        quieroAdoptar.agregarRespuesta(new RespuestaQuieroAdoptar(preguntaTenesOtrasMascotas, new OpcionPreguntaQuieroAdoptar(5, "Si")));



        List<ReglaConCondicionTipoOr> customRules = new ArrayList<>();
        customRules.add(new ReglaConCondicionTipoOr(
                new Condicion(new CaracteristicaMascota(tamanio, new OpcionCaracteristica(1, "GRANDE"))),
                Arrays.asList(
                        new RespuestaQuieroAdoptar[]{
                                new RespuestaQuieroAdoptar(preguntaPatio, new OpcionPreguntaQuieroAdoptar(3, "Si"))
                        }),
                1,
                0,
                0
        ));

        customRules.add(new ReglaConCondicionTipoOr(
                new Condicion(new RespuestaAdopcion(preguntaEsTranquilo, new OpcionPreguntaAdopcion(1, "Si"))),
                Arrays.asList(
                        new RespuestaQuieroAdoptar[]{
                                new RespuestaQuieroAdoptar(preguntaTenesOtrasMascotas, new OpcionPreguntaQuieroAdoptar(5, "Si")),
                                new RespuestaQuieroAdoptar(preguntaTenesOtrasMascotas, new OpcionPreguntaQuieroAdoptar(6, "No"))
                        }),
                1,
                0,
                0
        ));

        customRules.add(new ReglaConCondicionTipoOr(
                new Condicion(new RespuestaAdopcion(preguntaEsTranquilo, new OpcionPreguntaAdopcion(2, "No"))),
                Arrays.asList(
                        new RespuestaQuieroAdoptar[]{
                                new RespuestaQuieroAdoptar(preguntaTenesOtrasMascotas, new OpcionPreguntaQuieroAdoptar(6, "No"))
                        }),
                1,
                -3,
                0
        ));


        List<Regla> reglas = PublicacionReglasHelper.getInstance().getReglasFor(quieroAdoptar, customRules);
        ReglasEngine engine = new ReglasEngine<PublicacionMascotaEnAdopcion>();
        List<Puntaje<PublicacionMascotaEnAdopcion>> results = engine.puntuar(enAdopcion, reglas);

        assertTrue(results.get(0).getPuntaje() == 27);
        assertTrue(results.get(1).getPuntaje() == 23);
        assertTrue(results.get(2).getPuntaje() == 22);
        assertTrue(results.get(3).getPuntaje() == 19);*/

    }
}
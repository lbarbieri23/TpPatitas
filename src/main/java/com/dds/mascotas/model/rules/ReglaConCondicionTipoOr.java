package com.dds.mascotas.model.rules;

import java.util.List;

public class ReglaConCondicionTipoOr extends Regla {

    private Condicion condicion;
    private List<Respuesta> algunaRespuestaACumplir;
    private List<Respuesta> respuestasContestadas;
    private Integer puntosPorIncorrecta;
    private Integer puntosPorNoCumplirCondicion;

    public ReglaConCondicionTipoOr(Condicion condicion, List<Respuesta> algunaRespuestaACumplir, Integer puntosPorCorrecta, Integer puntosPorIncorrecta, Integer puntosPorNoCumplirCondicion) {
        this.condicion = condicion;
        this.algunaRespuestaACumplir = algunaRespuestaACumplir;
        super.puntos = puntosPorCorrecta;
        this.puntosPorIncorrecta = puntosPorIncorrecta;
        this.puntosPorNoCumplirCondicion = puntosPorNoCumplirCondicion;
    }

    public void setRespuestasContestadas(List<Respuesta> respuestasContestadas) {
        this.respuestasContestadas = respuestasContestadas;
    }

    @Override
    public Integer calcularPuntos(List<Respuesta> respuestasContestadasParaCondicion) {
        if (condicion.cumple(respuestasContestadasParaCondicion)) {  //SI HAY ALGUNA PREGUNTA CON ESA RESPUESTA
            for (Respuesta r: algunaRespuestaACumplir) {
                if (this.respuestasContestadas.stream().filter(x -> x.esIgual(r)).findAny().isPresent()) {
                    return puntos;
                }
            }
        } else {
            return puntosPorNoCumplirCondicion;
        }
        return  puntosPorIncorrecta;
    }

}

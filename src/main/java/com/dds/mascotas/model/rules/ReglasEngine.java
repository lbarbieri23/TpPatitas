package com.dds.mascotas.model.rules;

import com.dds.mascotas.model.*;

import java.util.ArrayList;
import java.util.List;

public class ReglasEngine<T extends Puntuable> {

    public List<Puntaje<T>> puntuar(List<T> listaAPuntuar,  List<Regla> reglas) {
        List<Puntaje<T>> results = new ArrayList<>();

        for (T item: listaAPuntuar) {
            Integer puntos = 0;
            for (Regla r: reglas) {
                puntos += r.calcularPuntos(item.getRespuestasContestadas());
            }

            results.add(new Puntaje<T>(item, puntos));
        }

        results.sort((x,y) -> y.getPuntaje().compareTo(x.getPuntaje()));
        return results;
    }




}

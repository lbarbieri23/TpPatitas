package com.dds.helpers;

import com.dds.mascotas.model.Hogar;
import com.dds.mascotas.model.Ubicacion;
import com.dds.mascotas.model.adapters.hogares.ICriterioBuilder;
import org.geotools.referencing.GeodeticCalculator;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class UbicacionHelper {

    private static UbicacionHelper instance;

    public static UbicacionHelper getInstance() {
        if (instance == null) {
            instance = new UbicacionHelper();
        }

        return instance;
    }

    private UbicacionHelper() {

    }

    private Double getDistanceInMetersFrom (IUbicable point1, IUbicable point2) {
        final GeodeticCalculator calc = new GeodeticCalculator();

        final Point2D source = new Point2D.Double(point1.getLongitud(), point2.getLatitud());
        final Point2D destination = new Point2D.Double(point1.getLongitud(), point2.getLatitud());
        calc.setStartingGeographicPoint(source);
        calc.setDestinationGeographicPoint(destination);

        return calc.getOrthodromicDistance();
    }


    public IUbicable obtenerMasCercana(List<IUbicable> ubicaciones, IUbicable ubicacion) {
        ubicaciones.sort(
                (a,b) -> getDistanceInMetersFrom(a,ubicacion).compareTo(getDistanceInMetersFrom(b, ubicacion))
        );
        return ubicaciones.get(0);
    }

    public List<IUbicable> obtenerUbicacionEnRadio(List<IUbicable> ubicaciones, IUbicable source, Double radioInMeters) {
        return ubicaciones.stream().filter(x -> getDistanceInMetersFrom(x, source) <= radioInMeters).collect(Collectors.toList());
    }


    public Ubicacion get (String direccion) {
        //FIXME TODO GOOGLE API
        return new Ubicacion(1d,1d);
    }


}

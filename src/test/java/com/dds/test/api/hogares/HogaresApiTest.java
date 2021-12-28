package com.dds.test.api.hogares;

import com.dds.services.hogares.model.Hogar;
import com.dds.services.hogares.HogarService;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class HogaresApiTest {

    @Test
    public void obtenerTodosLosHogares() throws  Exception {
        List<Hogar> hogares = new HogarService("4Xrkv0yBaeI9VykNbkh3ATWwfPqsZopLVx4V7alkbcsI2vHTRytWorA9hcYq").getHogares();
        assertTrue(hogares.size() > 0);
    }

}
package com.example.lukekramer.virtualzoo.domain.vehicle;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by lukekramer on 23/10/2016.
 */

public class VehicleTest {

    @Test
    public void createVehicle() throws Exception {

        Vehicle productTest = new Vehicle.Builder()
                .id(Long.valueOf("1"))
                .productDescription("Sports Car")
                .productType("Car")
                .productMake("BMW")
                .productModel("M3")
                .category("Vehicle")
                .build();

        Assert.assertNotNull(productTest);
        Assert.assertEquals("Sports Car",productTest.getDescription());
        Assert.assertEquals("Car",productTest.getVehicletype());
        Assert.assertEquals("BMW",productTest.getMake());
        Assert.assertEquals("M3",productTest.getModel());
        Assert.assertSame(Long.valueOf("1"),productTest.getId());
        Assert.assertEquals("Vehicle",productTest.getCategory());

        productTest = new Vehicle.Builder()
                .copy(productTest)
                .productDescription("Change")
                .build();
        Assert.assertEquals("Change",productTest.getDescription());

    }
}

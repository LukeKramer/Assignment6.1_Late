package com.example.lukekramer.virtualzoo.domain.computer;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by lukekramer on 23/10/2016.
 */

public class ComputerTest {

    @Test
    public void createComputer() throws Exception {

        Computer productTest = new Computer.Builder()
                .id(Long.valueOf("1"))
                .productDescription("Computer")
                .productType("LapTop")
                .productModel("MacBook Pro")
                .productMake("Apple")
                .category("Electronics")
                .build();

        Assert.assertNotNull(productTest);
        Assert.assertEquals("Computer",productTest.getDescription());
        Assert.assertEquals("Electronics",productTest.getCategory());
        Assert.assertEquals("LapTop",productTest.getType());
        Assert.assertEquals("MacBook Pro",productTest.getModel());
        Assert.assertEquals("Apple",productTest.getMake());
        Assert.assertSame(Long.valueOf("1"),productTest.getId());

        productTest = new Computer.Builder()
                .copy(productTest)
                .productDescription("Change")
                .build();
        Assert.assertEquals("Change",productTest.getDescription());


    }
}

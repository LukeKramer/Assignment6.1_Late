package com.example.lukekramer.virtualzoo.domain.cellphone;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by lukekramer on 23/10/2016.
 */

public class CellphoneTest {

    @Test
    public void createPhone() throws Exception {

        Cellphone productTest = new Cellphone.Builder()
                .id(Long.valueOf("1"))
                .productDescription("2015 model")
                .productMake("Iphone")
                .productModel("S6")
                .productType("SmartPhone")
                .category("Electronics")
                .build();

        Assert.assertNotNull(productTest);
        Assert.assertEquals("2015 model",productTest.getDescription());
        Assert.assertEquals("Iphone",productTest.getMake());
        Assert.assertEquals("S6",productTest.getModel());
        Assert.assertEquals("SmartPhone",productTest.getType());
        Assert.assertEquals("Electronics",productTest.getCategory());
        Assert.assertSame(Long.valueOf("1"),productTest.getId());


        productTest = new Cellphone.Builder()
                .copy(productTest)
                .productDescription("Change")
                .build();
        Assert.assertEquals("Change",productTest.getDescription());


    }


}

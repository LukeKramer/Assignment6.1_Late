package com.example.lukekramer.virtualzoo.domain.television;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by lukekramer on 23/10/2016.
 */

public class TelevisionTest {

    @Test
    public void createTelevision() throws Exception {

        Television productTest = new Television.Builder()
                .id(Long.valueOf("1"))
                .productDescription("Television")
                .productModel("Smart")
                .productMake("Samsung")
                .productSize("42inch")
                .category("Electronics")
                .build();

        Assert.assertNotNull(productTest);
        Assert.assertEquals("Television",productTest.getDescription());
        Assert.assertEquals("Electronics",productTest.getCategory());
        Assert.assertEquals("42inch",productTest.getSize());
        Assert.assertEquals("Smart",productTest.getModel());
        Assert.assertEquals("Samsung",productTest.getMake());
        Assert.assertSame(Long.valueOf("1"),productTest.getId());

        productTest = new Television.Builder()
                .copy(productTest)
                .productDescription("Change")
                .build();
        Assert.assertEquals("Change",productTest.getDescription());


    }
}

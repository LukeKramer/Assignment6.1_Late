package com.example.lukekramer.virtualzoo.domain.shoes;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by lukekramer on 23/10/2016.
 */

public class ShoesTest {

    @Test
    public void createShoes() throws Exception {

        Shoes productTest = new Shoes.Builder()
                .id(Long.valueOf("1"))
                .productDescription("Sandles")
                .productBrand("Crocs")
                .productSize("7")
                .category("shoes")
                .build();

        Assert.assertNotNull(productTest);
        Assert.assertEquals("Sandles",productTest.getDescription());
        Assert.assertEquals("shoes",productTest.getCategory());
        Assert.assertEquals("Crocs",productTest.getBrand());
        Assert.assertEquals("7",productTest.getSize());
        Assert.assertSame(Long.valueOf("1"),productTest.getId());

        productTest = new Shoes.Builder()
                .copy(productTest)
                .productDescription("Change")
                .build();
        Assert.assertEquals("Change",productTest.getDescription());


    }
}

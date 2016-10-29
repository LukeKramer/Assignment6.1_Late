package com.example.lukekramer.virtualzoo.domain.clothing;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by lukekramer on 23/10/2016.
 */

public class ClothingTest {

    @Test
    public void createClothing() throws Exception {

        Clothing productTest = new Clothing.Builder()
                .id(Long.valueOf("1"))
                .productDescription("Coach Jacket")
                .productBrand("Sol Sol")
                .productSize("medium")
                .category("Jacket")
                .build();

        Assert.assertNotNull(productTest);
        Assert.assertEquals("Coach Jacket",productTest.getDescription());
        Assert.assertEquals("Jacket",productTest.getCategory());
        Assert.assertEquals("Sol Sol",productTest.getBrand());
        Assert.assertEquals("medium",productTest.getSize());
        Assert.assertSame(Long.valueOf("1"),productTest.getId());

        productTest = new Clothing.Builder()
                .copy(productTest)
                .productDescription("Change")
                .build();
        Assert.assertEquals("Change",productTest.getDescription());


    }
}

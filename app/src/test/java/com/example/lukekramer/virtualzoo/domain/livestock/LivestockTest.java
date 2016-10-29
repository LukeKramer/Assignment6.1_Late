package com.example.lukekramer.virtualzoo.domain.livestock;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by lukekramer on 23/10/2016.
 */

public class LivestockTest {

    @Test
    public void createLivestock() throws Exception {

        Livestock productTest = new Livestock.Builder()
                .id(Long.valueOf("1"))
                .productDescription("Cow")
                .productAge("4")
                .productGrade("A")
                .category("Livestock")
                .build();

        Assert.assertNotNull(productTest);
        Assert.assertEquals("Cow",productTest.getDescription());
        Assert.assertEquals("4",productTest.getAge());
        Assert.assertEquals("A",productTest.getGrade());
        Assert.assertSame(Long.valueOf("1"),productTest.getId());
        Assert.assertEquals("Livestock",productTest.getCategory());

        productTest = new Livestock.Builder()
                .copy(productTest)
                .productDescription("Change")
                .build();
        Assert.assertEquals("Change",productTest.getDescription());



    }
}

package com.example.lukekramer.virtualzoo.domain.camera;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by lukekramer on 23/10/2016.
 */

public class CameraTest {

    @Test
    public void createCamera() throws Exception {

        Camera productTest = new Camera.Builder()
                .id(Long.valueOf("1"))
                .productDescription("Capture")
                .category("OutDoor")
                .productMake("Canon")
                .productModel("Z7000")
                .productSize("15")
                .productType("Digital")
                .build();

        Assert.assertNotNull(productTest);
        Assert.assertEquals("Capture",productTest.getDescription());
        Assert.assertEquals("Canon",productTest.getMake());
        Assert.assertEquals("Z7000",productTest.getModel());
        Assert.assertEquals("15",productTest.getSize());
        Assert.assertSame(Long.valueOf("1"),productTest.getId());
        Assert.assertNotNull("OutDoor",productTest.getCategory());

        productTest = new Camera.Builder()
                .copy(productTest)
                .productDescription("Change")
                .build();
        Assert.assertEquals("Change",productTest.getDescription());


    }
}

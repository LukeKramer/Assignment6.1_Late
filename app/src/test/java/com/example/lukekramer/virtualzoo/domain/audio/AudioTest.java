package com.example.lukekramer.virtualzoo.domain.audio;

import com.example.lukekramer.virtualzoo.domain.IProduct;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by lukekramer on 23/10/2016.
 */

public class AudioTest {

    @Test
    public void createAudio() throws Exception {

        Audio productTest = new Audio.Builder()
                .id(Long.valueOf("1"))
                .productDescription("2015 model")
                .productMake("Apple")
                .productWatts("")
                .productAmps("")
                .category("Speakers")
                .build();

        Assert.assertNotNull(productTest);
        Assert.assertEquals("2015 model",productTest.getDescription());
        Assert.assertEquals("Apple",productTest.getMake());
        Assert.assertEquals("Speakers",productTest.getCategory());
        Assert.assertEquals("",productTest.getWatts());
        Assert.assertEquals("",productTest.getAmps());
        Assert.assertSame(Long.valueOf("1"),productTest.getId());

        productTest = new Audio.Builder()
                .copy(productTest)
                .productDescription("Change")
                .build();
        Assert.assertEquals("Change",productTest.getDescription());


    }
}

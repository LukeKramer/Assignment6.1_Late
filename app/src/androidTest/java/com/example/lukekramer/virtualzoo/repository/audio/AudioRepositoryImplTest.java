package com.example.lukekramer.virtualzoo.repository.audio;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import com.example.lukekramer.virtualzoo.domain.audio.Audio;
import com.example.lukekramer.virtualzoo.repository.tables.CreateTables;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Set;

/**
 * Created by lukekramer on 24/10/2016.
 */
@RunWith(AndroidJUnit4.class)
public class AudioRepositoryImplTest {

    private static final String TAG_Audio="audio";
    private Long id;


    @Test
    public void audioRepositoryCRUDTest() throws Exception {

        Context appContext = InstrumentationRegistry.getTargetContext();

        //NB!!IMPORTANT
        //*****
        // Run the table creator code segment once to create tables then comment
        // this code segment out to avoid tables being reset(dropped the recreated) and data being lost
        // when running the testCase multiple times!!
        CreateTables createTables = new CreateTables(appContext);
        createTables.resetDatabase();
        createTables.createTables();

        //******************************************

        //Table
        //Create

        AudioRepository audioRepository = new AudioRepositoryImpl(appContext);

        Audio productTest = new Audio.Builder()
                .productDescription("2015 model")
                .productMake("Apple")
                .productWatts("")
                .productAmps("")
                .category("Speakers")
                .build();

        productTest=audioRepository.save(productTest);
        id = productTest.getId();
        Assert.assertNotNull(TAG_Audio+" CREATE",productTest);

        //READ (findALL)
        Set<Audio> audio = audioRepository.findAll();
        Assert.assertTrue(TAG_Audio+" READ ALL",audio.size()>0);

        //READ (find) by id
        Audio audioEntity = audioRepository.findById(id);
        Assert.assertNotNull(TAG_Audio+" READ ENTITY",audioEntity);

        //UPDATE
        Audio updateAudio = new Audio.Builder()
                .copy(audioEntity)
                .productWatts("50watts")
                .build();
        audioRepository.update(updateAudio);
        Audio newAudio = audioRepository.findById(id);
        Assert.assertEquals(TAG_Audio+ " UPDATE ENTITY","50watts",newAudio.getWatts());

        //DELETE
        audioRepository.delete(newAudio);
        Audio deletedAudio = audioRepository.findById(id);
        Assert.assertNull(TAG_Audio+" DELETE",deletedAudio);

        //DELETE ALL
        int totalrows = audioRepository.deleteAll();
        Assert.assertFalse(totalrows > 0);

    }
}

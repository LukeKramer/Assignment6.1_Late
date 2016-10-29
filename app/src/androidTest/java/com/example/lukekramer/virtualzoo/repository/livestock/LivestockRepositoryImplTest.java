package com.example.lukekramer.virtualzoo.repository.livestock;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import com.example.lukekramer.virtualzoo.domain.livestock.Livestock;
import com.example.lukekramer.virtualzoo.repository.tables.CreateTables;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Set;

/**
 * Created by lukekramer on 24/10/2016.
 */
@RunWith(AndroidJUnit4.class)
public class LivestockRepositoryImplTest {

    private static final String TAG_Livestock="livestock";
    private Long id;


    @Test
    public void livestockRepositoryCRUDTest() throws Exception {

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
        
        LivestockRepository livestockRepository = new LivestockRepositoryImpl(appContext);

        Livestock productTest = new Livestock.Builder()
                .productDescription("Cow")
                .productAge("4")
                .productGrade("A")
                .category("Livestock")
                .build();

        productTest=livestockRepository.save(productTest);
        id = productTest.getId();
        Assert.assertNotNull(TAG_Livestock+" CREATE",productTest);

        //READ (findALL)
        Set<Livestock> live = livestockRepository.findAll();
        Assert.assertTrue(TAG_Livestock+" READ ALL",live.size()>0);

        //READ (find) by id
        Livestock liveEntity = livestockRepository.findById(id);
        Assert.assertNotNull(TAG_Livestock+" READ ENTITY",liveEntity);

        //UPDATE
        Livestock updateLive = new Livestock.Builder()
                .copy(liveEntity)
                .productAge("2")
                .build();
        livestockRepository.update(updateLive);
        Livestock newLive = livestockRepository.findById(id);
        Assert.assertEquals(TAG_Livestock+ " UPDATE ENTITY","2",newLive.getAge());

        //DELETE
        livestockRepository.delete(newLive);
        Livestock deletedLive = livestockRepository.findById(id);
        Assert.assertNull(TAG_Livestock+" DELETE",deletedLive);

        //DELETE ALL
        int totalrows = livestockRepository.deleteAll();
        Assert.assertFalse(totalrows > 0);

    }
}

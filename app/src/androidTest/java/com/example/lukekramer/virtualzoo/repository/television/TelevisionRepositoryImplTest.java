package com.example.lukekramer.virtualzoo.repository.television;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import com.example.lukekramer.virtualzoo.domain.television.Television;
import com.example.lukekramer.virtualzoo.repository.tables.CreateTables;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Set;

/**
 * Created by lukekramer on 24/10/2016.
 */
@RunWith(AndroidJUnit4.class)
public class TelevisionRepositoryImplTest {

    private static final String TAG_Television="television";
    private Long id;


    @Test
    public void televisionRepositoryCRUDTest() throws Exception {

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
        TelevisionRepository televisionRepository = new TelevisionRepositoryImpl(appContext);
        
        Television productTest = new Television.Builder()
                .productDescription("Television")
                .productModel("Smart")
                .productMake("Samsung")
                .productSize("42inch")
                .category("Electronics")
                .build();

        productTest=televisionRepository.save(productTest);
        id = productTest.getId();
        Assert.assertNotNull(TAG_Television+" CREATE",productTest);

        //READ (findALL)
        Set<Television> tel = televisionRepository.findAll();
        Assert.assertTrue(TAG_Television+" READ ALL",tel.size()>0);

        //READ (find) by id
        Television telEntity = televisionRepository.findById(id);
        Assert.assertNotNull(TAG_Television+" READ ENTITY",telEntity);

        //UPDATE
        Television updateTel = new Television.Builder()
                .copy(telEntity)
                .productMake("Nokia")
                .build();
        televisionRepository.update(updateTel);
        Television newTel = televisionRepository.findById(id);
        Assert.assertEquals(TAG_Television+ " UPDATE ENTITY","Nokia",newTel.getMake());

        //DELETE
        televisionRepository.delete(newTel);
        Television deletedTel = televisionRepository.findById(id);
        Assert.assertNull(TAG_Television+" DELETE",deletedTel);

        //DELETE ALL
        int totalrows = televisionRepository.deleteAll();
        Assert.assertFalse(totalrows > 0);

    }
}

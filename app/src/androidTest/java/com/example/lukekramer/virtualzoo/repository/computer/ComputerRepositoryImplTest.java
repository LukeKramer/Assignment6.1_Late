package com.example.lukekramer.virtualzoo.repository.computer;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import com.example.lukekramer.virtualzoo.domain.computer.Computer;
import com.example.lukekramer.virtualzoo.repository.tables.CreateTables;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Set;

/**
 * Created by lukekramer on 24/10/2016.
 */
@RunWith(AndroidJUnit4.class)
public class ComputerRepositoryImplTest {

    private static final String TAG_Computer="computer";
    private Long id;


    @Test
    public void computerRepositoryCRUDTest() throws Exception {

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
        
        ComputerRepository computerRepository = new ComputerRepositoryImpl(appContext);


        Computer productTest = new Computer.Builder()
                .productDescription("Computer")
                .productType("LapTop")
                .productModel("MacBook Pro")
                .productMake("Apple")
                .productSize("50inch")
                .category("Electronics")
                .build();

        productTest=computerRepository.save(productTest);
        id = productTest.getId();
        Assert.assertNotNull(TAG_Computer+" CREATE",productTest);

        //READ (findALL)
        Set<Computer> com = computerRepository.findAll();
        Assert.assertTrue(TAG_Computer+" READ ALL",com.size()>0);

        //READ (find) by id
        Computer comEntity = computerRepository.findById(id);
        Assert.assertNotNull(TAG_Computer+" READ ENTITY",comEntity);

        //UPDATE
        Computer updateCom = new Computer.Builder()
                .copy(comEntity)
                .productMake("DELL")
                .build();
        computerRepository.update(updateCom);
        Computer newCom = computerRepository.findById(id);
        Assert.assertEquals(TAG_Computer+ " UPDATE ENTITY","DELL",newCom.getMake());

        //DELETE
        computerRepository.delete(newCom);
        Computer deletedCom = computerRepository.findById(id);
        Assert.assertNull(TAG_Computer+" DELETE",deletedCom);
        //DELETE ALL
        int totalrows = computerRepository.deleteAll();
        Assert.assertFalse(totalrows > 0);

    }
}

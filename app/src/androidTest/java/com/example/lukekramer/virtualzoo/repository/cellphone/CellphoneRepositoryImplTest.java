package com.example.lukekramer.virtualzoo.repository.cellphone;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import com.example.lukekramer.virtualzoo.domain.cellphone.Cellphone;
import com.example.lukekramer.virtualzoo.repository.tables.CreateTables;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Set;

/**
 * Created by lukekramer on 24/10/2016.
 */
@RunWith(AndroidJUnit4.class)
public class CellphoneRepositoryImplTest {


    private static final String TAG_Cellphone="cellphone";
    private Long id;


    @Test
    public void cellphoneRepositoryCRUDTest() throws Exception {

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
        
        CellphoneRepository cellphoneRepository = new CellphoneRepositoryImpl(appContext);

        Cellphone productTest = new Cellphone.Builder()
                .productDescription("2015 model")
                .productMake("Iphone")
                .productModel("S6")
                .productType("SmartPhone")
                .category("Electronics")
                .build();

        productTest=cellphoneRepository.save(productTest);
        id = productTest.getId();
        Assert.assertNotNull(TAG_Cellphone+" CREATE",productTest);

        //READ (findALL)
        Set<Cellphone> cell = cellphoneRepository.findAll();
        Assert.assertTrue(TAG_Cellphone+" READ ALL",cell.size()>0);

        //READ (find) by id
        Cellphone cellEntity = cellphoneRepository.findById(id);
        Assert.assertNotNull(TAG_Cellphone+" READ ENTITY",cellEntity);

        //UPDATE
        Cellphone updateCell = new Cellphone.Builder()
                .copy(cellEntity)
                .productMake("Samsung")
                .build();
        cellphoneRepository.update(updateCell);
        Cellphone newCell = cellphoneRepository.findById(id);
        Assert.assertEquals(TAG_Cellphone+ " UPDATE ENTITY","Samsung",newCell.getMake());

        //DELETE
        cellphoneRepository.delete(newCell);
        Cellphone deletedCell = cellphoneRepository.findById(id);
        Assert.assertNull(TAG_Cellphone+" DELETE",deletedCell);

        //DELETE ALL
        int totalrows = cellphoneRepository.deleteAll();
        Assert.assertFalse(totalrows > 0);

    }
}

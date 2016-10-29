package com.example.lukekramer.virtualzoo.repository.clothing;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import com.example.lukekramer.virtualzoo.domain.clothing.Clothing;
import com.example.lukekramer.virtualzoo.repository.tables.CreateTables;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Set;

/**
 * Created by lukekramer on 24/10/2016.
 */
@RunWith(AndroidJUnit4.class)
public class ClothingRepositoryImplTest {

    private static final String TAG_Clothing="clothing";
    private Long id;


    @Test
    public void clothingRepositoryCRUDTest() throws Exception {

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

        ClothingRepository clothingRepository = new ClothingRepositoryImpl(appContext);


        Clothing productTest = new Clothing.Builder()
                .productDescription("Coach Jacket")
                .productBrand("Sol Sol")
                .productSize("medium")
                .category("Jacket")
                .build();

        productTest=clothingRepository.save(productTest);
        id = productTest.getId();
        Assert.assertNotNull(TAG_Clothing+" CREATE",productTest);

        //READ (findALL)
        Set<Clothing> cloth = clothingRepository.findAll();
        Assert.assertTrue(TAG_Clothing+" READ ALL",cloth.size()>0);

        //READ (find) by id
        Clothing clothEntity = clothingRepository.findById(id);
        Assert.assertNotNull(TAG_Clothing+" READ ENTITY",clothEntity);

        //UPDATE
        Clothing updateCloth = new Clothing.Builder()
                .copy(clothEntity)
                .productBrand("young & lazy")
                .build();
        clothingRepository.update(updateCloth);
        Clothing newCloth = clothingRepository.findById(id);
        Assert.assertEquals(TAG_Clothing+ " UPDATE ENTITY","young & lazy",newCloth.getBrand());

        //DELETE
        clothingRepository.delete(newCloth);
        Clothing deletedCloth = clothingRepository.findById(id);
        Assert.assertNull(TAG_Clothing+" DELETE",deletedCloth);

        //DELETE ALL
        int totalrows = clothingRepository.deleteAll();
        Assert.assertFalse(totalrows > 0);

    }
}

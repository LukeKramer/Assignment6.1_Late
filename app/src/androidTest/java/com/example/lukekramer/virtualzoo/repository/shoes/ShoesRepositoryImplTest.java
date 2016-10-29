package com.example.lukekramer.virtualzoo.repository.shoes;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import com.example.lukekramer.virtualzoo.domain.shoes.Shoes;
import com.example.lukekramer.virtualzoo.repository.tables.CreateTables;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Set;

/**
 * Created by lukekramer on 24/10/2016.
 */
@RunWith(AndroidJUnit4.class)
public class ShoesRepositoryImplTest {

    private static final String TAG_Shoes="shoes";
    private Long id;


    @Test
    public void shoesRepositoryCRUDTest() throws Exception {
        
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
        ShoesRepository shoesRepository = new ShoesRepositoryImpl(appContext);

        Shoes productTest = new Shoes.Builder()
                .productDescription("Sandles")
                .productBrand("Crocs")
                .productSize("7")
                .category("shoes")
                .build();

        productTest=shoesRepository.save(productTest);
        id = productTest.getId();
        Assert.assertNotNull(TAG_Shoes+" CREATE",productTest);

        //READ (findALL)
        Set<Shoes> shoes = shoesRepository.findAll();
        Assert.assertTrue(TAG_Shoes+" READ ALL",shoes.size()>0);

        //READ (find) by id
        Shoes shoesEntity = shoesRepository.findById(id);
        Assert.assertNotNull(TAG_Shoes+" READ ENTITY",shoesEntity);

        //UPDATE
        Shoes updateShoes = new Shoes.Builder()
                .copy(shoesEntity)
                .productBrand("puma")
                .build();
        shoesRepository.update(updateShoes);
        Shoes newShoes = shoesRepository.findById(id);
        Assert.assertEquals(TAG_Shoes+ " UPDATE ENTITY","puma",newShoes.getBrand());

        //DELETE
        shoesRepository.delete(newShoes);
        Shoes deletedShoes = shoesRepository.findById(id);
        Assert.assertNull(TAG_Shoes+" DELETE",deletedShoes);

        //DELETE ALL
        int totalrows = shoesRepository.deleteAll();
        Assert.assertFalse(totalrows > 0);

    }
}

package com.example.lukekramer.virtualzoo.repository.camera;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import com.example.lukekramer.virtualzoo.domain.camera.Camera;
import com.example.lukekramer.virtualzoo.repository.tables.CreateTables;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Set;

/**
 * Created by lukekramer on 24/10/2016.
 */
@RunWith(AndroidJUnit4.class)
public class CameraRepositoryImplTest {
    private static final String TAG_Camera="camera";
    private Long id;


    @Test
    public void cameraRepositoryCRUDTest() throws Exception {

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
        
        CameraRepository cameraRepository = new CameraRepositoryImpl(appContext);


        Camera productTest = new Camera.Builder()
                .productDescription("Capture")
                .category("OutDoor")
                .productMake("Canon")
                .productModel("Z7000")
                .productSize("15")
                .productType("Digital")
                .build();


        productTest=cameraRepository.save(productTest);
        id = productTest.getId();
        Assert.assertNotNull(TAG_Camera+" CREATE",productTest);

        //READ (findALL)
        Set<Camera> camera = cameraRepository.findAll();
        Assert.assertTrue(TAG_Camera+" READ ALL",camera.size()>0);

        //READ (find) by id
        Camera cameraEntity = cameraRepository.findById(id);
        Assert.assertNotNull(TAG_Camera+" READ ENTITY",cameraEntity);

        //UPDATE
        Camera updateCamera = new Camera.Builder()
                .copy(cameraEntity)
                .productMake("Sony")
                .build();
        cameraRepository.update(updateCamera);
        Camera newCamera = cameraRepository.findById(id);
        Assert.assertEquals(TAG_Camera+ " UPDATE ENTITY","Sony",newCamera.getMake());

        //DELETE
        cameraRepository.delete(newCamera);
        Camera deletedCamera = cameraRepository.findById(id);
        Assert.assertNull(TAG_Camera+" DELETE",deletedCamera);

        //DELETE ALL
        int totalrows = cameraRepository.deleteAll();
        Assert.assertFalse(totalrows > 0);

    }
}

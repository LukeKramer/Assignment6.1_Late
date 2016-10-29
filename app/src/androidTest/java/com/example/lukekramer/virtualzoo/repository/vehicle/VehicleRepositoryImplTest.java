package com.example.lukekramer.virtualzoo.repository.vehicle;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import com.example.lukekramer.virtualzoo.domain.vehicle.Vehicle;
import com.example.lukekramer.virtualzoo.repository.tables.CreateTables;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Set;

/**
 * Created by lukekramer on 24/10/2016.
 */
@RunWith(AndroidJUnit4.class)
public class VehicleRepositoryImplTest {

    private static final String TAG_Vehicle="vehicle";
    private Long id;
    
    @Test
    public void vehicleRepositoryCRUDTest() throws Exception {

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
        
        VehicleRepository vehicleRepository = new VehicleRepositoryImpl(appContext);

        Vehicle productTest = new Vehicle.Builder()
                .productDescription("Sports Car")
                .productType("Car")
                .productMake("BMW")
                .productModel("M3")
                .category("Vehicle")
                .build();

        productTest=vehicleRepository.save(productTest);
        id = productTest.getId();
        Assert.assertNotNull(TAG_Vehicle+" CREATE",productTest);

        //READ (findALL)
        Set<Vehicle> vehicles = vehicleRepository.findAll();
        Assert.assertTrue(TAG_Vehicle+" READ ALL",vehicles.size()>0);

        //READ (find) by id
        Vehicle vehicleEntity = vehicleRepository.findById(id);
        Assert.assertNotNull(TAG_Vehicle+" READ ENTITY",vehicleEntity);

        //UPDATE
        Vehicle updateVehicle = new Vehicle.Builder()
                .copy(vehicleEntity)
                .productModel("Z8")
                .build();
        vehicleRepository.update(updateVehicle);
        Vehicle newVehicle = vehicleRepository.findById(id);
        Assert.assertEquals(TAG_Vehicle+ " UPDATE ENTITY","Z8",newVehicle.getModel());

        //DELETE
        vehicleRepository.delete(newVehicle);
        Vehicle deletedVehicle = vehicleRepository.findById(id);
        Assert.assertNull(TAG_Vehicle+" DELETE",deletedVehicle);

        //DELETE ALL
        int totalrows = vehicleRepository.deleteAll();
        Assert.assertFalse(totalrows > 0);

    }
}

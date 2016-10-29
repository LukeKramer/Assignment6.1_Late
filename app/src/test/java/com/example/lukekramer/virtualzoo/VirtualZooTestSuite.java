package com.example.lukekramer.virtualzoo;

import com.example.lukekramer.virtualzoo.domain.audio.AudioTest;
import com.example.lukekramer.virtualzoo.domain.book.BookTest;
import com.example.lukekramer.virtualzoo.domain.camera.CameraTest;
import com.example.lukekramer.virtualzoo.domain.cellphone.Cellphone;
import com.example.lukekramer.virtualzoo.domain.cellphone.CellphoneTest;
import com.example.lukekramer.virtualzoo.domain.clothing.ClothingTest;
import com.example.lukekramer.virtualzoo.domain.computer.ComputerTest;
import com.example.lukekramer.virtualzoo.domain.livestock.LivestockTest;
import com.example.lukekramer.virtualzoo.domain.shoes.ShoesTest;
import com.example.lukekramer.virtualzoo.domain.television.TelevisionTest;
import com.example.lukekramer.virtualzoo.domain.vehicle.VehicleTest;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */

@RunWith(Suite.class)
@Suite.SuiteClasses({
        AudioTest.class,
        BookTest.class,
        CameraTest.class,
        CellphoneTest.class,
        ClothingTest.class,
        ComputerTest.class,
        LivestockTest.class,
        ShoesTest.class,
        TelevisionTest.class,
        VehicleTest.class
})
public class VirtualZooTestSuite {

}
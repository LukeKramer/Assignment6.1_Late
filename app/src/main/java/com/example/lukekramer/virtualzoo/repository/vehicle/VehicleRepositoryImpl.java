package com.example.lukekramer.virtualzoo.repository.vehicle;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.lukekramer.virtualzoo.database.DBConstants;
import com.example.lukekramer.virtualzoo.domain.vehicle.Vehicle;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by lukekramer on 24/10/2016.
 */

public class VehicleRepositoryImpl extends SQLiteOpenHelper implements VehicleRepository {

    public static final String TABLE_Vehicle = "vehicle";
    private SQLiteDatabase db;

    public static final String V_ID = "id";
    public static final String V_Description = "description";
    public static final String V_Make = "make";
    public static final String V_Model = "model";
    public static final String V_Type = "type";
    public static final String V_CATAGORY = "catagory";

    private static final String DATABASE_CREATE_Vehicle = " CREATE TABLE "
            + TABLE_Vehicle + "("
            + V_ID + " INTEGER  PRIMARY KEY AUTOINCREMENT, "
            + V_Description + " TEXT NOT NULL , "
            + V_Make + " TEXT NOT NULL , "
            + V_Model + " TEXT NOT NULL , "
            + V_Type+ " TEXT NOT NULL , "
            + V_CATAGORY + " TEXT NOT NULL);";

    public VehicleRepositoryImpl(Context context) {
        super(context, DBConstants.DATABASE_NAME, null, DBConstants.DATABASE_VERSION);
    }

    public void open() throws SQLException {
        db = this.getWritableDatabase();
    }

    public void close() {
        this.close();
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    @Override
    public Vehicle findById(Long id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(
                TABLE_Vehicle,
                new String[]{
                        V_ID,
                        V_Description,
                        V_Make,
                        V_Model,
                        V_Type,
                        V_CATAGORY},
                V_ID + " =? ",
                new String[]{String.valueOf(id)},
                null,
                null,
                null,
                null);
        if (cursor.moveToFirst()) {
            final Vehicle result = new Vehicle.Builder()
                    .id(cursor.getLong(0))
                    .productDescription(cursor.getString(1))
                    .productMake(cursor.getString(2))
                    .productModel(cursor.getString(3))
                    .productType(cursor.getString(4))
                    .category(cursor.getString(5))
                    .build();

            return result;
        } else {
            return null;
        }
    }

    @Override
    public Vehicle save(Vehicle entity) {
        open();
        ContentValues values = new ContentValues();
        values.put(V_ID, entity.getId());
        values.put(V_Description, entity.getDescription());
        values.put(V_Make, entity.getMake());
        values.put(V_Model, entity.getModel());
        values.put(V_Type, entity.getVehicletype());
        values.put(V_CATAGORY, entity.getCategory());
        long id = db.insertOrThrow(TABLE_Vehicle, null, values);
        Vehicle insertedEntity = new Vehicle.Builder()
                .copy(entity)
                .id(new Long(id))
                .build();
        return insertedEntity;
    }

    @Override
    public Vehicle update(Vehicle entity) {
        open();
        ContentValues values = new ContentValues();
        values.put(V_ID, entity.getId());
        values.put(V_Description, entity.getDescription());
        values.put(V_Make, entity.getMake());
        values.put(V_Model, entity.getModel());
        values.put(V_Type, entity.getVehicletype());
        values.put(V_CATAGORY, entity.getCategory());
        db.update(
                TABLE_Vehicle,
                values,
                 V_ID + " =? ",
                new String[]{String.valueOf(entity.getId())}
        );
        return entity;
    }

    @Override
    public Vehicle delete(Vehicle entity) {
        open();
        db.delete(
                TABLE_Vehicle,
                V_ID + " =? ",
                new String[]{String.valueOf(entity.getId())});
        return entity;
    }

    @Override
    public Set<Vehicle> findAll() {
        SQLiteDatabase db = this.getReadableDatabase();
        String selectAll = " SELECT * FROM " + TABLE_Vehicle;
        Set<Vehicle> settings = new HashSet<>();
        open();
        Cursor cursor = db.rawQuery(selectAll, null);
        if (cursor.moveToFirst()) {
            do {
                final Vehicle result = new Vehicle.Builder()
                        .id(cursor.getLong(0))
                        .productDescription(cursor.getString(1))
                        .productMake(cursor.getString(2))
                        .productModel(cursor.getString(3))
                        .productType(cursor.getString(4))
                        .category(cursor.getString(5))
                        .build();
                settings.add(result);
            } while (cursor.moveToNext());
        }

        return settings;
    }

    @Override
    public int deleteAll() {
        open();
        int rowsDeleted = db.delete(TABLE_Vehicle,null,null);
        //close();
        return rowsDeleted;
    }
}

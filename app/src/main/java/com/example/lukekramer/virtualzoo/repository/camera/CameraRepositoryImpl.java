package com.example.lukekramer.virtualzoo.repository.camera;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.lukekramer.virtualzoo.database.DBConstants;
import com.example.lukekramer.virtualzoo.domain.camera.Camera;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by lukekramer on 24/10/2016.
 */

public class CameraRepositoryImpl extends SQLiteOpenHelper implements CameraRepository {

    public static final String TABLE_Camera = "camera";
    private SQLiteDatabase db;

    public static final String Camera_ID = "id";
    public static final String Camera_Description = "description";
    public static final String Camera_Make = "make";
    public static final String Camera_Model = "model";
    public static final String Camera_Size = "size";
    public static final String Camera_Type = "type";
    public static final String Camera_CATAGORY = "catagory";

    private static final String DATABASE_CREATE_Camera = " CREATE TABLE "
            + TABLE_Camera + "("
            + Camera_ID + " INTEGER  PRIMARY KEY AUTOINCREMENT, "
            + Camera_Description + " TEXT NOT NULL , "
            + Camera_Make + " TEXT NOT NULL , "
            + Camera_Model + " TEXT NOT NULL , "
            + Camera_Size + " TEXT NOT NULL , "
            + Camera_Type + " TEXT NOT NULL , "
            + Camera_CATAGORY + " TEXT NOT NULL);";




    public CameraRepositoryImpl(Context context) {
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
    public Camera findById(Long id) {

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(
                TABLE_Camera,
                new String[]{
                        Camera_ID,
                        Camera_Description,
                        Camera_Make,
                        Camera_Model,
                        Camera_Size,
                        Camera_Type,
                        Camera_CATAGORY},
                Camera_ID + " =? ",
                new String[]{String.valueOf(id)},
                null,
                null,
                null,
                null);
        if (cursor.moveToFirst()) {
            final Camera result = new Camera.Builder()
                    .id(cursor.getLong(0))
                    .productDescription(cursor.getString(1))
                    .productMake(cursor.getString(2))
                    .productModel(cursor.getString(3))
                    .productSize(cursor.getString(4))
                    .productType(cursor.getString(5))
                    .category(cursor.getString(6))
                    .build();

            return result;
        } else {
            return null;
        }
    }

    @Override
    public Camera save(Camera entity) {

        open();
        ContentValues values = new ContentValues();
        values.put(Camera_ID, entity.getId());
        values.put(Camera_Description, entity.getDescription());
        values.put(Camera_Make, entity.getMake());
        values.put(Camera_Model, entity.getModel());
        values.put(Camera_Size, entity.getSize());
        values.put(Camera_Type, entity.getType());
        values.put(Camera_CATAGORY, entity.getCategory());
        long id = db.insertOrThrow(TABLE_Camera, null, values);
        Camera insertedEntity = new Camera.Builder()
                .copy(entity)
                .id(new Long(id))
                .build();
        return insertedEntity;

    }

    @Override
    public Camera update(Camera entity) {
        open();
        ContentValues values = new ContentValues();
        values.put(Camera_ID, entity.getId());
        values.put(Camera_Description, entity.getDescription());
        values.put(Camera_Make, entity.getMake());
        values.put(Camera_Model, entity.getModel());
        values.put(Camera_Size, entity.getSize());
        values.put(Camera_Type, entity.getType());
        values.put(Camera_CATAGORY, entity.getCategory());
        db.update(
                TABLE_Camera,
                values,
                Camera_ID + " =? ",
                new String[]{String.valueOf(entity.getId())}
        );
        return entity;
    }

    @Override
    public Camera delete(Camera entity) {
        open();
        db.delete(
                TABLE_Camera,
                Camera_ID + " =? ",
                new String[]{String.valueOf(entity.getId())});
        return entity;
    }

    @Override
    public Set<Camera> findAll() {
        SQLiteDatabase db = this.getReadableDatabase();
        String selectAll = " SELECT * FROM " + TABLE_Camera;
        Set<Camera> settings = new HashSet<>();
        open();
        Cursor cursor = db.rawQuery(selectAll, null);
        if (cursor.moveToFirst()) {
            do {
                final Camera result = new Camera.Builder()
                        .id(cursor.getLong(0))
                        .productDescription(cursor.getString(1))
                        .productMake(cursor.getString(2))
                        .productModel(cursor.getString(3))
                        .productSize(cursor.getString(4))
                        .productType(cursor.getString(5))
                        .category(cursor.getString(6))
                        .build();
                settings.add(result);
            } while (cursor.moveToNext());
        }

        return settings;
    }

    @Override
    public int deleteAll() {
        open();
        int rowsDeleted = db.delete(TABLE_Camera,null,null);
        //close();
        return rowsDeleted;
    }
}

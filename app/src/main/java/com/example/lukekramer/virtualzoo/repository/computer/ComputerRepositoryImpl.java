package com.example.lukekramer.virtualzoo.repository.computer;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.lukekramer.virtualzoo.database.DBConstants;
import com.example.lukekramer.virtualzoo.domain.computer.Computer;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by lukekramer on 24/10/2016.
 */

public class ComputerRepositoryImpl extends SQLiteOpenHelper implements ComputerRepository {
    public static final String TABLE_Computer = "computer";
    private SQLiteDatabase db;

    public static final String Com_ID = "id";
    public static final String Com_Description = "description";
    public static final String Com_Make = "make";
    public static final String Com_Model = "model";
    public static final String Com_Size = "size";
    public static final String Com_Type = "type";
    public static final String Com_CATAGORY = "catagory";

    private static final String DATABASE_CREATE_Computer = " CREATE TABLE "
            + TABLE_Computer + "("
            + Com_ID + " INTEGER  PRIMARY KEY AUTOINCREMENT, "
            + Com_Description + " TEXT NOT NULL , "
            + Com_Make + " TEXT NOT NULL , "
            + Com_Model + " TEXT NOT NULL , "
            + Com_Size + " TEXT NOT NULL , "
            + Com_Type + " TEXT NOT NULL , "
            + Com_CATAGORY + " TEXT NOT NULL);";




    public ComputerRepositoryImpl(Context context) {
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
    public Computer findById(Long id) {

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(
                TABLE_Computer,
                new String[]{
                        Com_ID,
                        Com_Description,
                        Com_Make,
                        Com_Model,
                        Com_Size,
                        Com_Type,
                        Com_CATAGORY},
                Com_ID + " =? ",
                new String[]{String.valueOf(id)},
                null,
                null,
                null,
                null);
        if (cursor.moveToFirst()) {
            final Computer result = new Computer.Builder()
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
    public Computer save(Computer entity) {
        open();
        ContentValues values = new ContentValues();
        values.put(Com_ID, entity.getId());
        values.put(Com_Description, entity.getDescription());
        values.put(Com_Make, entity.getMake());
        values.put(Com_Model, entity.getModel());
        values.put(Com_Size, entity.getSize());
        values.put(Com_Type, entity.getType());
        values.put(Com_CATAGORY, entity.getCategory());
        long id = db.insertOrThrow(TABLE_Computer, null, values);
        Computer insertedEntity = new Computer.Builder()
                .copy(entity)
                .id(new Long(id))
                .build();
        return insertedEntity;

    }

    @Override
    public Computer update(Computer entity) {
        open();
        ContentValues values = new ContentValues();
        values.put(Com_ID, entity.getId());
        values.put(Com_Description, entity.getDescription());
        values.put(Com_Make, entity.getMake());
        values.put(Com_Model, entity.getModel());
        values.put(Com_Size, entity.getSize());
        values.put(Com_Type, entity.getType());
        values.put(Com_CATAGORY, entity.getCategory());
        db.update(
                TABLE_Computer,
                values,
                Com_ID + " =? ",
                new String[]{String.valueOf(entity.getId())}
        );
        return entity;
    }

    @Override
    public Computer delete(Computer entity) {
        open();
        db.delete(
                TABLE_Computer,
                Com_ID + " =? ",
                new String[]{String.valueOf(entity.getId())});
        return entity;
    }

    @Override
    public Set<Computer> findAll() {
        SQLiteDatabase db = this.getReadableDatabase();
        String selectAll = " SELECT * FROM " + TABLE_Computer;
        Set<Computer> settings = new HashSet<>();
        open();
        Cursor cursor = db.rawQuery(selectAll, null);
        if (cursor.moveToFirst()) {
            do {
                final Computer result = new Computer.Builder()
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
        int rowsDeleted = db.delete(TABLE_Computer,null,null);
        //close();
        return rowsDeleted;
    }
}

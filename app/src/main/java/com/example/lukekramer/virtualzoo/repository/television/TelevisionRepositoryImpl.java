package com.example.lukekramer.virtualzoo.repository.television;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.lukekramer.virtualzoo.database.DBConstants;
import com.example.lukekramer.virtualzoo.domain.television.Television;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by lukekramer on 24/10/2016.
 */

public class TelevisionRepositoryImpl extends SQLiteOpenHelper implements TelevisionRepository {

    public static final String TABLE_Television = "television";
    private SQLiteDatabase db;

    public static final String Tel_ID = "id";
    public static final String Tel_Description = "description";
    public static final String Tel_Make = "make";
    public static final String Tel_Model = "model";
    public static final String Tel_Size = "size";
    public static final String Tel_CATAGORY = "catagory";

    private static final String DATABASE_CREATE_Television = " CREATE TABLE "
            + TABLE_Television + "("
            + Tel_ID + " INTEGER  PRIMARY KEY AUTOINCREMENT, "
            + Tel_Description + " TEXT NOT NULL , "
            + Tel_Make + " TEXT NOT NULL , "
            + Tel_Model + " TEXT NOT NULL , "
            + Tel_Size + " TEXT NOT NULL , "
            + Tel_CATAGORY + " TEXT NOT NULL);";


    public TelevisionRepositoryImpl(Context context) {
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
    public Television findById(Long id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(
                TABLE_Television,
                new String[]{
                        Tel_ID,
                        Tel_Description,
                        Tel_Make,
                        Tel_Model,
                        Tel_Size,
                        Tel_CATAGORY},
                Tel_ID + " =? ",
                new String[]{String.valueOf(id)},
                null,
                null,
                null,
                null);
        if (cursor.moveToFirst()) {
            final Television result = new Television.Builder()
                    .id(cursor.getLong(0))
                    .productDescription(cursor.getString(1))
                    .productMake(cursor.getString(2))
                    .productModel(cursor.getString(3))
                    .productSize(cursor.getString(4))
                    .category(cursor.getString(5))
                    .build();

            return result;
        } else {
            return null;
        }
    }

    @Override
    public Television save(Television entity) {
        open();
        ContentValues values = new ContentValues();
        values.put(Tel_ID, entity.getId());
        values.put(Tel_Description, entity.getDescription());
        values.put(Tel_Make, entity.getMake());
        values.put(Tel_Model, entity.getModel());
        values.put(Tel_Size, entity.getSize());
        values.put(Tel_CATAGORY, entity.getCategory());
        long id = db.insertOrThrow(TABLE_Television, null, values);
        Television insertedEntity = new Television.Builder()
                .copy(entity)
                .id(new Long(id))
                .build();
        return insertedEntity;
    }

    @Override
    public Television update(Television entity) {
        open();
        ContentValues values = new ContentValues();
        values.put(Tel_ID, entity.getId());
        values.put(Tel_Description, entity.getDescription());
        values.put(Tel_Make, entity.getMake());
        values.put(Tel_Model, entity.getModel());
        values.put(Tel_Size, entity.getSize());
        values.put(Tel_CATAGORY, entity.getCategory());
        db.update(
                TABLE_Television,
                values,
                Tel_ID + " =? ",
                new String[]{String.valueOf(entity.getId())}
        );
        return entity;
    }

    @Override
    public Television delete(Television entity) {
        open();
        db.delete(
                TABLE_Television,
                Tel_ID + " =? ",
                new String[]{String.valueOf(entity.getId())});
        return entity;
    }

    @Override
    public Set<Television> findAll() {
        SQLiteDatabase db = this.getReadableDatabase();
        String selectAll = " SELECT * FROM " + TABLE_Television;
        Set<Television> settings = new HashSet<>();
        open();
        Cursor cursor = db.rawQuery(selectAll, null);
        if (cursor.moveToFirst()) {
            do {
                final Television result = new Television.Builder()
                        .id(cursor.getLong(0))
                        .productDescription(cursor.getString(1))
                        .productMake(cursor.getString(2))
                        .productModel(cursor.getString(3))
                        .productSize(cursor.getString(4))
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
        int rowsDeleted = db.delete(TABLE_Television,null,null);
        //close();
        return rowsDeleted;
    }
}

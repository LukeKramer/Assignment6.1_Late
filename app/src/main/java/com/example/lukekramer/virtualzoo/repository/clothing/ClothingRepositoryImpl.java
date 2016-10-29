package com.example.lukekramer.virtualzoo.repository.clothing;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.lukekramer.virtualzoo.database.DBConstants;
import com.example.lukekramer.virtualzoo.domain.clothing.Clothing;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by lukekramer on 24/10/2016.
 */

public class ClothingRepositoryImpl extends SQLiteOpenHelper implements ClothingRepository {

    public static final String TABLE_Clothing = "clothing";
    private SQLiteDatabase db;

    public static final String Clothing_ID = "id";
    public static final String Clothing_Description = "description";
    public static final String Clothing_Brand = "brand";
    public static final String Clothing_Size = "size";
    public static final String Clothing_CATAGORY = "catagory";

    private static final String DATABASE_CREATE_Clothing = " CREATE TABLE "
            + TABLE_Clothing + "("
            + Clothing_ID + " INTEGER  PRIMARY KEY AUTOINCREMENT, "
            + Clothing_Description + " TEXT NOT NULL , "
            + Clothing_Brand + " TEXT NOT NULL , "
            + Clothing_Size + " TEXT NOT NULL , "
            + Clothing_CATAGORY + " TEXT NOT NULL);";




    public ClothingRepositoryImpl(Context context) {
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
    public Clothing findById(Long id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(
                TABLE_Clothing,
                new String[]{
                        Clothing_ID,
                        Clothing_Description,
                        Clothing_Brand,
                        Clothing_Size,
                        Clothing_CATAGORY},
                Clothing_ID + " =? ",
                new String[]{String.valueOf(id)},
                null,
                null,
                null,
                null);
        if (cursor.moveToFirst()) {
            final Clothing result = new Clothing.Builder()
                    .id(cursor.getLong(0))
                    .productDescription(cursor.getString(1))
                    .productBrand(cursor.getString(2))
                    .productSize(cursor.getString(3))
                    .category(cursor.getString(4))
                    .build();

            return result;
        } else {
            return null;
        }
    }

    @Override
    public Clothing save(Clothing entity) {

        open();
        ContentValues values = new ContentValues();
        values.put(Clothing_ID, entity.getId());
        values.put(Clothing_Description, entity.getDescription());
        values.put(Clothing_Brand, entity.getBrand());
        values.put(Clothing_Size, entity.getSize());
        values.put(Clothing_CATAGORY, entity.getCategory());
        long id = db.insertOrThrow(TABLE_Clothing, null, values);
        Clothing insertedEntity = new Clothing.Builder()
                .copy(entity)
                .id(new Long(id))
                .build();
        return insertedEntity;
    }

    @Override
    public Clothing update(Clothing entity) {
        open();
        ContentValues values = new ContentValues();
        values.put(Clothing_ID, entity.getId());
        values.put(Clothing_Description, entity.getDescription());
        values.put(Clothing_Brand, entity.getBrand());
        values.put(Clothing_Size, entity.getSize());
        values.put(Clothing_CATAGORY, entity.getCategory());
        db.update(
                TABLE_Clothing,
                values,
                Clothing_ID + " =? ",
                new String[]{String.valueOf(entity.getId())}
        );
        return entity;
    }

    @Override
    public Clothing delete(Clothing entity) {
        open();
        db.delete(
                TABLE_Clothing,
                Clothing_ID + " =? ",
                new String[]{String.valueOf(entity.getId())});
        return entity;
    }

    @Override
    public Set<Clothing> findAll() {
        SQLiteDatabase db = this.getReadableDatabase();
        String selectAll = " SELECT * FROM " + TABLE_Clothing;
        Set<Clothing> settings = new HashSet<>();
        open();
        Cursor cursor = db.rawQuery(selectAll, null);
        if (cursor.moveToFirst()) {
            do {
                final Clothing result = new Clothing.Builder()
                        .id(cursor.getLong(0))
                        .productDescription(cursor.getString(1))
                        .productBrand(cursor.getString(2))
                        .productSize(cursor.getString(3))
                        .category(cursor.getString(4))
                        .build();
                settings.add(result);
            } while (cursor.moveToNext());
        }

        return settings;
    }

    @Override
    public int deleteAll() {
        open();
        int rowsDeleted = db.delete(TABLE_Clothing,null,null);
        //close();
        return rowsDeleted;
    }
}

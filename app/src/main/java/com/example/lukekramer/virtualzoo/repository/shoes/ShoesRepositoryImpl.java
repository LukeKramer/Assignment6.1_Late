package com.example.lukekramer.virtualzoo.repository.shoes;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.lukekramer.virtualzoo.database.DBConstants;
import com.example.lukekramer.virtualzoo.domain.shoes.Shoes;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by lukekramer on 24/10/2016.
 */

public class ShoesRepositoryImpl extends SQLiteOpenHelper implements ShoesRepository {

    public static final String TABLE_Shoes = "shoes";
    private SQLiteDatabase db;

    public static final String Shoes_ID = "id";
    public static final String Shoes_Description = "description";
    public static final String Shoes_Brand = "brand";
    public static final String Shoes_Size = "size";
    public static final String Shoes_CATAGORY = "catagory";

    private static final String DATABASE_CREATE_Shoes = " CREATE TABLE "
            + TABLE_Shoes + "("
            + Shoes_ID + " INTEGER  PRIMARY KEY AUTOINCREMENT, "
            + Shoes_Description + " TEXT NOT NULL , "
            + Shoes_Brand + " TEXT NOT NULL , "
            + Shoes_Size + " TEXT NOT NULL , "
            + Shoes_CATAGORY + " TEXT NOT NULL);";




    public ShoesRepositoryImpl(Context context) {
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
    public Shoes findById(Long id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(
                TABLE_Shoes,
                new String[]{
                        Shoes_ID,
                        Shoes_Description,
                        Shoes_Brand,
                        Shoes_Size,
                        Shoes_CATAGORY},
                Shoes_ID + " =? ",
                new String[]{String.valueOf(id)},
                null,
                null,
                null,
                null);
        if (cursor.moveToFirst()) {
            final Shoes result = new Shoes.Builder()
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
    public Shoes save(Shoes entity) {
        open();
        ContentValues values = new ContentValues();
        values.put(Shoes_ID, entity.getId());
        values.put(Shoes_Description, entity.getDescription());
        values.put(Shoes_Brand, entity.getBrand());
        values.put(Shoes_Size, entity.getSize());
        values.put(Shoes_CATAGORY, entity.getCategory());
        long id = db.insertOrThrow(TABLE_Shoes, null, values);
        Shoes insertedEntity = new Shoes.Builder()
                .copy(entity)
                .id(new Long(id))
                .build();
        return insertedEntity;
    }

    @Override
    public Shoes update(Shoes entity) {
        open();
        ContentValues values = new ContentValues();
        values.put(Shoes_ID, entity.getId());
        values.put(Shoes_Description, entity.getDescription());
        values.put(Shoes_Brand, entity.getBrand());
        values.put(Shoes_Size, entity.getSize());
        values.put(Shoes_CATAGORY, entity.getCategory());
        db.update(
                TABLE_Shoes,
                values,
                Shoes_ID + " =? ",
                new String[]{String.valueOf(entity.getId())}
        );
        return entity;
    }

    @Override
    public Shoes delete(Shoes entity) {
        open();
        db.delete(
                TABLE_Shoes,
                Shoes_ID + " =? ",
                new String[]{String.valueOf(entity.getId())});
        return entity;
    }

    @Override
    public Set<Shoes> findAll() {
        SQLiteDatabase db = this.getReadableDatabase();
        String selectAll = " SELECT * FROM " + TABLE_Shoes;
        Set<Shoes> settings = new HashSet<>();
        open();
        Cursor cursor = db.rawQuery(selectAll, null);
        if (cursor.moveToFirst()) {
            do {
                final Shoes result = new Shoes.Builder()
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
        int rowsDeleted = db.delete(TABLE_Shoes,null,null);
        //close();
        return rowsDeleted;
    }
}

package com.example.lukekramer.virtualzoo.repository.cellphone;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.lukekramer.virtualzoo.database.DBConstants;
import com.example.lukekramer.virtualzoo.domain.cellphone.Cellphone;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by lukekramer on 24/10/2016.
 */

public class CellphoneRepositoryImpl extends SQLiteOpenHelper implements CellphoneRepository {

    public static final String TABLE_Cellphone = "cellphone";
    private SQLiteDatabase db;

    public static final String Cellphone_ID = "id";
    public static final String Cellphone_Description = "description";
    public static final String Cellphone_Make = "make";
    public static final String Cellphone_Model = "model";
    public static final String Cellphone_Type = "type";
    public static final String Cellphone_CATAGORY = "catagory";

    private static final String DATABASE_CREATE_Cellphone = " CREATE TABLE "
            + TABLE_Cellphone + "("
            + Cellphone_ID + " INTEGER  PRIMARY KEY AUTOINCREMENT, "
            + Cellphone_Description + " TEXT NOT NULL , "
            + Cellphone_Make + " TEXT NOT NULL , "
            + Cellphone_Model + " TEXT NOT NULL , "
            + Cellphone_Type + " TEXT NOT NULL , "
            + Cellphone_CATAGORY + " TEXT NOT NULL);";




    public CellphoneRepositoryImpl(Context context) {
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
    public Cellphone findById(Long id) {

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(
                TABLE_Cellphone,
                new String[]{
                        Cellphone_ID,
                        Cellphone_Description,
                        Cellphone_Make,
                        Cellphone_Model,
                        Cellphone_Type,
                        Cellphone_CATAGORY},
                Cellphone_ID + " =? ",
                new String[]{String.valueOf(id)},
                null,
                null,
                null,
                null);
        if (cursor.moveToFirst()) {
            final Cellphone result = new Cellphone.Builder()
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
    public Cellphone save(Cellphone entity) {

        open();
        ContentValues values = new ContentValues();
        values.put(Cellphone_ID, entity.getId());
        values.put(Cellphone_Description, entity.getDescription());
        values.put(Cellphone_Make, entity.getMake());
        values.put(Cellphone_Model, entity.getModel());
        values.put(Cellphone_Type, entity.getType());
        values.put(Cellphone_CATAGORY, entity.getCategory());
        long id = db.insertOrThrow(TABLE_Cellphone, null, values);
        Cellphone insertedEntity = new Cellphone.Builder()
                .copy(entity)
                .id(new Long(id))
                .build();
        return insertedEntity;
    }

    @Override
    public Cellphone update(Cellphone entity) {
        open();
        ContentValues values = new ContentValues();
        values.put(Cellphone_ID, entity.getId());
        values.put(Cellphone_Description, entity.getDescription());
        values.put(Cellphone_Make, entity.getMake());
        values.put(Cellphone_Model, entity.getModel());
        values.put(Cellphone_Type, entity.getType());
        values.put(Cellphone_CATAGORY, entity.getCategory());
        db.update(
                TABLE_Cellphone,
                values,
                Cellphone_ID + " =? ",
                new String[]{String.valueOf(entity.getId())}
        );
        return entity;
    }

    @Override
    public Cellphone delete(Cellphone entity) {
        open();
        db.delete(
                TABLE_Cellphone,
                Cellphone_ID + " =? ",
                new String[]{String.valueOf(entity.getId())});
        return entity;
    }

    @Override
    public Set<Cellphone> findAll() {
        SQLiteDatabase db = this.getReadableDatabase();
        String selectAll = " SELECT * FROM " + TABLE_Cellphone;
        Set<Cellphone> settings = new HashSet<>();
        open();
        Cursor cursor = db.rawQuery(selectAll, null);
        if (cursor.moveToFirst()) {
            do {
                final Cellphone result = new Cellphone.Builder()
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
        int rowsDeleted = db.delete(TABLE_Cellphone,null,null);
        //close();
        return rowsDeleted;
    }
}

package com.example.lukekramer.virtualzoo.repository.livestock;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.lukekramer.virtualzoo.database.DBConstants;
import com.example.lukekramer.virtualzoo.domain.livestock.Livestock;


import java.util.HashSet;
import java.util.Set;

/**
 * Created by lukekramer on 24/10/2016.
 */

public class LivestockRepositoryImpl extends SQLiteOpenHelper implements LivestockRepository {

    public static final String TABLE_Livestock = "livestock";
    private SQLiteDatabase db;

    public static final String Live_ID = "id";
    public static final String Live_Description = "description";
    public static final String Live_Grade = "grade";
    public static final String Live_Age = "age";
    public static final String Live_CATAGORY = "catagory";

    private static final String DATABASE_CREATE_Livestock = " CREATE TABLE "
            + TABLE_Livestock + "("
            + Live_ID + " INTEGER  PRIMARY KEY AUTOINCREMENT, "
            + Live_Description + " TEXT NOT NULL , "
            + Live_Grade + " TEXT NOT NULL , "
            + Live_Age + " TEXT NOT NULL , "
            + Live_CATAGORY + " TEXT NOT NULL);";


    public LivestockRepositoryImpl(Context context) {
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
    public Livestock findById(Long id) {

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(
                TABLE_Livestock,
                new String[]{
                        Live_ID,
                        Live_Description,
                        Live_Grade,
                        Live_Age,
                        Live_CATAGORY},
                Live_ID + " =? ",
                new String[]{String.valueOf(id)},
                null,
                null,
                null,
                null);
        if (cursor.moveToFirst()) {
            final Livestock result = new Livestock.Builder()
                    .id(cursor.getLong(0))
                    .productDescription(cursor.getString(1))
                    .productGrade(cursor.getString(2))
                    .productAge(cursor.getString(3))
                    .category(cursor.getString(4))
                    .build();

            return result;
        } else {
            return null;
        }
    }

    @Override
    public Livestock save(Livestock entity) {
        open();
        ContentValues values = new ContentValues();
        values.put(Live_ID, entity.getId());
        values.put(Live_Description, entity.getDescription());
        values.put(Live_Grade, entity.getGrade());
        values.put(Live_Age, entity.getAge());
        values.put(Live_CATAGORY, entity.getCategory());
        long id = db.insertOrThrow(TABLE_Livestock, null, values);
        Livestock insertedEntity = new Livestock.Builder()
                .copy(entity)
                .id(new Long(id))
                .build();
        return insertedEntity;
    }

    @Override
    public Livestock update(Livestock entity) {
        open();
        ContentValues values = new ContentValues();
        values.put(Live_ID, entity.getId());
        values.put(Live_Description, entity.getDescription());
        values.put(Live_Grade, entity.getGrade());
        values.put(Live_Age, entity.getAge());
        values.put(Live_CATAGORY, entity.getCategory());
        db.update(
                TABLE_Livestock,
                values,
                Live_ID + " =? ",
                new String[]{String.valueOf(entity.getId())}
        );
        return entity;
    }

    @Override
    public Livestock delete(Livestock entity) {
        open();
        db.delete(
                TABLE_Livestock,
                Live_ID + " =? ",
                new String[]{String.valueOf(entity.getId())});
        return entity;
    }

    @Override
    public Set<Livestock> findAll() {
        SQLiteDatabase db = this.getReadableDatabase();
        String selectAll = " SELECT * FROM " + TABLE_Livestock;
        Set<Livestock> settings = new HashSet<>();
        open();
        Cursor cursor = db.rawQuery(selectAll, null);
        if (cursor.moveToFirst()) {
            do {
                final Livestock result = new Livestock.Builder()
                        .id(cursor.getLong(0))
                        .productDescription(cursor.getString(1))
                        .productGrade(cursor.getString(2))
                        .productAge(cursor.getString(3))
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
        int rowsDeleted = db.delete(TABLE_Livestock,null,null);
        //close();
        return rowsDeleted;
    }
}

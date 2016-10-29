package com.example.lukekramer.virtualzoo.repository.audio;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.lukekramer.virtualzoo.database.DBConstants;
import com.example.lukekramer.virtualzoo.domain.audio.Audio;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by lukekramer on 23/10/2016.
 */

public class AudioRepositoryImpl extends SQLiteOpenHelper implements AudioRepository{

    public static final String TABLE_Audio = "audio";
    private SQLiteDatabase db;

    public static final String Audio_ID = "id";
    public static final String Audio_Description = "description";
    public static final String Audio_MAKE = "make";
    public static final String Audio_WATTS = "watts";
    public static final String Audio_AMPS = "amps";
    public static final String Audio_CATAGORY = "catagory";

    private static final String DATABASE_CREATE_Audio = " CREATE TABLE "
            + TABLE_Audio + "("
            + Audio_ID + " INTEGER  PRIMARY KEY AUTOINCREMENT, "
            + Audio_Description + " TEXT NOT NULL , "
            + Audio_MAKE + " TEXT NOT NULL , "
            + Audio_WATTS + " TEXT NOT NULL , "
            + Audio_AMPS + " TEXT NOT NULL , "
            + Audio_CATAGORY + " TEXT NOT NULL);";



    public AudioRepositoryImpl(Context context) {
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
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        Log.w(this.getClass().getName(),
                "Upgrading database from version " + oldVersion + " to "
                        + newVersion + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_Audio);
        onCreate(db);

    }

    @Override
    public Audio findById(Long id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(
                TABLE_Audio,
                new String[]{
                        Audio_ID,
                        Audio_Description,
                        Audio_MAKE,
                        Audio_WATTS,
                        Audio_AMPS,
                        Audio_CATAGORY},
                Audio_ID + " =? ",
                new String[]{String.valueOf(id)},
                null,
                null,
                null,
                null);
        if (cursor.moveToFirst()) {
            final Audio result = new Audio.Builder()
                    .id(cursor.getLong(0))
                    .productDescription(cursor.getString(1))
                    .productMake(cursor.getString(2))
                    .productWatts(cursor.getString(3))
                    .productAmps(cursor.getString(4))
                    .category(cursor.getString(5))
                    .build();

            return result;
        } else {
            return null;
        }

    }

    @Override
    public Audio save(Audio entity) {
        open();
        ContentValues values = new ContentValues();
        values.put(Audio_ID, entity.getId());
        values.put(Audio_Description, entity.getDescription());
        values.put(Audio_MAKE, entity.getMake());
        values.put(Audio_WATTS, entity.getWatts());
        values.put(Audio_AMPS, entity.getAmps());
        values.put(Audio_CATAGORY, entity.getCategory());
        long id = db.insertOrThrow(TABLE_Audio, null, values);
        Audio insertedEntity = new Audio.Builder()
                .copy(entity)
                .id(new Long(id))
                .build();
        return insertedEntity;
    }

    @Override
    public Audio update(Audio entity) {
        open();
        ContentValues values = new ContentValues();
        values.put(Audio_ID, entity.getId());
        values.put(Audio_Description, entity.getDescription());
        values.put(Audio_MAKE, entity.getMake());
        values.put(Audio_WATTS, entity.getWatts());
        values.put(Audio_AMPS, entity.getAmps());
        values.put(Audio_CATAGORY, entity.getCategory());
        db.update(
                TABLE_Audio,
                values,
                Audio_ID + " =? ",
                new String[]{String.valueOf(entity.getId())}
        );
        return entity;
    }

    @Override
    public Audio delete(Audio entity) {
        open();
        db.delete(
                TABLE_Audio,
                Audio_ID + " =? ",
                new String[]{String.valueOf(entity.getId())});
        return entity;
    }

    @Override
    public Set<Audio> findAll() {

        SQLiteDatabase db = this.getReadableDatabase();
        String selectAll = " SELECT * FROM " + TABLE_Audio;
        Set<Audio> settings = new HashSet<>();
        open();
        Cursor cursor = db.rawQuery(selectAll, null);
        if (cursor.moveToFirst()) {
            do {
                final Audio result = new Audio.Builder()
                        .id(cursor.getLong(0))
                        .productDescription(cursor.getString(1))
                        .productMake(cursor.getString(2))
                        .productWatts(cursor.getString(3))
                        .productAmps(cursor.getString(4))
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
        int rowsDeleted = db.delete(TABLE_Audio,null,null);
        //close();
        return rowsDeleted;
    }
}

package com.example.lukekramer.virtualzoo.repository.tables;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.lukekramer.virtualzoo.database.DBConstants;

/**
 * Created by lukekramer on 23/10/2016.
 */

public class CreateTables extends SQLiteOpenHelper {

    //audio
    public static final String TABLE_Audio = "audio";

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
    //book
    public static final String TABLE_Book = "book";

    public static final String Book_ID = "id";
    public static final String Book_Description = "description";
    public static final String Book_Author = "author";
    public static final String Book_Title = "title";
    public static final String Book_Isbn = "isbn";
    public static final String Book_CATAGORY = "catagory";

    private static final String DATABASE_CREATE_Book = " CREATE TABLE "
            + TABLE_Book + "("
            + Book_ID + " INTEGER  PRIMARY KEY AUTOINCREMENT, "
            + Book_Description + " TEXT NOT NULL , "
            + Book_Author + " TEXT NOT NULL , "
            + Book_Title + " TEXT NOT NULL , "
            + Book_Isbn + " TEXT NOT NULL , "
            + Book_CATAGORY + " TEXT NOT NULL);";
    //camera
    public static final String TABLE_Camera = "camera";

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
    //cellphone
    public static final String TABLE_Cellphone = "cellphone";

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
    //clothing
    public static final String TABLE_Clothing = "clothing";

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
    //computer
    public static final String TABLE_Computer = "computer";

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
    //livestock
    public static final String TABLE_Livestock = "livestock";

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
    //shoes
    public static final String TABLE_Shoes = "shoes";

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
    //Television
    public static final String TABLE_Television = "television";

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
    //vehicle
    public static final String TABLE_Vehicle = "vehicle";

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


    public CreateTables(Context context) {
        super(context, DBConstants.DATABASE_NAME, null, DBConstants.DATABASE_VERSION);
    }

    public void createTables(){
        getReadableDatabase().execSQL(DATABASE_CREATE_Audio);
        getReadableDatabase().execSQL(DATABASE_CREATE_Book);
        getReadableDatabase().execSQL(DATABASE_CREATE_Camera);
        getReadableDatabase().execSQL(DATABASE_CREATE_Cellphone);
        getReadableDatabase().execSQL(DATABASE_CREATE_Clothing);
        getReadableDatabase().execSQL(DATABASE_CREATE_Computer);
        getReadableDatabase().execSQL(DATABASE_CREATE_Livestock);
        getReadableDatabase().execSQL(DATABASE_CREATE_Shoes);
        getReadableDatabase().execSQL(DATABASE_CREATE_Television);
        getReadableDatabase().execSQL(DATABASE_CREATE_Vehicle);

    }

    public void resetDatabase(){
        getReadableDatabase().execSQL("DROP TABLE IF EXISTS " + TABLE_Audio);
        getReadableDatabase().execSQL("DROP TABLE IF EXISTS " + TABLE_Book);
        getReadableDatabase().execSQL("DROP TABLE IF EXISTS " + TABLE_Camera);
        getReadableDatabase().execSQL("DROP TABLE IF EXISTS " + TABLE_Cellphone);
        getReadableDatabase().execSQL("DROP TABLE IF EXISTS " + TABLE_Clothing);
        getReadableDatabase().execSQL("DROP TABLE IF EXISTS " + TABLE_Computer);
        getReadableDatabase().execSQL("DROP TABLE IF EXISTS " + TABLE_Livestock);
        getReadableDatabase().execSQL("DROP TABLE IF EXISTS " + TABLE_Shoes);
        getReadableDatabase().execSQL("DROP TABLE IF EXISTS " + TABLE_Television);
        getReadableDatabase().execSQL("DROP TABLE IF EXISTS " + TABLE_Vehicle);

    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        Log.w(this.getClass().getName(),
                "Upgrading database from version " + oldVersion + " to "
                        + newVersion + ", which will destroy all old data");
        resetDatabase();
        onCreate(db);
    }
}

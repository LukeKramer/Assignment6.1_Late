package com.example.lukekramer.virtualzoo.repository.book;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.lukekramer.virtualzoo.database.DBConstants;
import com.example.lukekramer.virtualzoo.domain.book.Book;


import java.util.HashSet;
import java.util.Set;

/**
 * Created by lukekramer on 24/10/2016.
 */

public class BookRepositoryImpl extends SQLiteOpenHelper implements BookRepository {

    public static final String TABLE_Book = "book";
    private SQLiteDatabase db;

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


    public BookRepositoryImpl(Context context) {
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
    public Book findById(Long id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(
                TABLE_Book,
                new String[]{
                        Book_ID,
                        Book_Description,
                        Book_Author,
                        Book_Title,
                        Book_Isbn,
                        Book_CATAGORY},
                Book_ID + " =? ",
                new String[]{String.valueOf(id)},
                null,
                null,
                null,
                null);
        if (cursor.moveToFirst()) {
            final Book result = new Book.Builder()
                    .id(cursor.getLong(0))
                    .productDescription(cursor.getString(1))
                    .productAuthor(cursor.getString(2))
                    .productTitle(cursor.getString(3))
                    .productISBN(cursor.getString(4))
                    .category(cursor.getString(5))
                    .build();

            return result;
        } else {
            return null;
        }
    }

    @Override
    public Book save(Book entity) {

        open();
        ContentValues values = new ContentValues();
        values.put(Book_ID, entity.getId());
        values.put(Book_Description, entity.getDescription());
        values.put(Book_Author, entity.getAuthor());
        values.put(Book_Title, entity.getTitle());
        values.put(Book_Isbn, entity.getISBN());
        values.put(Book_CATAGORY, entity.getCategory());
        long id = db.insertOrThrow(TABLE_Book, null, values);
        Book insertedEntity = new Book.Builder()
                .copy(entity)
                .id(new Long(id))
                .build();
        return insertedEntity;
    }

    @Override
    public Book update(Book entity) {
        open();
        ContentValues values = new ContentValues();
        values.put(Book_ID, entity.getId());
        values.put(Book_Description, entity.getDescription());
        values.put(Book_Author, entity.getAuthor());
        values.put(Book_Title, entity.getTitle());
        values.put(Book_Isbn, entity.getISBN());
        values.put(Book_CATAGORY, entity.getCategory());
        db.update(
                TABLE_Book,
                values,
                Book_ID + " =? ",
                new String[]{String.valueOf(entity.getId())}
        );
        return entity;
    }

    @Override
    public Book delete(Book entity) {
        open();
        db.delete(
                TABLE_Book,
                Book_ID + " =? ",
                new String[]{String.valueOf(entity.getId())});
        return entity;
    }

    @Override
    public Set<Book> findAll() {
        SQLiteDatabase db = this.getReadableDatabase();
        String selectAll = " SELECT * FROM " + TABLE_Book;
        Set<Book> settings = new HashSet<>();
        open();
        Cursor cursor = db.rawQuery(selectAll, null);
        if (cursor.moveToFirst()) {
            do {
                final Book result = new Book.Builder()
                        .id(cursor.getLong(0))
                        .productDescription(cursor.getString(1))
                        .productAuthor(cursor.getString(2))
                        .productTitle(cursor.getString(3))
                        .productISBN(cursor.getString(4))
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
        int rowsDeleted = db.delete(TABLE_Book,null,null);
        //close();
        return rowsDeleted;
    }
}

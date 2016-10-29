package com.example.lukekramer.virtualzoo.repository.book;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import com.example.lukekramer.virtualzoo.domain.book.Book;
import com.example.lukekramer.virtualzoo.repository.tables.CreateTables;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Set;

/**
 * Created by lukekramer on 24/10/2016.
 */
@RunWith(AndroidJUnit4.class)
public class BookRepositoryImplTest {

    private static final String TAG_Book="book";
    private Long id;

    @Test
    public void bookRepositoryCRUDTest() throws Exception {

        Context appContext = InstrumentationRegistry.getTargetContext();

        //NB!!IMPORTANT
        //*****
        // Run the table creator code segment once to create tables then comment
        // this code segment out to avoid tables being reset(dropped the recreated) and data being lost
        // when running the testCase multiple times!!
        CreateTables createTables = new CreateTables(appContext);
        createTables.resetDatabase();
        createTables.createTables();

        //******************************************

        //Table
        //Create
        
        BookRepository bookRepository = new BookRepositoryImpl(appContext);

        Book productTest = new Book.Builder()
                .productDescription("Fairy Tale")
                .productAuthor("Ferin Taylor")
                .productTitle("Snow White")
                .productISBN("945-2497659111")
                .category("Thriller")
                .build();
        

        productTest=bookRepository.save(productTest);
        id = productTest.getId();
        Assert.assertNotNull(TAG_Book+" CREATE",productTest);

        //READ (findALL)
        Set<Book> book = bookRepository.findAll();
        Assert.assertTrue(TAG_Book+" READ ALL",book.size()>0);

        //READ (find) by id
        Book bookEntity = bookRepository.findById(id);
        Assert.assertNotNull(TAG_Book+" READ ENTITY",bookEntity);

        //UPDATE
        Book updateBook = new Book.Builder()
                .copy(bookEntity)
                .productTitle("Changed")
                .build();
        bookRepository.update(updateBook);
        Book newBook = bookRepository.findById(id);
        Assert.assertEquals(TAG_Book+ " UPDATE ENTITY","Changed",newBook.getTitle());

        //DELETE
        bookRepository.delete(newBook);
        Book deletedBook = bookRepository.findById(id);
        Assert.assertNull(TAG_Book+" DELETE",deletedBook);

        //DELETE ALL
        int totalrows = bookRepository.deleteAll();
        Assert.assertFalse(totalrows > 0);

    }
}

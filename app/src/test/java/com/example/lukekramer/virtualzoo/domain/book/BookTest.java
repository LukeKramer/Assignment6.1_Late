package com.example.lukekramer.virtualzoo.domain.book;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by lukekramer on 23/10/2016.
 */

public class BookTest {

    @Test
    public void createBook() throws Exception {

        Book productTest = new Book.Builder()
                .id(Long.valueOf("1"))
                .productDescription("Fairy Tale")
                .productAuthor("Ferin Taylor")
                .productTitle("Snow White")
                .productISBN("945-2497659111")
                .category("Thriller")
                .build();

        Assert.assertNotNull(productTest);
        Assert.assertEquals("Fairy Tale",productTest.getDescription());
        Assert.assertEquals("Snow White",productTest.getTitle());
        Assert.assertEquals("Ferin Taylor",productTest.getAuthor());
        Assert.assertEquals("945-2497659111",productTest.getISBN());
        Assert.assertSame(Long.valueOf("1"),productTest.getId());
        Assert.assertNotNull("Thriller",productTest.getCategory());

        productTest = new Book.Builder()
                .copy(productTest)
                .productDescription("Change")
                .build();
        Assert.assertEquals("Change",productTest.getDescription());


    }
}

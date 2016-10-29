package com.example.lukekramer.virtualzoo.domain.book;

import com.example.lukekramer.virtualzoo.domain.IProduct;

import java.io.Serializable;

/**
 * Created by lukekramer on 23/10/2016.
 */

public class Book implements IProduct,Serializable {

    private Long id;
    private String category;
    private String description;
    private String author;
    private String title;
    private String isbn;


    protected Book()
    {

    }

    private Book(Builder value){

        this.id = value.id;
        this.category = value.category;
        this.description = value.description;
        this.author = value.author;
        this.title = value.title;
        this.isbn = value.isbn;

    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public String getCategory() {
        return category;
    }

    @Override
    public String getDescription() {
        return description;
    }


    public String getTitle() {
        return title;
    }


    public String getAuthor() {
        return author;
    }


    public String getISBN() {
        return isbn;
    }



    public static class Builder {

        private Long id;
        private String category;
        private String description;
        private String author;
        private String title;
        private String isbn;


        public Builder() {

        }

        public Book.Builder id(Long value) {
            this.id = value;
            return this;
        }

        public Book.Builder category(String value) {
            this.category= value;
            return this;
        }

        public Book.Builder productDescription(String value) {
            this.description = value;
            return this;
        }

        public Book.Builder productAuthor(String value) {
            this.author = value;
            return this;
        }

        public Book.Builder productTitle(String value) {
            this.title = value;
            return this;
        }

        public Book.Builder productISBN(String value) {
            this.isbn = value;
            return this;
        }


        public Book.Builder copy(Book value) {
            this.id = value.id;
            this.category = value.category;
            this.description = value.description;
            this.author = value.author;
            this.title = value.title;
            this.isbn = value.isbn;

            return this;
        }

        public Book build() {
            return new Book(this);
        }
    }

}

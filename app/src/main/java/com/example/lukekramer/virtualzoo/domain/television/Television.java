package com.example.lukekramer.virtualzoo.domain.television;

import com.example.lukekramer.virtualzoo.domain.IProduct;

import java.io.Serializable;


/**
 * Created by lukekramer on 23/10/2016.
 */

public class Television implements IProduct,Serializable {

    private Long id;
    private String category;
    private String description;
    private String make;
    private String model;
    private String size;



    protected Television(){}

    protected Television(Television.Builder value){

        this.id = value.id;
        this.category = value.category;
        this.description = value.description;
        this.make = value.make;
        this.model = value.model;
        this.size = value.size;

    }


    public String getSize() {
        return size;
    }


    public String getMake() {
        return make;
    }


    public String getModel() {
        return model;
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

    public static class Builder {

        private Long id;
        private String category;
        private String description;
        private String make;
        private String model;
        private String size;


        public Builder() {

        }

        public Television.Builder id(Long value) {
            this.id = value;
            return this;
        }

        public Television.Builder category(String value) {
            this.category= value;
            return this;
        }

        public Television.Builder productDescription(String value) {
            this.description = value;
            return this;
        }

        public Television.Builder productMake(String value) {
            this.make = value;
            return this;
        }

        public Television.Builder productModel(String value) {
            this.model = value;
            return this;
        }

        public Television.Builder productSize(String value) {
            this.size = value;
            return this;
        }

        public Television.Builder copy(Television value) {
            this.id = value.id;
            this.category = value.category;
            this.description = value.description;
            this.make = value.make;
            this.model = value.model;
            this.size = value.size;
            return this;
        }


        public Television build() {

            return new Television(this);
        }
    }

}

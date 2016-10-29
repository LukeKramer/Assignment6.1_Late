package com.example.lukekramer.virtualzoo.domain.shoes;

import com.example.lukekramer.virtualzoo.domain.IProduct;

import java.io.Serializable;


/**
 * Created by lukekramer on 23/10/2016.
 */

public class Shoes implements IProduct,Serializable {

    private Long id;
    private String category;
    private String description;
    private String brand;
    private String size;


    protected Shoes(){

    }

    private Shoes(Shoes.Builder value){

        this.id = value.id;
        this.category = value.category;
        this.description = value.description;
        this.brand = value.brand;
        this.size = value.size;

    }


    public String getBrand() {
        return brand;
    }


    public String getSize() {
        return size;
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
        private String brand;
        private String size;


        public Builder() {

        }

        public Shoes.Builder id(Long value) {
            this.id = value;
            return this;
        }

        public Shoes.Builder category(String value) {
            this.category= value;
            return this;
        }

        public Shoes.Builder productDescription(String value) {
            this.description = value;
            return this;
        }

        public Shoes.Builder productBrand(String value) {
            this.brand = value;
            return this;
        }

        public Shoes.Builder productSize(String value) {
            this.size = value;
            return this;
        }


        public Shoes.Builder copy(Shoes value) {
            this.id = value.id;
            this.category = value.category;
            this.description = value.description;
            this.brand = value.brand;
            this.size = value.size;
            return this;
        }

        public Shoes build() {

            return new Shoes(this);
        }
    }

}

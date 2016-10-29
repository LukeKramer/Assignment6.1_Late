package com.example.lukekramer.virtualzoo.domain.cellphone;

import com.example.lukekramer.virtualzoo.domain.IProduct;

import java.io.Serializable;

/**
 * Created by lukekramer on 23/10/2016.
 */

public class Cellphone implements IProduct,Serializable{

    private Long id;
    private String category;
    private String description;
    private String make;
    private String model;
    private String type;

    protected Cellphone(){}

    protected Cellphone(Cellphone.Builder value){

        this.id = value.id;
        this.category = value.category;
        this.description = value.description;
        this.make = value.make;
        this.model = value.model;
        this.type = value.type;

    }

    public String getType(){
        return type;
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
        private String type;


        public Builder() {

        }

        public Cellphone.Builder id(Long value) {
            this.id = value;
            return this;
        }

        public Cellphone.Builder category(String value) {
            this.category= value;
            return this;
        }

        public Cellphone.Builder productDescription(String value) {
            this.description = value;
            return this;
        }

        public Cellphone.Builder productMake(String value) {
            this.make = value;
            return this;
        }

        public Cellphone.Builder productModel(String value) {
            this.model = value;
            return this;
        }

        public Cellphone.Builder productType(String value) {
            this.type = value;
            return this;
        }

        public Cellphone.Builder copy(Cellphone value) {
            this.id = value.id;
            this.category = value.category;
            this.description = value.description;
            this.make = value.make;
            this.model = value.model;
            this.type = value.type;
            return this;
        }


        public Cellphone build() {

            return new Cellphone(this);
        }
    }

}

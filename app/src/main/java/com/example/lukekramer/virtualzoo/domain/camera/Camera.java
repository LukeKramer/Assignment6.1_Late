package com.example.lukekramer.virtualzoo.domain.camera;

import com.example.lukekramer.virtualzoo.domain.IProduct;

import java.io.Serializable;

/**
 * Created by lukekramer on 23/10/2016.
 */

public class Camera implements IProduct,Serializable{

    private Long id;
    private String category;
    private String description;
    private String make;
    private String model;
    private String size;
    private String type;



    protected Camera(){}

    protected Camera(Camera.Builder value){

        this.id = value.id;
        this.category = value.category;
        this.description = value.description;
        this.make = value.make;
        this.model = value.model;
        this.size = value.size;
        this.type = value.type;

    }

    public String getType(){
        return type;
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
        private String type;


        public Builder() {

        }

        public Camera.Builder id(Long value) {
            this.id = value;
            return this;
        }

        public Camera.Builder category(String value) {
            this.category= value;
            return this;
        }

        public Camera.Builder productDescription(String value) {
            this.description = value;
            return this;
        }

        public Camera.Builder productMake(String value) {
            this.make = value;
            return this;
        }

        public Camera.Builder productModel(String value) {
            this.model = value;
            return this;
        }

        public Camera.Builder productSize(String value) {
            this.size = value;
            return this;
        }

        public Camera.Builder productType(String value) {
            this.type = value;
            return this;
        }

        public Camera.Builder copy(Camera value) {
            this.id = value.id;
            this.category = value.category;
            this.description = value.description;
            this.make = value.make;
            this.model = value.model;
            this.size = value.size;
            this.type = value.type;
            return this;
        }


        public Camera build() {

            return new Camera(this);
        }
    }

}

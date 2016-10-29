package com.example.lukekramer.virtualzoo.domain.vehicle;

import com.example.lukekramer.virtualzoo.domain.IProduct;

import java.io.Serializable;

/**
 * Created by lukekramer on 23/10/2016.
 */

public class Vehicle implements IProduct,Serializable{

    private Long id;
    private String category;
    private String description;
    private String type;
    private String make;
    private String model;



    protected Vehicle(){}

    private Vehicle(Builder value){

        this.id = value.id;
        this.category = value.category;
        this.description = value.description;
        this.type = value.type;
        this.make = value.make;
        this.model = value.model;


    }


    public String getVehicletype() {
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
        private String type;
        private String make;
        private String model;


        public Builder() {

        }

        public Vehicle.Builder id(Long value) {
            this.id = value;
            return this;
        }

        public Vehicle.Builder category(String value) {
            this.category= value;
            return this;
        }

        public Vehicle.Builder productDescription(String value) {
            this.description = value;
            return this;
        }

        public Vehicle.Builder productType(String value) {
            this.type = value;
            return this;
        }

        public Vehicle.Builder productMake(String value) {
            this.make = value;
            return this;
        }

        public Vehicle.Builder productModel(String value) {
            this.model = value;
            return this;
        }

        public Vehicle.Builder copy(Vehicle value) {
            this.id = value.id;
            this.category = value.category;
            this.description = value.description;
            this.type = value.type;
            this.make = value.make;
            this.model = value.model;
            return this;
        }

        public Vehicle build() {

            return new Vehicle(this);
        }
    }


}

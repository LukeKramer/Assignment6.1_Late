package com.example.lukekramer.virtualzoo.domain.audio;

import com.example.lukekramer.virtualzoo.domain.IProduct;

import java.io.Serializable;


/**
 * Created by lukekramer on 23/10/2016.
 */

public class Audio implements IProduct,Serializable{

    private Long id;
    private String category;
    private String description;
    private String make;
    private String watts;
    private String amps;


    protected Audio(){}

    protected Audio(Builder value){

        this.id = value.id;
        this.category = value.category;
        this.description = value.description;
        this.make = value.make;
        this.watts = value.watts;
        this.amps = value.amps;

    }


    public String getWatts() {
        return watts;
    }


    public String getAmps() {
        return amps;
    }


    public String getMake() {
        return make;
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
        private String watts;
        private String amps;



        public Builder() {

        }

        public Audio.Builder id(Long value) {
            this.id = value;
            return this;
        }

        public Audio.Builder category(String value) {
            this.category= value;
            return this;
        }

        public Audio.Builder productDescription(String value) {
            this.description = value;
            return this;
        }

        public Audio.Builder productMake(String value) {
            this.make = value;
            return this;
        }

        public Audio.Builder productWatts(String value) {
            this.watts = value;
            return this;
        }

        public Audio.Builder productAmps(String value) {
            this.amps = value;
            return this;
        }

        public Audio.Builder copy(Audio value) {
            this.id = value.id;
            this.category = value.category;
            this.description = value.description;
            this.make = value.make;
            this.watts = value.watts;
            this.amps = value.amps;
            return this;
        }


        public Audio build() {

            return new Audio(this);
        }
    }

}

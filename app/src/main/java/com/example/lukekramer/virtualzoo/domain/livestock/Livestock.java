package com.example.lukekramer.virtualzoo.domain.livestock;

import com.example.lukekramer.virtualzoo.domain.IProduct;

import java.io.Serializable;

/**
 * Created by lukekramer on 23/10/2016.
 */

public class Livestock implements IProduct,Serializable {

    private Long id;
    private String category;
    private String description;
    private String age;
    private String grade;

    protected Livestock() {

    }

    private Livestock(Builder value){

        this.id = value.id;
        this.category = value.category;
        this.description = value.description;
        this.age = value.age;
        this.grade = value.grade;

    }

    public Long getId() {
        return id;
    }

    public String getCategory() {
        return category;
    }

    public String getDescription() {
        return description;
    }

    public String getAge() {
        return age;
    }

    public String getGrade() {
        return grade;
    }

    public static class Builder {

        private Long id;
        private String category;
        private String description;
        private String age;
        private String grade;

        public Builder() {

        }

        public Livestock.Builder id(Long value) {
            this.id = value;
            return this;
        }

        public Livestock.Builder category(String value) {
            this.category= value;
            return this;
        }

        public Livestock.Builder productDescription(String value) {
            this.description = value;
            return this;
        }

        public Livestock.Builder productAge(String value) {
            this.age = value;
            return this;
        }

        public Livestock.Builder productGrade(String value) {
            this.grade = value;
            return this;
        }

        public Livestock.Builder copy(Livestock value) {
            this.id = value.id;
            this.category = value.category;
            this.description = value.description;
            this.age = value.age;
            this.grade = value.grade;
            return this;
        }


        public Livestock build() {

            return new Livestock(this);
        }
    }

}

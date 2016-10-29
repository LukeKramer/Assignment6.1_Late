package com.example.lukekramer.virtualzoo.domain;

/**
 * Created by lukekramer on 23/10/2016.
 */

public interface IProduct {

    /**
     * @return returns a products id
     */

    Long getId();

    /**
     * @return returns a String of the Category
     */

    String getCategory();


    /**
     * @return returns a String describing the product
     */
    String getDescription();

}

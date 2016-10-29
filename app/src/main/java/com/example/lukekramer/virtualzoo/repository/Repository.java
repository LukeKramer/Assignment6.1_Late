package com.example.lukekramer.virtualzoo.repository;

import java.util.Set;

/**
 * Created by lukekramer on 23/10/2016.
 */

public interface Repository<E, ID> {

    E findById(ID id);

    E save(E entity);

    E update(E entity);

    E delete(E entity);

    Set<E> findAll();

    int deleteAll();
}

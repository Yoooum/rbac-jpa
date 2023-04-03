package com.prprv.jpa.service;

/**
 * @author Yoooum
 */
public interface Crud<T> {
    T create(T t);
    T select(Long id);
    T update(T t);
    void delete(Long id);
}

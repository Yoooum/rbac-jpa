package com.prprv.jpa.service;

/**
 * @author Yoooum
 */
public interface CrudService<T> {
    T create(T t);
    T select(Long id);
    T update(T t);
    void delete(Long id);
}

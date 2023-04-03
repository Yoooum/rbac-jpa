package com.prprv.jpa.service;

import com.prprv.jpa.entity.AbstractEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Yoooum
 */
public abstract class BaseCrudService<T extends AbstractEntity, D extends JpaRepository<T, Long>> {

        protected D repository;

        public BaseCrudService(D repository) {
            this.repository = repository;
        }

        public T save(T entity) {
            return repository.save(entity);
        }

        public T update(T entity) {
            return repository.save(entity);
        }

        public void delete(T entity) {
            repository.delete(entity);
        }

        public void deleteById(Long id) {
            repository.deleteById(id);
        }

        public T findById(Long id) {
            return repository.findById(id).orElse(null);
        }

        public Iterable<T> findAll() {
            return repository.findAll();
        }
}

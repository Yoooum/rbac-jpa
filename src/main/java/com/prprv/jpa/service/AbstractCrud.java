package com.prprv.jpa.service;

import com.prprv.jpa.entity.AbstractEntity;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

/**
 * @author Yoooum
 */
public abstract class AbstractCrud<T extends AbstractEntity, D extends JpaRepository<T, Long>> {

    protected final D repository;

    protected AbstractCrud(D repository) {
        this.repository = repository;
    }

    public T save(@RequestBody T entity) {
        return repository.save(entity);
    }

    public T update(T entity) {
        Optional<T> byId = repository.findById(entity.getId());
        if (byId.isPresent()) {
            T raw = byId.get();
            BeanUtils.copyProperties(entity, raw, "id");
            return repository.saveAndFlush(raw);
        }
        return null;
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    public List<T> findAll() {
        return repository.findAll();
    }

    public Page<T> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    public T findById(Long id) {
        return repository.findById(id).orElse(null);
    }
}

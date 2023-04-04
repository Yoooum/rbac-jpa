package com.prprv.jpa.controller;

import com.prprv.jpa.entity.AbstractEntity;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

/**
 * @author Yoooum
 */
public abstract class BaseCrudController<T extends AbstractEntity, D extends JpaRepository<T, Long>> {
    protected D repository;
    public BaseCrudController(D repository) {
        this.repository = repository;
    }

    @Operation(summary = "create", description = "创建")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public T save(@RequestBody T entity) {
        return repository.save(entity);
    }

    @Operation(summary = "update", description = "更新")
    @PutMapping("/{id}")
    public T update(@PathVariable("id") T raw, T t) {
        BeanUtils.copyProperties(t, raw, "id");
        return repository.saveAndFlush(raw);
    }

    @Operation(summary = "delete", description = "删除")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") Long id) {
        repository.deleteById(id);
    }

    @Operation(summary = "findAll", description = "查询所有，分页查询")
    @GetMapping
    public Page<T> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Operation(summary = "get", description = "查询单个")
    @GetMapping("/{id}")
    public T get(@PathVariable("id") T t) {
        return t;
    }
}

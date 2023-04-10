package com.prprv.jpa.controller;

import com.prprv.jpa.entity.AbstractEntity;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * @author Yoooum
 */
public abstract class AbstractCrudController<T extends AbstractEntity, D extends JpaRepository<T, Long>> {
    protected final D repository;

    public AbstractCrudController(D repository) {
        this.repository = repository;
    }

    @Operation(summary = "create", description = "创建")
    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public T save(@RequestBody T entity) {
        return repository.save(entity);
    }

    @Operation(summary = "update", description = "更新")
    @PutMapping("/update")
    public ResponseEntity<T> update(@RequestBody T entity) {
        Optional<T> byId = repository.findById(entity.getId());
        if (byId.isPresent()) {
            T raw = byId.get();
            BeanUtils.copyProperties(entity, raw, "id");
            return ResponseEntity.ok(repository.saveAndFlush(raw));
        }
        return ResponseEntity.notFound().build();
    }

    @Operation(summary = "delete", description = "删除")
    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") Long id) {
        repository.deleteById(id);
    }

    @Operation(summary = "findAll", description = "查询所有，分页查询")
    @GetMapping("/list")
    public List<T> findAll() {
        return repository.findAll();
    }

    @Operation(summary = "findAll(Pageable pageable)", description = "查询所有，分页查询")
    @GetMapping(value = "/page", params = {"page", "size", "sort", "direction"})
    public Page<T> findAll(@RequestParam("page") Integer page,
                           @RequestParam("size") Integer size,
                           @RequestParam("sort") String sort,
                           @RequestParam("direction") String direction) {
        Pageable pageable;
        if (direction.equals("asc") || direction.equals("desc")) {
            pageable = PageRequest.of(page, size, Sort.Direction.fromString(direction), sort);
        } else {
            pageable = Pageable.unpaged();
        }
        return repository.findAll(pageable);
    }

    @Operation(summary = "get", description = "查询单个")
    @GetMapping("/{id}")
    public T get(@PathVariable("id") Long id) {
        return repository.findById(id).orElse(null);
    }
}

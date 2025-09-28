package de.dnd.stiki.plugins.rest;

import de.dnd.stiki.application.AbstractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public abstract class AbstractController<D, S extends AbstractService<?, D, ?, ?>> {

    @Autowired
    S service;

    @GetMapping()
    public ResponseEntity<List<D>> getAll() {

        return ResponseEntity.ok().body(service.getAll());
    }
}

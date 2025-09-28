package de.dnd.stiki.plugins.rest;

import de.dnd.stiki.adapters.item.ItemDto;
import de.dnd.stiki.application.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/item")
public class ItemController {

    @Autowired
    ItemService service;

    @GetMapping()
    public ResponseEntity<List<ItemDto>> getAllItems() {

        return ResponseEntity.ok().body(service.getAllItems());
    }
}

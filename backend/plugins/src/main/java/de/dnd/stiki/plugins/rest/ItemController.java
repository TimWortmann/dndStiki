package de.dnd.stiki.plugins.rest;

import de.dnd.stiki.adapters.item.ItemDto;
import de.dnd.stiki.application.ItemService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/item")
public class ItemController extends AbstractController<ItemDto, ItemService> {
}

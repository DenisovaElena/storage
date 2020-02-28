package ru.example.storage.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import ru.example.storage.service.StorageService;
import ru.example.storage.to.ItemsQuery;
import java.util.List;

public abstract class AbstractStorageRestController {
    protected final Logger LOG = LoggerFactory.getLogger(getClass());

    @Autowired
    StorageService storageService;

    List<Integer> getItemsInBox(ItemsQuery itemsQuery) {
        return storageService.getItemsInBox(itemsQuery);
    }
}

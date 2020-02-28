package ru.example.storage.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.example.storage.to.ItemsQuery;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = StorageRestController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class StorageRestController extends AbstractStorageRestController {
    public static final String REST_URL = "/rest/storage";

    // Метод возвращает все Items из ящика, учитывая вложенность
    @Override
    @PostMapping(value = "/getItemsInBox")
    List<Integer> getItemsInBox(@Valid @RequestBody ItemsQuery itemsQuery) {
        return super.getItemsInBox(itemsQuery);
    }
}

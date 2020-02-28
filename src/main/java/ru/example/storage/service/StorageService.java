package ru.example.storage.service;

import ru.example.storage.to.ItemsQuery;
import ru.example.storage.to.Storage;
import java.util.List;

public interface StorageService {
    void save(Storage storage);

    List<Integer> getItemsInBox(ItemsQuery itemsQuery);
}

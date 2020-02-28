package ru.example.storage.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.example.storage.dao.BoxRepository;
import ru.example.storage.dao.ItemRepository;
import ru.example.storage.model.BaseEntity;
import ru.example.storage.model.Box;
import ru.example.storage.model.Item;
import ru.example.storage.to.ItemsQuery;
import ru.example.storage.to.Storage;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class StorageServiceImpl implements StorageService{
    @Autowired
    private BoxRepository boxRepository;

    @Autowired
    private ItemRepository itemRepository;

    @Override
    public void save(Storage storage) {
        // Сохраняем все Items из Storage, которые без коробок
        for (Item item: storage.getItems()) {
            itemRepository.save(item);
        }
        // Сохраняем все Boxes из Storage, включая все вложенные в них объекты (другие Boxes, а также Items)
        for (Box box: storage.getBox()) {
            boxRepository.save(box);
        }
    }

    @Override
    public List<Integer> getItemsInBox(ItemsQuery itemsQuery) {
        // Получаем ящик Box по id
        Box box = boxRepository.findById(itemsQuery.getBox()).orElse(new Box());
        return box.flattened()
                // проверяем, есть ли у ящика Items, если не проверим, то будет ошибка NullPointerException
                .filter(b -> b.getItems() != null)
                // преобразуем в стрим Boxes в стрим Items
                .flatMap(b -> b.getItems().stream())
                // если цвет в запросе вообще не указан, то ничего не фильтруем, в противном случае фильтруем по цвету
                .filter(i -> itemsQuery.getColor() == null || i.getColor() != null && i.getColor().equals(itemsQuery.getColor()))
                .map(BaseEntity::getId).collect(Collectors.toList());
    }
}

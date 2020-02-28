package ru.example.storage.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.example.storage.model.Item;

public interface ItemRepository extends JpaRepository<Item, Integer> {
}

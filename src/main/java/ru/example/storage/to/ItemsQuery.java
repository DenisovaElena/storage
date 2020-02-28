package ru.example.storage.to;

import lombok.Data;

@Data
// dto для REST-контроллера, который соответствует структуре JSON-ответа согласно заданию
public class ItemsQuery {
    Integer box;
    String color;
}

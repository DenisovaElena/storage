package ru.example.storage.to;

import lombok.Data;
import ru.example.storage.model.Box;
import ru.example.storage.model.Item;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

// Вспомогательный dto, для того, чтобы десериализовать XML
@Data
@XmlRootElement(name = "Storage")
@XmlAccessorType(XmlAccessType.FIELD)
public class Storage {
    @XmlElement(name = "Item", required = false)
    private Item[] items;
    @XmlElement(name = "Box", required = false)
    private Box[] box;
}

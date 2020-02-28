package ru.example.storage.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import javax.persistence.*;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlTransient;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@XmlAccessorType(XmlAccessType.FIELD)
public class Item extends BaseEntity {
    @XmlAttribute(name = "color", required = true)
    private String color;

    @XmlTransient
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "contained_in", nullable = true)
    private Box parentBox;

    // После десериализации ссылки на родительский объект сохраняем в переменную parentBox
    void afterUnmarshal(Unmarshaller unmarshaller, Object parent) {
        if (parent instanceof Box) {
            parentBox = (Box) parent;
        }
    }

    // Принудительно переопределяем toString, иначе lombok вставит туда переменную parentBox и будет рекурсия, а потом ошибка
    @Override
    public String toString() {
        return "Item{" +
                "color='" + color + '\'' +
                '}';
    }
}

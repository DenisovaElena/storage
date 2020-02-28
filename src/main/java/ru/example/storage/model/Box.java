package ru.example.storage.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import javax.persistence.*;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlTransient;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@XmlAccessorType(XmlAccessType.FIELD)
public class Box extends BaseEntity{
    @XmlElement(name = "Item", required = false)
    @OneToMany(fetch = FetchType.LAZY,  mappedBy = "parentBox", cascade = CascadeType.ALL)
    private List<Item> items;

    @XmlElement(name = "Box", required = false)
    @OneToMany(fetch = FetchType.LAZY,  mappedBy = "parentBox", cascade = CascadeType.ALL)
    private List<Box> box;

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

    // http://squirrel.pl/blog/2015/03/04/walking-recursive-data-structures-using-java-8-streams/
    // метод, позволяющий получить плоский список из дочерних объектов
    // необходим, чтобы рекурсивно вытащить все ящики и сделать из них плоских список
    public Stream<Box> flattened() {
        return Stream.concat(
                Stream.of(this),
                Optional.ofNullable(box)
                        .map(Collection::stream)
                        .orElseGet(Stream::empty).flatMap(Box::flattened));
    }

    // Принудительно переопределяем toString, иначе lombok вставит туда переменную parentBox и будет рекурсия, а потом ошибка
    @Override
    public String toString() {
        return "Box{" +
                "items=" + items +
                ", box=" + box +
                '}';
    }
}

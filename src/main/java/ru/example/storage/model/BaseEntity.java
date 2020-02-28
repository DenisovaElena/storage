package ru.example.storage.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;

@MappedSuperclass
@Access(AccessType.FIELD)
@Data
@NoArgsConstructor
@AllArgsConstructor
@XmlAccessorType(XmlAccessType.FIELD)
// Базовый класс для всех классов, у которых есть поле id
public class BaseEntity {
    @Id
    @XmlAttribute(name = "id", required = true)
    private Integer id;
}

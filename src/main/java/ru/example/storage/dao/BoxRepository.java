package ru.example.storage.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.example.storage.model.Box;

public interface BoxRepository extends JpaRepository<Box, Integer> {
}

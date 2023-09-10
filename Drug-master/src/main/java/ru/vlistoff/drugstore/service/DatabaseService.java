package ru.vlistoff.drugstore.service;


import java.util.List;
import java.util.Optional;

public interface DatabaseService<T> {
    Optional<T> getById(Long id);
    List<T> getAll();
    T create(T t);
    T update(T t, Long id);
    void delete(Long id);
}

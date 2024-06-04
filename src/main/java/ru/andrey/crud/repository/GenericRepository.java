package ru.andrey.crud.repository;

import java.util.List;

public interface GenericRepository<T, ID> {

    T create(T t);

    T getById(ID id);

    List<T> getAll();

    void deleteById(ID id);

    T update(T t);

}

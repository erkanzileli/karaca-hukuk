package dao;

import java.util.List;

public interface BaseDAO<T> {
    void create(T t);
    List<T> read();
    T readOne(long id);
    void update(T t);
    void delete(long id);
}

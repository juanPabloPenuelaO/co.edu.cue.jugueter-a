package repository;

import java.util.List;

public interface Repository <T>{
    List<T> list();
    T byId(Long id);
    void save(T t);

    void Upgrate();

    void delete(Long id);
}
package repository;

import domain.Identifiable;

public interface IRepository<T extends Identifiable> {
    void add(T element, String id);
    void delete(String id);
    void update(T element, String id);
    T findById(String id);
    T[] getAll();
}

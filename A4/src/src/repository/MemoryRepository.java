package repository;

import domain.Identifiable;
import java.util.HashMap;

public class MemoryRepository<T extends Identifiable> implements IRepository<T> {

    protected HashMap<String, T> elements = new HashMap<>();

    @Override
    public void add(T element, String id) {
        elements.put(id, element);
    }

    @Override
    public void delete(String id) {
        elements.remove(id);
    }

    @Override
    public void update(T element, String id) {
        elements.put(id, element);
    }

    @Override
    public T findById(String id) {
        return elements.get(id);
    }

    @Override
    public T[] getAll() {
        return elements.values().toArray((T[]) new Identifiable[0]);
    }
}

package domain;

public interface AbstractFilter<T> {
    boolean accept(T entity);
}
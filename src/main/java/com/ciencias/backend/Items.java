package com.ciencias.backend;

public interface Items<T> {
    void add(T item);
    boolean remove();
}

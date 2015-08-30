package com.ciencias.backend;

import javafx.collections.ObservableList;

public interface Items<T> {
    void add(T item);
    boolean remove(int i);
    ObservableList<T> getItems();
    int search(SearchBehaviour<T> searchBehaviour, T queryItem);
}

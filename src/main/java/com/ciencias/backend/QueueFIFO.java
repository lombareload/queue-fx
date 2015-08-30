package com.ciencias.backend;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.List;

public class QueueFIFO<T> implements Items<T> {
    final private List<T> nodes = new ArrayList<>();
    final private ObservableList<T> items = FXCollections.observableList(nodes);

    public ObservableList<T> getItems() {
        return items;
    }

    @Override
    public int search(SearchBehaviour<T> searchBehaviour, T queryItem) {
        int i = 0;
        for (T node : nodes) {
            if(searchBehaviour.matches(node, queryItem)){
                return i;
            }
            i++;
        }
        return -1;
    }

    public void add(T item){
        items.add(item);
    }

    public boolean remove(int i){
        if(items.isEmpty()){
            return false;
        } else {
//            items.remove(0);
            items.remove(i);
            return true;
        }
    }
}

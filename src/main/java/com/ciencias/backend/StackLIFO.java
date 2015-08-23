package com.ciencias.backend;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.List;

public class StackLIFO<T> implements Items<T> {
    final private List<T> nodes = new ArrayList<>();
    final private ObservableList<T> items = FXCollections.observableList(nodes);

    public ObservableList<T> getItems() {
        return items;
    }

    public void add(T item){
        items.add(item);
    }

    public boolean remove(){
        if(items.isEmpty()){
            return false;
        } else {
            int lastIndex = items.size() - 1;
            items.remove(lastIndex);
            return true;
        }
    }
}

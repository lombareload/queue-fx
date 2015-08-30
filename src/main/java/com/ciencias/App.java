package com.ciencias;

import com.ciencias.backend.ContainsBehaviour;
import com.ciencias.backend.Items;
import com.ciencias.backend.QueueFIFO;
import com.ciencias.backend.SearchBehaviour;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class App extends Application {
    ListView<String> listView;
    Items<String> queueFIFO = new QueueFIFO<>();
    SearchBehaviour searchBehaviour = new ContainsBehaviour();
    TextField textoNodo;

    @Override
    public void start(Stage stage) throws Exception {
        Group root = initializeRoot();

        Scene scene = new Scene(root, 450, 400);
        stage.setScene(scene);
        stage.show();
        textoNodo.requestFocus();
    }

    private Group initializeRoot(){
        Group root = new Group();

        addControls(root);
        return root;
    }

    private void addControls(Group root){
        GridPane grid = new GridPane();
        textoNodo = new TextField("1");
        Button searchButton = new Button("Search");
        searchButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                String text = textoNodo.getText();
                int foundIndex = queueFIFO.search(searchBehaviour, text);
                System.out.println("foundIndex: " + foundIndex);
                listView.requestFocus();
                listView.getFocusModel().focus(foundIndex);
            }
        });
        Button addButton = new Button("Add");
        EventHandler<ActionEvent> addEventHandler = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                String text = textoNodo.getText();
                textoNodo.setText("");
                queueFIFO.add(text);
                textoNodo.requestFocus();
            }
        };
        addButton.setOnAction(addEventHandler);
        Button deleteButton = new Button("Delete");
        deleteButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                int focusedIndex = listView.getFocusModel().getFocusedIndex();
                if (focusedIndex > -1) {
                    queueFIFO.remove(focusedIndex);
                } else {

                }
                textoNodo.requestFocus();
            }
        });
        textoNodo.setOnAction(addEventHandler);
        addList(grid);
        textoNodo.setMinHeight(20);
        textoNodo.setAlignment(Pos.CENTER);
        VBox vBox = new VBox();
        HBox hBox = new HBox();
        hBox.setAlignment(Pos.CENTER_RIGHT);
        hBox.getChildren().add(searchButton);
        hBox.getChildren().add(addButton);
        hBox.getChildren().add(deleteButton);
        vBox.getChildren().add(textoNodo);
        vBox.getChildren().add(hBox);

        vBox.setAlignment(Pos.CENTER_RIGHT);
        GridPane.setValignment(vBox, VPos.CENTER);
        grid.add(vBox, 1, 0);
        root.getChildren().add(grid);
    }

    private void addList(GridPane root) {
        VBox vBox = new VBox();
        vBox.setAlignment(Pos.CENTER);
        listView = new ListView<>(queueFIFO.getItems());
        listView.maxHeight(100);
        listView.setEditable(false);
        vBox.getChildren().add(listView);
        vBox.maxHeight(100);
        GridPane.setValignment(vBox, VPos.CENTER);
        GridPane.setConstraints(vBox, 100, 100);
        root.add(vBox, 0, 0);
    }

    public static void main(String[] args) {
        Application.launch(args);
    }

}

package com.ciencias;

import com.ciencias.backend.QueueFIFO;
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
    QueueFIFO<String> queueFIFO = new QueueFIFO<>();
    TextField textoNodo;

    @Override
    public void start(Stage stage) throws Exception {
        Group root = initializeRoot();

        Scene scene = new Scene(root, 400, 300);
        stage.setScene(scene);
        stage.show();
    }

    private Group initializeRoot(){
        Group root = new Group();

        addControls(root);
        return root;
    }

    private void addControls(Group root){
        GridPane grid = new GridPane();
        textoNodo = new TextField("1");
        Button addButton = new Button("Add");
        addButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                String text = textoNodo.getText();
                System.out.println(text);
                queueFIFO.add(text);
            }
        });
        Button deleteButton = new Button("Delete");
        deleteButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                queueFIFO.remove();
            }
        });
        addList(grid);
        textoNodo.setMinHeight(20);
        textoNodo.setAlignment(Pos.CENTER);
        VBox vBox = new VBox();
        HBox hBox = new HBox();
        vBox.getChildren().add(textoNodo);
        hBox.setAlignment(Pos.CENTER_RIGHT);
        hBox.getChildren().add(addButton);
        hBox.getChildren().add(deleteButton);
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

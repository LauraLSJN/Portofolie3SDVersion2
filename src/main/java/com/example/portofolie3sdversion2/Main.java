package com.example.portofolie3sdversion2;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

public class Main extends Application {
    private Model model=new Model();
    private Controller con=new Controller(model,this);
    private TextField field=new TextField();
    private TextArea area=new TextArea();
    void setArea(String s){area.setText(s);}
    void clearField(){field.setText("");}


    @Override
    public void start(Stage stage) {
        ArrayList<String> liste = new ArrayList<>(model.readListOfHabourNames());

        //From Port combobox
        Label lab1 = new Label("From Port");
        ComboBox<String> comboFromPort = new ComboBox<>();
        for(String fromHarbour: liste){
            comboFromPort.getItems().add(fromHarbour);
        }

        //From port combobox
        Label lab2 = new Label("To Port");
        ComboBox<String> comboToPort = new ComboBox<>();
        for(String toHarbour: liste){
            comboToPort.getItems().add(toHarbour);
        }


        //Number of containers
        Label lab3 = new Label("Number of containers");
        TextField fld = new TextField();
        Button srch = new Button("Search");
        TextArea res = new TextArea();

        srch.setOnAction(e -> res.setText("Pressed\n" //e er en nameless funktion, som Java har (Lambda notation)
                + comboFromPort.getValue()
                +"\n" +comboToPort.getValue()
                +"\n" + fld.getText()
        )); //Tager event state fra button og skriver "Press" i textArea

        GridPane pane1 = new GridPane();
        BorderPane root = new BorderPane();
        lab1.setPrefSize(150,30);
        lab2.setPrefSize(150,30);
        lab3.setPrefSize(150,30);
        srch.setStyle("-fx-font:22 arial; -fx-base: rgb(101,225,59);" +
                "-fx-text-fill: rgb(255,255,255);");


        pane1.add(lab1,1,1);
        pane1.add(comboFromPort,1,2);
        pane1.add(lab2,2,1);
        pane1.add(comboToPort,2,2);
        pane1.add(lab3,3,1);
        pane1.add(fld,3,2);



        root.setTop(pane1);
        root.setCenter(srch);
        root.setBottom(res);

        //VBox root = new VBox(lab1,comboFromPort,lab2,comboToPort,lab3,fld,srch,res);


        //Group root = new Group(); // the root is Group or Pane
        Scene scene = new Scene(root, 500, 500, Color.PINK);
        stage.setTitle("JavaFX Demo");
        stage.setScene(scene);
        stage.show();




    }

    public static void main(String[] args) {
        launch();
    }
}


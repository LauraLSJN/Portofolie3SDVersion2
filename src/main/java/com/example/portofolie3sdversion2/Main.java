package com.example.portofolie3sdversion2;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import java.util.ArrayList;


public class Main extends Application {
    private TextArea area=new TextArea();
    private Model model=new Model();
    private Controller controller =new Controller(model,this);
    private TextField field=new TextField();

    void setArea(String s){area.setText(s);}
    void clearField(){field.setText("");}


    @Override
    public void start(Stage stage) {
        ArrayList<String> liste = new ArrayList<>(model.readListOfHabourNames());

        //Fra havn combobox
        Label lab1 = new Label("Fra Havn");
        ComboBox<String> comboFromHabour = new ComboBox<>();
        for(String fromHarbour: liste){
            comboFromHabour.getItems().add(fromHarbour);
        }

        //Til havn combobox
        Label lab2 = new Label("Til Havn");
        ComboBox<String> comboToHabour = new ComboBox<>();
        for(String toHarbour: liste){
            comboToHabour.getItems().add(toHarbour);
        }
        comboToHabour.getValue();

        //Antal containers tekstfelt
        Label lab3 = new Label("Antal af containers");
        TextField antalContainers = new TextField();

        //Søge knap
        Button srch = new Button("Søg");


        //Tager event state fra button og indsætter værdien af textArea -> eventlistener -> sætter værdierne
        //e er en nameless funktion, som Java har (Lambda notation)
        srch.setOnAction(e -> controller.search(comboFromHabour.getValue(),
                comboToHabour.getValue(),
                antalContainers.getText()));



        GridPane pane1 = new GridPane();
        BorderPane root = new BorderPane();
        lab1.setPrefSize(150,30);
        lab2.setPrefSize(150,30);
        lab3.setPrefSize(150,30);
        srch.setStyle("-fx-font:22 arial; -fx-base: rgb(101,225,59);" +
                "-fx-text-fill: rgb(255,255,255);");


        //Placering af combobox, og tekstfelter
        pane1.add(lab1,1,1);
        pane1.add(comboFromHabour,1,2);
        pane1.add(lab2,2,1);
        pane1.add(comboToHabour,2,2);
        pane1.add(lab3,3,1);
        pane1.add(antalContainers,3,2);



        root.setTop(pane1);
        root.setCenter(srch);
        root.setBottom(area);


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



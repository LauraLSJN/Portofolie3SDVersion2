//Anna Borg, Christine Van Wulffeld og Laura Sofie Juel Nielsen

package com.example.portofolie3sdversion2;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import java.util.ArrayList;

public class Main extends Application { //Applications er en abstrakt java klasse og muliggør grafisk user interface (GUI)
    private TextArea area=new TextArea(); //kommer fra aplication
    private Model model=new Model(); //Egen klasse
    private Controller controller =new Controller(model,this); //Egen klasse, som tager argumenter: model,


    void setArea(String s){
        area.setText(s);
    } //Sætter teksen i TextArea


    @Override //overrider fra Application
    public void start(Stage stage) { //obs. bliver den brugt?
        ArrayList<String> liste = new ArrayList<>(model.readListOfHabourNames()); //Liste er en liste af havne navne fra model

        //Fra havn combobox
        Label lab1 = new Label("Fra Havn");
        ComboBox<String> comboFromHabour = new ComboBox<>(); //ComboBox metode i application
        for(String fromHarbour: liste){
            comboFromHabour.getItems().add(fromHarbour); //det er fra havne som vi tilføjer
        }

        //Til havn combobox - samme som ovenstående bare til havne istedet
        Label lab2 = new Label("Til Havn");
        ComboBox<String> comboToHabour = new ComboBox<>();
        for(String toHarbour: liste){
            comboToHabour.getItems().add(toHarbour);
        }
        //comboToHabour.getValue(); //Kan slettes

        //Antal containers tekstfelt hvor man har mulighed for at skrive noget
        Label lab3 = new Label("Antal af containers");
        TextField antalContainers = new TextField();

        //Søge knap
        Button srch = new Button("Søg");


        //Tager event state fra button og indsætter værdien af textArea -> eventlistener -> sætter værdierne
        //e er en nameless funktion, som Java har (Lambda notation)
        //Når der trykkes skal der søges - ser om der er en handling eller ej - hvis der er skal
        //Den gå ind i controller klassen og tage de 3x parametre.
        srch.setOnAction(e -> controller.search(comboFromHabour.getValue(),
                comboToHabour.getValue(),
                antalContainers.getText()));


        //Layout
        GridPane pane1 = new GridPane(); //Grids
        BorderPane root = new BorderPane(); //Borders på user interface
        lab1.setPrefSize(150,30);
        lab2.setPrefSize(150,30);
        lab3.setPrefSize(150,30);
        //styler srch knap
        srch.setStyle("-fx-font:22 arial; -fx-base: rgb(101,225,59);" +
                "-fx-text-fill: rgb(255,255,255);");


        //Placering af combobox, og tekstfelter
        pane1.add(lab1,1,1);
        pane1.add(comboFromHabour,1,2);
        pane1.add(lab2,2,1);
        pane1.add(comboToHabour,2,2);
        pane1.add(lab3,3,1);
        pane1.add(antalContainers,3,2);

        //Placering af plane1, srch og area ved root.
        root.setTop(pane1);
        root.setCenter(srch);
        root.setBottom(area); //setBottom er bunden


        Scene scene = new Scene(root, 500, 500); //sætter størrelsen på det user interface som kommer frem
        stage.setTitle("Portofolio 3 SD");
        stage.setScene(scene);
        stage.show();

    }

    public static void main(String[] args) {
        launch();
    }
}
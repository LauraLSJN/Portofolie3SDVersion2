//Dele af denne kode er implementeret fra Mads Rosendahls kode. Denne kode er givet i forelæsning JavaFX Programming
package com.example.portofolie3sdversion2;
import java.util.ArrayList;

class Controller{
    Model model;
    Main view;

    //Konstruktør
    Controller(Model model, Main view){
        this.model=model;
        this.view=view;
    }

    //Deafult public
    void search(String comboFromHabour, String comboToHabour, String antalContainer){

        //Videresender værdierne fra parameteren til model.readSearchVessel, og ligges i en ArrayList<String> res
        ArrayList<String> res = model.readSearchVessel(comboFromHabour, comboToHabour, antalContainer); //readSearchVessel --> metode fra Model klassen
        System.out.println("Res: " + res); //Printer ud i konsol, test til os selv

        if(res.size() == 0 ) { //Hvis res.size er lig med 0, så udskrives "intet ledigt" til brugeren
            view.setArea("Intet ledigt");
        } else { //Ellers hent liste med ledige Vessels
            System.out.println(res); //Print konsol, test til os selv
           ArrayList<String> transportID = model.searchTransport(res.get(0));  //0 da det henter første element i listen som er ledigt - vi printer første navn som er ledigt
            //Vi kan godt have flere transporter fra og til den havn på listen, men vi skal bare have den første, derfor get 0
            String areaTekst = transportID.get(0); //her ligger vi elementet ind i en string, som bruges til at printe ud i view
            view.setArea(areaTekst);

            //Tilføjer ekstraFlow
            model.addExtraFlow(res.get(0),antalContainer); //Hvis der er en som er ledig, bliver det tilføjet til databasen med det samme.
            System.out.println(areaTekst + ": " + antalContainer); //Print til konsol, test til os selv

        }
    }

}

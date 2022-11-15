package com.example.portofolie3sdversion2;

import java.util.ArrayList;

class Controller{
    Model model;
    Main view;
    Controller(Model model, Main view){
        this.model=model;
        this.view=view;
    }


    void search(String comboFromPort, String comboToPort, String antalContainer){

        //Videresender værdierne fra parameteren til model.readSearchVessel, og ligges i en ArrayList<String> res
        ArrayList<String> res = model.readSearchVessel(comboFromPort, comboToPort, antalContainer);
        System.out.println("Res: " + res); //Printer ud i konsol, test til os selv

        if(res.size() == 0 ) { //Hvis res.size er lig med 0, så udskrives "intet ledigt" til brugeren
            view.setArea("Intet ledigt");
        } else { //Ellers hent liste med ledige Vessels
            System.out.println(res); //Print konsol, test til os selv
           ArrayList<String> transportID = model.searchTransport(res.get(0));  //0 da det henter første element i listen som er ledigt - vi printer første navn som er ledigt
            String areaTekst = transportID.get(0); //her ligger vi elementet ind i en string
            view.setArea(areaTekst);

            //Tilføjer ekstraFlow
            model.addExtraFlow(res.get(0),antalContainer);
            System.out.println(areaTekst + antalContainer); //Print til konsol, test til os selv

        }






        //System.out.println(model.readSearchVessel(comboFromPort, comboToPort, antalContainer));






        //hvis arraylisten == model is -> system print ln

        //Kalder metode i model, som returnere data
        //String res = model.Search(fromP, toP, antal) -> Sender resultat tilbage fra view
        //Kalder model search i controller -> Smider tre parametre ned i

        //Model search, må skal gerne returnere arrayList/String
    }


}

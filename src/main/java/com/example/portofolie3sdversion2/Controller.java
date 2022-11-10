package com.example.portofolie3sdversion2;


//tjeks and bounds for hvad man sender videre

import java.util.ArrayList;

class Controller{
    Model model;
    Main view;
    Controller(Model model, Main view){
        this.model=model; this.view=view;
    }
    void initArea(){
        String toarea="";
        for(String t:model.get())toarea+=t+"\n";
        view.setArea(toarea);
    }
    void enterText(String s){
        model.add(s);
        view.clearField();
        String toarea="";
        for(String t:model.get())toarea+=t+"\n";
        view.setArea(toarea);
    }


    void search(String comboFromPort, String comboToPort, String antalContainer){

        //view.setArea("det virker fra controller");
        //ArrayList<String> res = new ArrayList<>(model.readSearchVessel(comboFromPort, comboToPort, antalContainer));
        System.out.println(model.readSearchVessel(comboFromPort, comboToPort, antalContainer));






        //hvis arraylisten == model is -> system print ln

        //Kalder metode i model, som returnere data
        //String res = model.Search(fromP, toP, antal) -> Sender resultat tilbage fra view
        //Kalder model search i controller -> Smider tre parametre ned i

        //Model search, må skal gerne returnere arrayList/String
    }


}

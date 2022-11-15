package com.example.portofolie3sdversion2;

import java.util.ArrayList;

class Model{
    MyDB db=new MyDB();
    private MyDB db1;

    //Controller controller = new Controller();
    Model(){
        //db.cmd("drop table if exists lst1;");
        db.cmd("create table if not exists lst1 "+
                "(fld1 integer primary key autoincrement, fld2 text);");
    }
    void add(String s){ // remember to sanitize your data!
        db.cmd("insert into lst1 (fld2) values ('"+s+"');");
    }

    ArrayList<String> get(){
        return db.query("select fld2 from lst1 order by fld1;","fld2");
    }


    //Extract a list of ports for combo boxes query
   ArrayList<String>  readListOfHabourNames() {
       return db.query("select name from habour;", "name");
    }

    //Søger efter vessel med ledig kapacitet
    //SQL Query henter vesselName som kører fra og til valgte port med angivet containers hvis eksisterende container + indtastet container er lavere end kapacitet
    ArrayList<String> readSearchVessel(String comboFromPort, String comboToPort, String antalContainers){
        System.out.println(comboFromPort + " " + comboToPort + " " + antalContainers);

        //ændret v.name as vessel -> v.name as vesselName
        return db.query(
                "select t.tid as TransportID, fromHabour.name as fromport, toHabour.name as toport, v.name as vesselName, Sum(f.containers) as antalContainer, v.capacity as containerCapacity "
                + " from transport t "
                + " inner join vessel v on t.vessel = v.vid "
                + " inner join habour fromHabour on t.fromhabour = fromHabour.hid "
                + " inner join habour toHabour on t.tohabour = toHabour.hid "
                + " left outer join flow f on t.tid = f.transport "
                + " where fromport = '" + comboFromPort +"' and toport = '" + comboToPort + "' "
                + "group by t.tid "
                + " having antalContainer + "+antalContainers+" <= v.capacity ", "TransportID");
    }

    //SQL query som henter Vessel name ud fra TransportID (fra readSearchVessel metode)
    ArrayList<String> searchTransport(String transportID){
        return db.query(  "select v.name as vesselName, fromHabour.name as fromport, toHabour.name as toport"
                + " from transport t"
                + " inner join vessel v on v.vid = t.vessel"
                + " inner join habour fromHabour on fromHabour.hid = t.fromhabour "
                + " inner join habour toHabour on toHabour.hid = t.tohabour "
                + " WHERE t.tid = " + transportID +";", "vesselName" );
    }

    //Tilføjer ny kolonne til flowtabel med transportid og antalcontainers til flow der generere et nytflow id automatisk
    void addExtraFlow (String transportID, String antalContainers){
        db.cmd("INSERT INTO flow(transport,containers) VALUES (" + transportID + "," + antalContainers+");");
    }




}

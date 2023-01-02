//Dele af denne kode er implementeret fra Mads Rosendahls kode. Denne kode er givet i forelæsning JavaFX Programming

package com.example.portofolie3sdversion2;

import java.util.ArrayList;

class Model{
    MyDB db=new MyDB(); //Nyt objekt af MyDB. Vi kunne anvende Singleton.


    //Henter liste af habour til combobox query - Query er når vi henter data fra databasen.
   ArrayList<String>  readListOfHabourNames() {
       return db.query("SELECT name FROM habour;", "name");
    }

    //Søger efter vessel med ledig kapacitet
    //SQL Query henter vesselName som kører fra og til valgte port med angivet containers hvis eksisterende container + indtastet container er lavere end kapacitet
    ArrayList<String> readSearchVessel(String comboFromHabour, String comboToHabour, String antalContainers){
        return db.query(
                //Where har man altid før group by
                //Having er altid efter group by - De to gør det samme
                "select t.tid as TransportID, fromHabour.name as fromport, toHabour.name as toport,v.name as vesselName, Sum(f.containers) as antalContainer, v.capacity as containerCapacity "
                + " from transport t "
                + " inner join vessel v on t.vessel = v.vid "
                + " inner join habour fromHabour on t.fromhabour = fromHabour.hid "
                + " inner join habour toHabour on t.tohabour = toHabour.hid "
                + " left outer join flow f on t.tid = f.transport " //man kunne have brugt inner join - ikke sælig stor forskel
                + " where fromport = '" + comboFromHabour +"' and toport = '" + comboToHabour + "' "
                + "group by t.tid "
                + " having antalContainer + "+antalContainers+" <= v.capacity ", "TransportID");
    }
//ovenstående får vi bare transport id'et
    //Nedenstående får vi så navnet på det transport id.
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

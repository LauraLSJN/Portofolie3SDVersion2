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

    //Search for vessels with available capacity query
   /* ArrayList<String> readSearchVesselsWithAvailableCapacity(String comboToPort, String comboFromPort){
        return db.query("select v.vid, v.name, v.capacity, f.fid, (v.capacity-f.containers)as diff "
                +"from transport t"
                +" inner join flow f on f.fid = t.tid "
                + "   inner join habour h on h.hid = t.fromhabour"
                +"    inner join habour h2 on h2.hid = t.tohabour"
                +"  where h.name LIKE " + comboFromPort + " AND h2.name LIKE " + comboToPort + " and diff = 6000;", "v.vid");

   // return   db.query("select" +  comboToPort +  "from habour","v.name");

    }*/

    //Search for vessels with available capacity query
    /*void CMDreadSearchVesselsWithAvailableCapacity(String comboToPort, String comboFromPort) {
        db.cmd("select v.vid, v.name, v.capacity, f.fid, (v.capacity-f.containers )as diff "
                + "from transport t"
                + " inner join flow f on f.fid = t.tid "
                + "   inner join habour h on h.hid = t.fromhabour"
                + "    inner join habour h2 on h2.hid = t.tohabour"
                + "  where h.name LIKE " + comboFromPort + " AND h2.name LIKE " + comboToPort +";");
    }*/


  /*  ArrayList<String> readSearchVessel1(String comboFromPort, String comboToPort, String antalContainers){
        System.out.println(comboFromPort + " " + comboToPort + " " + antalContainers);
        return db.query("SELECT t.tid as TransportID,"
                + " FROM transport t "
                + "INNER JOIN"

        );
    }*/

    ArrayList<String> readSearchVessel(String comboFromPort, String comboToPort, String antalContainers){
        System.out.println(comboFromPort + " " + comboToPort + " " + antalContainers);

        return db.query(
                "select t.tid as TransportID, fromHabour.name as fromport, toHabour.name as toport, v.name as vessel, Sum(f.containers) as antalContainer, v.capacity as containerCapacity "
                + " from transport t "
                + " inner join vessel v on t.vessel = v.vid "
                + " inner join habour fromHabour on t.fromhabour = fromHabour.hid "
                + " inner join habour toHabour on t.tohabour = toHabour.hid "
                + " left outer join flow f on t.tid = f.transport "
                + " where fromport = '" + comboFromPort +"' and toport = '" + comboToPort + "' "
                + "group by t.tid "
                + " having antalContainer + "+antalContainers+" < v.capacity ", "TransportID"

        );



       /*return db.query(
                "SELECT t.tid AS TransportID, v.vid, v.name, v.capacity, f.fid, (v.capacity-f.containers) AS diff " +
                        "FROM transport t " +
                        "INNER JOIN flow f ON f.fid = t.tid " +
                        "INNER JOIN vessel v ON t.vessel = v.vid " +
                        "INNER JOIN habour fromHarbour ON h.hid = t.fromhabour " +
                        "INNER JOINN habour toHarbour ON toHarbour.hid = t.tohabour " +
                        "WHERE fromHarbour.name LIKE '" + comboFromPort + "' AND toHarbour.name LIKE '" + comboToPort + "' AND '" + antalContainers + " < diff;"
                ,"TransportID"
       );*/



    }



    //Lav metode til nøjagtig samme erklæring som search -> Returnere tekstString istedet for void


        //Search for vessels with available capacity query
        /*ArrayList<String> readSearchVessel1(String comboFromPort, String comboToPort, String antalContainers){
        return db.query(
                "select v.vid, v.name, v.capacity, f.fid (c.capacity-f.containers) as diff " +
                "from transport t" +
                "inner join flow f on f.fid = t.tid " +
                "inner join vessel v on t.vessel = v.vid" +
                "inner join habour fromHarbour on h.hid = t.fromhabour " +
                "inner join habour toHarbour on toHarbour.hid = t.tohabour " +
                "where fromHarbour.name LIKE" + comboFromPort + "AND toHarbour.name LIKE " + comboToPort + "and" + antalContainers + "> diff;"
                ,""); //Sæt query ind her
    }*/

    /*select v.vid, v.name, v.capacity, f.fid, (v.capacity-f.containers) as diff
    from transport t
     da det er den finder fid med tid. Disse er basically ens
    inner join vessel v on t.vessel = v.vid
  "   inner join habour h on h.hid = t.fromhabour
    inner join habour h2 on h2.hid = t.tohabour"

    where h.name LIKE 'Jawaharlal Nehru' AND h2.name LIKE 'Mombasa' and diff = 6000;*/




}

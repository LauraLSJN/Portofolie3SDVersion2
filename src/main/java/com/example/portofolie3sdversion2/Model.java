package com.example.portofolie3sdversion2;

import java.util.ArrayList;

class Model{
    MyDB db=new MyDB();
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

    void readSearchVessel(){
        return db.query(); //Sæt query ind her
    }




}

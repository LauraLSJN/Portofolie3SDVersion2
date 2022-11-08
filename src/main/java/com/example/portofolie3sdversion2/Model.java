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

    void readHarbourNames () {
        db.cmd("select name from habour");


    }



   ArrayList<String>  readListOfHabourNames() {
       return db.query("select name from habour;", "name");
    }


}

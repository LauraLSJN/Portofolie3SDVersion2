//Dele af denne kode er implementeret fra Mads Rosendahls kode. Denne kode er givet i forelæsning JavaFX Programming

package com.example.portofolie3sdversion2;

import java.sql.*;
import java.util.ArrayList;

class MyDB{
    Connection conn = null;
    MyDB(){
        if(conn==null)open();
    }
    public void open(){
        try {
            String url = "jdbc:sqlite:identifier.sqlite";
            conn = DriverManager.getConnection(url);
            System.out.println("Åbner");
        } catch (SQLException e) {
            System.out.println("Kan ikke åbne");
            if (conn != null) close();
        };
    }
    public void close(){
        try {
            if (conn != null) conn.close();
        } catch (SQLException e ) {
            System.out.println("Kan ikke åbne");
        }
        conn=null;
    }
    public void cmd(String sql){ //cmd anvendes til insert
        if(conn==null)open();
        if(conn==null){System.out.println("Ingen forbindelse");return;}
        Statement stmt=null;
        try {
            stmt = conn.createStatement();
            stmt.executeUpdate(sql);
        } catch (SQLException e ) {
            System.out.println("Fejl i SQL Statement "+sql);
        }
        try {
            if (stmt != null) { stmt.close(); }
        } catch (SQLException e ) {
            System.out.println("EFejl i SQL Statement "+sql);
        }
    }
    public ArrayList<String> query(String query, String fld){ //query anvendes til select
        ArrayList<String> res=new ArrayList<>();
        if(conn==null)open();
        if(conn==null){System.out.println("Ingen forbindelse");return res;}
        Statement stmt=null;
        try {
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                String name = rs.getString(fld);
                res.add(name);
            }
        } catch (SQLException e ) {
            System.out.println("Fejl i SQL Statement "+query+" "+fld);
        }
        try {
            if (stmt != null) { stmt.close(); }
        } catch (SQLException e ) {
            System.out.println("Fejl i SQL Statement "+query+" "+fld);
        }
        return res;
    }
}

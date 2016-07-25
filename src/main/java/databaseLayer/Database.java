package databaseLayer;

/**
 * Created by belmin on 21.07.2016..
 */


import org.json.JSONObject;

import java.sql.*;
import java.util.ArrayList;


public class Database implements IDatabaseLayer {
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static String DB_URL  = "jdbc:mysql://localhost/socketchat";;
    static String USER ="root";
    static String PASS ="1234";
    static Boolean IsTableCreated = true; //  PROMIJENITI VRIJEDNOST NA FALSE




    Connection conn = null;
    Statement stmt = null;

    public Database() {
        // Konstruktor

    }


    // USPOSTAVLJANJE KONEKCIJE SA BAZOM

    public void start() {
        if(conn == null) {
            try {
                Class.forName("com.mysql.jdbc.Driver");

                conn = DriverManager.getConnection(DB_URL, USER, PASS);

                System.out.println("Connected to the database!");

            }catch(SQLException se){
                se.printStackTrace();
            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }


    // KREIRANJE TABELE poruke
    public void create() {
        if(conn!=null) {
            try {
                stmt = conn.createStatement();
                String sql;
                sql = "CREATE TABLE IF NOT EXISTS`socketchat`.`poruke` "+
                        "(`id` INT NOT NULL AUTO_INCREMENT,"+
                        "`user` VARCHAR(45) NOT NULL,"+
                        "`poruka` VARCHAR(144) NOT NULL,"+
                        "PRIMARY KEY (`id`));";
                stmt.executeUpdate(sql);
                System.out.println("Created table in database");
                IsTableCreated = true;
            }catch(SQLException se){
                se.printStackTrace();
            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }


    // DODAVANJE PORUKE
    public void addMsg(String sender, String msg) {
        if(conn!=null && IsTableCreated == true) {
            try {
                stmt = conn.createStatement();
                String sql = "INSERT INTO poruke (user, poruka) VALUES (\"" + sender+"\",\"" + msg + "\")";
                stmt.executeUpdate(sql);
                System.out.println("Inserted records into the table...");
            }catch(SQLException se){
                se.printStackTrace();
            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }

    // ISPISIVANJE SVIH PORUKA
    public void dajPoruke() {
        if(conn!=null) {
            ArrayList<MsgToDb> poruke = new ArrayList<MsgToDb>();
            try {
                stmt = conn.createStatement();
                String sql;
                sql = "SELECT user, poruka FROM poruke";
                ResultSet rs = stmt.executeQuery(sql);

                while(rs.next()){
                    String user  = rs.getString("user");
                    String poruka = rs.getString("poruka");

                    poruke.add(new MsgToDb(user,poruka));
                }
                // return poruke;
                rs.close();
                stmt.close();
                conn.close();
            }catch(SQLException se){
                se.printStackTrace();
            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }

    // DODAVANJE PREKIDANJE KONEKCIJE
    public void stop() {
        if (conn != null) {
            try{
                if(stmt!=null)
                    stmt.close();
            }catch(SQLException se2){
            }
            try{

                if(conn!=null)
                    conn.close();

            }catch(SQLException se){
                se.printStackTrace();
            }
        }
        conn = null;
        // System.out.println("Doviđenja!");

    }
}

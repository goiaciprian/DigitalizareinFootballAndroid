package com.OlimpiaComarnic.Backend.utils;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoDatabase;


/**
 * Clasa care realizeaza conexiunea la baza de date
 */
public class DBConnection {

    private final static String connStr = "mongodb+srv://application:SldBoKoldBDTLYFp@sdapcluster.5axsh.mongodb.net/projectDB?retryWrites=true&w=majority";
    private static volatile MongoClient con = null;
    private static volatile MongoDatabase db = null;

    /**
     * Metoda de creare a conexinii catre baza de date
     *
     * @return MongoDatabase, daca conexiune a fost cu success, si NULL, daca a aparut o erroare
     */
    public synchronized static MongoDatabase getDatabase() {

        try {
            if (con == null)
                createConn();

            db = con.getDatabase("projectDB");
            return db;
        } catch (Exception ex) {
            System.out.println("Erroare in conectare: " + ex.getMessage());
        }
        return null;
    }

    public synchronized static void createConn() {
        try {
            if (con == null) {
                MongoClientURI url = new MongoClientURI(connStr);
                con = new MongoClient(url);
            }
        } catch (Exception ex) {
            System.out.println("Erroare in conectare: " + ex.getMessage());
        }
    }

    /**
     * Metoda de inchidere a conexiunii catre bazade date
     */
    public static synchronized void closeConn() {
        try {
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
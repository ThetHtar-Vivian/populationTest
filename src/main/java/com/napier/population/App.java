package com.napier.population;

public class App {
    public static void main(String[] args) {
        DbConnection con = new DbConnection();
        con.connect();
        con.disconnect();
    }
}

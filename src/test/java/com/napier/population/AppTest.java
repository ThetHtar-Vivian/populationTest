package com.napier.population;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import java.sql.Connection;

import static org.junit.jupiter.api.Assertions.*;

class AppTest {

    DbConnection db;
    Connection con;
    PopulationReport report;

    @BeforeEach
    void setUp() {
        db = new DbConnection();// Establish connection to the database
        con = db.connect("localhost:33060", 10000, "com.mysql.cj.jdbc.Driver");
        report = new PopulationReport(con);
    }

    @AfterEach
    void tearDown() {
        db.disconnect();
    }
}
package com.napier.population;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;

import static org.junit.jupiter.api.Assertions.*;

class DbConnectionTest {

    DbConnection db;
    Connection con;

    @Test
    void testConnectSuccess() {
        DbConnection db = new DbConnection();
        Connection con = db.connect("localhost:33060", 10000);
        assertNotNull(con);
    }

    @Test
    void testConnectFailure() {
        DbConnection db = new DbConnection();
        Connection con = db.connect("invalid:3306", 0);
        assertNull(con); // after 10 failed attempts
    }

    @Test
    void testInterruptedDuringSleep() {
        DbConnection db = new DbConnection();
        Thread.currentThread().interrupt(); // force interruption

        Connection con = db.connect("invalid:3306", 1000);
        // interruption does not kill test, but covers the catch block
        assertNull(con);
    }

    @Test
    void testDisconnectSuccess() {
        DbConnection db = new DbConnection();
        db.connect("localhost:33060", 10000);
        int check = db.disconnect();
        assertEquals(1, check);
    }

    @Test
    void testDisconnectWhenNull() {
        DbConnection db = new DbConnection();
        assertEquals(0, db.disconnect());
    }
}
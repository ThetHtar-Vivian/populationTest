package com.napier.population;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.lang.reflect.Field;
import java.sql.Connection;

import static org.junit.jupiter.api.Assertions.*;

class DbConnectionTest {

    @Test
    void testConnectSuccess() {
        DbConnection db = new DbConnection();
        Connection con = db.connect("localhost:33060", 10000, "com.mysql.cj.jdbc.Driver");
        assertNotNull(con, "Expected non-null connection for valid parameters");
    }

    @Test
    void testConnectFailure() {
        DbConnection db = new DbConnection();
        Connection con = db.connect("invalid:3306", 0, "com.mysql.cj.jdbc.Driver");
        assertNull(con, "Expected null connection after failed attempts with invalid host");
    }

    @Test
    void testDriverClassNotFound() {
        DbConnection db = new DbConnection();

        assertThrows(RuntimeException.class, () -> {
            try {
                db.connect("localhost:33060", 0, "invalid.DriverClass");
            } catch (RuntimeException ex) {
                if (!(ex.getCause() instanceof ClassNotFoundException)) {
                    throw new AssertionError("Expected cause to be ClassNotFoundException", ex);
                }
                throw ex; // rethrow to satisfy assertThrows
            }
        }, "Expected RuntimeException when driver class is not found");
    }

    @Test
    void testInterruptedDuringSleep() {
        DbConnection db = new DbConnection();
        Thread.currentThread().interrupt(); // force interruption

        Connection con = db.connect("invalid:3306", 1000, "com.mysql.cj.jdbc.Driver");
        assertNull(con, "Expected null connection if thread is interrupted during retry sleep");
    }

    @Test
    void testDisconnectSuccess() {
        DbConnection db = new DbConnection();
        db.connect("localhost:33060", 10000, "com.mysql.cj.jdbc.Driver");
        int check = db.disconnect();

        assertEquals(1, check, "Expected disconnect to return 1 for successful close");
    }

    @Test
    void testDisconnectWhenNull() {
        DbConnection db = new DbConnection();
        assertEquals(0, db.disconnect(), "Expected disconnect to return 0 if connection is null");
    }

    @Test
    void testDisconnectException() throws Exception {
        DbConnection db = new DbConnection();

        // Create a mock connection
        Connection mockCon = Mockito.mock(Connection.class);

        // Force close() to throw an exception
        Mockito.doThrow(new RuntimeException("close failed"))
                .when(mockCon).close();

        // Inject the mock connection into db
        Field field = DbConnection.class.getDeclaredField("con");
        field.setAccessible(true);
        field.set(db, mockCon);

        // Call disconnect() → should hit catch block and return 0
        int result = db.disconnect();

        assertEquals(0, result, "Expected disconnect to return 0 if close() throws exception");
    }

}

// File:    TestDB.java
// Created: 15/05/2015

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author mahefa
 */
public class TestDB {

    public TestDB() {
    }

    public static void main(String[] args) {
        DBAccess dbAccess = new DBAccess("root", "t00r");
        ResultSet res = dbAccess.executeQuery("show databases;");
        try {
            while (res.next()) {
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}

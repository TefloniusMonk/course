package com.young.fighter.course.backend.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseUtil {
    private static final String URL = "jdbc:postgresql://localhost:5432/db";
    private static final String PASSWORD = "postgres";
    private static final String USER = "postgres";
    private static final String TRUNCATE =
            "truncate table course.basket_products, " +
                    "            course.bill, " +
                    "            course.basket, course.customer, " +
                    "            course.catalog_products, " +
                    "            course.catalog, " +
                    "             course.product cascade;";

    public static void clearDb() {
        try {
            Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
            Statement statement = connection.createStatement();
            statement.execute(TRUNCATE);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}

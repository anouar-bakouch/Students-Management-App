package com.gl.todo_ameliored_version.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySql {

    private static MySql instance = null;
    private final String USER = "root";
    private final String PASSWORD = "password";
    private final String URL = "jdbc:mysql://localhost:3307/db_?characterEncoding=utf8";


    private MySql(){}

    public static MySql getInstance(){
        if(instance == null) instance = new MySql();
        return instance;
    }

    public Connection getConnection() throws SQLException {

        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch(Exception e){
            e.printStackTrace();
            System.out.println(e.getMessage());
        }

        return DriverManager.getConnection(URL,USER,PASSWORD);

    }


}

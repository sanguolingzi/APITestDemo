package cn.com.formycat;

import com.google.common.collect.Lists;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.List;

public class DBConnection {

    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost:8066/TESTDB";
    static final String USER = "root";
    static final String PASS = "123456";
    private List<Connection> pool = Lists.newArrayList();

    public DBConnection(int poolSize){
        pool = new ArrayList(poolSize);
    }

    public DBConnection(){
    }

    public void init() throws Exception{
        Class.forName(JDBC_DRIVER);
    }


    public Connection getConnection() throws Exception{
            return  DriverManager.getConnection(DB_URL,USER,PASS);
    }
}

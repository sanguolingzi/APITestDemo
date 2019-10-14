package cn.com.formycat;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class TestMain {

    public static void main(String[] s)throws Exception{
        Statement stmt = null;
        Connection conn = null;
        try{
            DBConnection dbConnection = new DBConnection();
            conn = dbConnection.getConnection();
            stmt = conn.createStatement();
            String sql;
            sql = "SELECT * FROM f_user";
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next()){
                System.out.println(rs.getString(1));
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            stmt.close();
            conn.close();
        }

    }



}

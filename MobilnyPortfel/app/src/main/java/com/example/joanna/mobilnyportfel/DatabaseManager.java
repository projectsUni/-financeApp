package com.example.joanna.mobilnyportfel;
//STEP 1. Import required packages
import android.util.Log;

import java.sql.*;

public class DatabaseManager {
    // JDBC driver name and database URL
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "sql2.freemysqlhosting.net/sql2204145";

    //  Database credentials
    static final String USER = "sql2204145";
    static final String PASS = "mM5%rS4%";

    public static void main() {
        Connection conn = null;
        Statement stmt = null;
        try{
            //STEP 2: Register JDBC driver
            Log.i("lala", "0");
            Class.forName("com.mysql.jdbc.Driver");
            Log.i("lala", "1");
            //STEP 3: Open a connection
            Log.i("lala","Connecting to database...");
            conn = DriverManager.getConnection(DB_URL,USER,PASS);


            //STEP 4: Execute a query
            Log.i("lala","Creating statement...");
            stmt = conn.createStatement();
            String sql;
            sql = "SELECT id, first, last, age FROM Employees";
            ResultSet rs = stmt.executeQuery(sql);

            //STEP 5: Extract data from result set
            while(rs.next()){
                //Retrieve by column name
                int id  = rs.getInt("id");
                int age = rs.getInt("age");
                String first = rs.getString("first");
                String last = rs.getString("last");

                //Display values
                Log.i("lala", "ID: " + id);
                Log.i("lala", ", Age: " + age);
                Log.i("lala", ", First: " + first);
                Log.i("lala", "Last: " + last);
            }



            //STEP 6: Clean-up environment
            rs.close();
            stmt.close();
            conn.close();
        }catch(SQLException se){
            //Handle errors for JDBC
            se.printStackTrace();
        }catch(Exception e){
            //Handle errors for Class.forName
            e.printStackTrace();
        }finally{
            //finally block used to close resources
            try{
                if(stmt!=null)
                    stmt.close();
            }catch(SQLException se2){
            }// nothing we can do
            try{
                if(conn!=null)
                    conn.close();
            }catch(SQLException se){
                se.printStackTrace();
            }//end finally try
        }//end try
        Log.i("lala","Goodbye!");
    }//end main
}//end FirstExample
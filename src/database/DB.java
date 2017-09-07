/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author TOKGO
 */
public abstract class DB {
    private static final String DRIVER = "com.mysql.jdbc.Driver";
    private static final String DB_URL = "jdbc:mysql://localhost:3306/steward";
    private static final String uer = "root";
    private static final String password = "123456";
    protected static Connection con = null;
    protected static PreparedStatement statement = null;
    protected static ResultSet rs=null;
    static{
        try {
            Class.forName(DRIVER);
        } catch (ClassNotFoundException ex) {}
    }
     protected  static void link(String sql) throws SQLException{ 
        con = DriverManager.getConnection(DB_URL,uer,password);
        statement = con.prepareStatement(sql);
    }
    protected static  void Close() throws SQLException{
        if(con != null)
        {
            statement.executeUpdate();
            con.close();
        }
    }
}

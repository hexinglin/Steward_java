/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Steward.database;

import static config.SystemConfig.getSTEWARDS_CONFIG;
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
    private static final String DB_URL = getSTEWARDS_CONFIG().getDBURL();
    private static final String uer = getSTEWARDS_CONFIG().getDBuser();
    private static final String password = getSTEWARDS_CONFIG().getDBPSD();
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

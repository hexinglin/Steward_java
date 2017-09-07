/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Steward.database;

import database.DB;
import java.sql.*;
import java.util.ArrayList;


/**
 *
 * @author TOKGO
 */
public class AccountDB extends DB{
    private static final String Addsql = "insert into account (id,password,xcoin,ftime) values (?,?,?,?)" ;
    private static final String AllInformsql = "select* from account";
    private static final String QueryIDsql = "select * from account where id=";
    private static final String Deletesql = "delete from account where id=";

    public static ArrayList<AccountNode> GetAllInform() throws SQLException{
        ArrayList<AccountNode> informlist = new ArrayList();
        AccountNode account = null;
        link(AllInformsql);
        rs = statement.executeQuery();
        while(rs.next()){
            account = new AccountNode();
            ToAccount(rs,account);
            informlist.add(account);
        }
        return informlist;
    }
    
    public  static AccountNode QueryID(String ID) throws SQLException{
        AccountNode account = new AccountNode();
        link( QueryIDsql+"'" +ID+"'");
        rs = statement.executeQuery();
        rs.next();
        ToAccount(rs,account);
        con.close();
        return account;
    }
    
    public static void AlterXcoin(String ID,int xcoin) throws SQLException{
        link("update account set xcoin ='"+xcoin+"' where id= '"+ ID +"'");
        Close();
    }
    
     public static void AlterFtime(String ID,int ftime) throws SQLException{
        link("update account set ftime ='"+ftime+"' where id= '"+ ID +"'");
        Close();
    }
    
    public static void DeleteAccount(String id) throws SQLException{
        link(Deletesql + "'" +id+"'");
        Close();
    }
    
    public static void AddNewAccout(String idString,String password,int xcoin,int ftime) throws SQLException{
        link(Addsql);
        statement.setString(1, idString);
        statement.setString(2, password);
        statement.setInt(3, xcoin);
        statement.setInt(4, ftime);
        Close();
    }
    
    private static void ToAccount(ResultSet rs,AccountNode account) throws SQLException{
        account.idString = rs.getString("id");
        account.password = rs.getString("password");
        account.xcoin = rs.getInt("xcoin");
        account.ftime = rs.getInt("ftime");
    }
    
}
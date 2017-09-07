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
public class AllowPackageDB extends DB{
    private static final String Addsql = "insert into allowpackage (packagename) values (?)" ;
    private static final String AllInformsql = "select* from allowpackage";
    private static final String Deletesql = "delete from allowpackage where packagename=";

    public static ArrayList<String> GetAllInform() throws SQLException{
        ArrayList<String> packagelist = new ArrayList();
        link(AllInformsql);
        rs = statement.executeQuery();
        while(rs.next()){
            packagelist.add(rs.getString("packagename"));
        }
        return packagelist;
    }
    
    
    public static void DeleteAccount(String packagename) throws SQLException{
        link(Deletesql + "'" +packagename+"'");
        Close();
    }
    
    public static void AddNewPackage(String packagename) throws SQLException{
        link(Addsql);
        statement.setString(1, packagename);
        Close();
    }
    
    
}
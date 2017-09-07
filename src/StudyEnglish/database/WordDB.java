/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package StudyEnglish.database;

import database.DB;
import java.sql.*;
import java.util.ArrayList;


/**
 *
 * @author TOKGO
 */
public class WordDB extends DB{
    private static final String Addsql = "insert into word (no,english,chinese) values (?,?,?)" ;
    private static final String AllInformsql = "select* from word";
    private static final String QueryNOsql = "select * from word where no=";
    private static final String QueryEnglishsql = "select * from word where english=";
    private static final String Deletesql = "delete from word where english=";

    public static ArrayList<WORDNode> GetAllInform() throws SQLException{
        ArrayList<WORDNode> informlist = new ArrayList();
        WORDNode word = null;
        link(AllInformsql);
        rs = statement.executeQuery();
        while(rs.next()){
            word = new WORDNode();
            ToData(rs,word);
            informlist.add(word);
        }
        return informlist;
    }
    
    public  static WORDNode QueryID(int NO) throws SQLException{
        WORDNode word = new WORDNode();
        link(QueryNOsql +"'" +NO+"'");
        rs = statement.executeQuery();
        rs.next();
        ToData(rs,word);
        con.close();
        return word;
    }
    
    public static WORDNode QueryID(String english) throws SQLException{
        WORDNode word = new WORDNode();
        link(QueryEnglishsql +"'" +english+"'");
        rs = statement.executeQuery();
        rs.next();
        ToData(rs,word);
        con.close();
        return word;
    }
    
    public static void DeleteAccount(String id) throws SQLException{
        link(Deletesql + "'" +id+"'");
        Close();
    }
    
    public static void AddNewAccout(int NO,String english, String chinese) throws SQLException{
        link(Addsql);
        statement.setInt(1, NO);
        statement.setString(2, english);
        statement.setString(3, chinese);
        Close();
    }
    
    private static void ToData(ResultSet rs,WORDNode word) throws SQLException{
        word.NO = rs.getInt("no");
        word.english = rs.getString("english");
        word.chinese = rs.getString("chinese");
    }
    
}
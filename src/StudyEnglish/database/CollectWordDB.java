/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package StudyEnglish.database;


import StudyEnglish.database.CollectWordNode;
import database.DB;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author 11315
 */
public class CollectWordDB extends DB{
    private static final String Addsql = "insert into wordcollect (english,label) values (?,?)" ;
    private static final String QueryWordsql = "select * from wordcollect where english=";
    private static final String Deletesql = "delete from wordcollect where english=";
    
    public static List<CollectWordNode> GetAllCollect() throws SQLException{
         List<CollectWordNode> informlist = new ArrayList();
          CollectWordNode wordNode =null;
        link("select * from wordcollect");
        rs = statement.executeQuery();
         while(rs.next()){
            wordNode= new CollectWordNode();
            wordNode.english = rs.getString("english");
            informlist.add(wordNode);
        }
        con.close();
        return informlist;
    }
    
    public static CollectWordNode QueryWord(String english) throws SQLException{
        CollectWordNode wordNode =new CollectWordNode();
        link(QueryWordsql +"'" +english+"'");
        rs = statement.executeQuery();
        rs.next();
        wordNode.english = rs.getString("english");
        con.close();
        return wordNode;
    }
    
    
     public static void AddNewWord(String english) throws SQLException{
        link(Addsql);
        statement.setString(1, english);
        statement.setInt(2, 0);
        Close();
    }
     
     public static void DeleteMsg(String english) throws SQLException{
        link(Deletesql + "'" +english+"'");
        Close();
    }
    
    
}

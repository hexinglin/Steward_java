/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package StudyEnglish.Practic;

import NetCommunication.NetTCP;
import StudyEnglish.database.CollectWordDB;
import StudyEnglish.database.WORDNode;
import StudyEnglish.database.WordDB;
import java.net.Socket;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import static tool.SocketTool.GetRandom;

/**
 *
 * @author 11315
 */
public class Practic {
     private final int[] seletno = new int[4];
    private final NetTCP serversCom ;
    private static final String NETHEADER = "22";
    public Practic(NetTCP serversCom) {
        this.serversCom = serversCom;
    }
    
    
    private void GePracticPromble(Socket socket){
        try {
            ArrayList<WORDNode> informlist = WordDB.GetAllInform();
            ArrayList<WORDNode> SeletList = new ArrayList<>();
            if (informlist.size()<4) 
                return;
            for (int i = 0; i < 4; i++) 
                SeletList.add(informlist.get(GetNO(i, informlist.size())));
            String sendstr =NETHEADER+"2"+ EncodeWord(SeletList);
            serversCom.Send(socket, sendstr); 
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }
    
     private String EncodeWord( ArrayList<WORDNode> SeletList){
        String string = "";
        int NoI = GetRandom(1,5);
        string += SeletList.get(NoI - 1).english+"\t";
        string += SeletList.get(0).chinese+"\t"+SeletList.get(1).chinese+"\t"+SeletList.get(2).chinese+"\t"
                +SeletList.get(3).chinese+"\t"+NoI+"\t";
        return string;
    }
    
    private int GetNO(int noI,int maxi){
        int no =GetRandom(0, maxi);
        for (int i = 0; i < noI; i++) {
            if (no == seletno[i]) {
                no =GetRandom(0, maxi);
                i=-1;
            }
        }
        seletno[noI]=no;
        return no;
    }
    
    private void CollectWord(Socket socket,String data){
         try {
             CollectWordDB.AddNewWord(data);
             serversCom.Send(socket, NETHEADER+"31");
         } catch (SQLException ex) {
             Logger.getLogger(Practic.class.getName()).log(Level.SEVERE, null, ex);
             serversCom.Send(socket, NETHEADER+"32");
         }
    }
    
    
    
     public void Recive(Socket socket ,String data){
        switch(data.charAt(0)){
            case '1':break;
            case '2':GePracticPromble(socket); break;
            case '3':CollectWord(socket, data.substring(1));break;//收藏
        }
    
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Steward;

import NetCommunication.AdhibitionBase;
import NetCommunication.NetTCP;
import Steward.database.AccountDB;
import Steward.database.AccountNode;
import java.net.Socket;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author TOKGO
 */
public class StewardServer extends AdhibitionBase{
    
    
//    private final NetTCP serversCom ;
    
     public StewardServer(char adhibitionFlag,NetTCP serversCom) {
         super(adhibitionFlag, serversCom);
//        this.serversCom = serversCom;
    }
    
     
     
     
     
    private void UpdateFtime(Socket socket ,char flag,String data){
        try {
            String[] strs = data.split("\n");
            int lastupdate = Integer.valueOf(strs[0]);
            int nowtime = Integer.valueOf(strs[1]);
            AccountNode account = AccountDB.QueryID("123");
            if (lastupdate == account.ftime) 
                AccountDB.AlterFtime("123", nowtime);
            else{
                AccountDB.AlterFtime("123", account.ftime+(nowtime -lastupdate));
                Send(socket, flag,""+(account.ftime+(nowtime -lastupdate)));
            } 
        } catch (SQLException ex) {
            Logger.getLogger(StewardServer.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    }
    
    private void BuyFtime(Socket socket ,char flag ,int buyxcoin){
        try {
            AccountNode account = AccountDB.QueryID("123");
            if (account.xcoin < buyxcoin) 
               Send(socket, flag, "20");
            else{
                AccountDB.AlterFtime("123", account.ftime + StewardTool.xcoin2ftime(buyxcoin));
                AccountDB.AlterXcoin("123", account.xcoin - buyxcoin);
                Send(socket, flag, "21");
            }
        } catch (SQLException ex) {
            Logger.getLogger(StewardServer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void GetXcionFtime(Socket socket ,char flag){
        try {
            AccountNode account = AccountDB.QueryID("123");
            Send(socket, flag, "1"+account.xcoin+"\n"+account.ftime);
        } catch (SQLException ex) {
            Logger.getLogger(StewardServer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void Run(Socket socket,char flag, String data) {
        int runtime =Integer.valueOf(data.split("\n")[0]);
        int sumruntime =Integer.valueOf(data.split("\n")[1]);
        if (runtime < sumruntime *0.7 || sumruntime < 1800) 
            return;
        try {
            AccountNode account = AccountDB.QueryID("123");
            if (sumruntime >3600) 
                AccountDB.AlterXcoin("123", account.xcoin +50);
            else
                AccountDB.AlterXcoin("123", account.xcoin +20);
        } catch (SQLException ex) {}
    }
   
    public void ActivityRequest(Socket socket,char flag, String data){
         switch(data.charAt(0)){
            case '1':GetXcionFtime(socket,flag);
                break;
            case '2': Run(socket,flag,data.substring(1));
                break;
            case '3':BuyFtime(socket,flag,Integer.valueOf(data.substring(1))); 
                break;
        }
    }
    
    
    
    @Override
    public void NetRecive(Socket socket, String data) {
        switch(data.charAt(0)){
            case '0':ActivityRequest(socket,data.charAt(0),data.substring(1)) ; 
                break;
            case '1':UpdateFtime(socket,data.charAt(0),data.substring(1));
                break;
            case '3':
                break;
        }
    }

    
}
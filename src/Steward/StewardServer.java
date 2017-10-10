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
import Steward.database.AllowPackageDB;
import config.SystemConfig;
import static config.SystemConfig.getSTEWARDS_CONFIG;
import java.net.Socket;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author TOKGO
 */
public class StewardServer extends AdhibitionBase{
    private static final String UserName = "123";
    
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
            AccountNode account = AccountDB.QueryID(UserName);
            if (lastupdate == account.ftime) 
                AccountDB.AlterFtime(UserName, nowtime);
            else{
                AccountDB.AlterFtime(UserName, account.ftime+(nowtime -lastupdate));
                Send(socket, flag,""+(account.ftime+(nowtime -lastupdate)));
            } 
        } catch (SQLException ex) {
            Logger.getLogger(StewardServer.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    }
    
    private void BuyFtime(Socket socket ,char flag ,int buyxcoin){
        try {
            AccountNode account = AccountDB.QueryID(UserName);
            if (account.xcoin < buyxcoin) 
               Send(socket, flag, "20");
            else{
                AccountDB.AlterFtime(UserName, account.ftime + getSTEWARDS_CONFIG().getFtimeMap(buyxcoin));
                AccountDB.AlterXcoin(UserName, account.xcoin - buyxcoin);
                Send(socket, flag, "21");
            }
        } catch (SQLException ex) {
            Logger.getLogger(StewardServer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void GetXcionFtime(Socket socket ,char flag){
        try {
            AccountNode account = AccountDB.QueryID(UserName);
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
            AccountNode account = AccountDB.QueryID(UserName);
            if (sumruntime >3600) 
                AccountDB.AlterXcoin(UserName, account.xcoin +50);
            else
                AccountDB.AlterXcoin(UserName, account.xcoin +20);
        } catch (SQLException ex) {}
    }
   
     private void BuyXcoin(Socket socket, char flag, int xcoin) {
        try {
            AccountNode account = AccountDB.QueryID(UserName);
            AccountDB.AlterXcoin(UserName, account.xcoin + xcoin);
            Send(socket, flag, "41");
        } catch (SQLException ex) {
           Send(socket, flag, "40");
        }
    }
    
    public void ActivityRequest(Socket socket,char flag, String data){
         switch(data.charAt(0)){
            case '1':GetXcionFtime(socket,flag);
                break;
            case '2': Run(socket,flag,data.substring(1));
                break;
            case '3':BuyFtime(socket,flag,Integer.valueOf(data.substring(1))); 
                break;
            case '4':BuyXcoin(socket,flag,Integer.valueOf(data.substring(1)));
                
        }
    }
    
     private void PackageOperation(Socket socket, char flag, String data) {
         System.out.println("sdfa"+data);
         try {
            //获取全部应用包名
            if (data.charAt(0) == '1') {
                String SendString ="";
                for(String packagename: AllowPackageDB.GetAllInform())
                    SendString += packagename+"\n";
                Send(socket, flag, SendString);
            }
            else //添加应用包名
            if (data.charAt(0) == '2') 
                AllowPackageDB.AddNewPackage(data.substring(1));
            else //删除应用包名
            if (data.charAt(0) == '3') 
                AllowPackageDB.DeleteAccount(data.substring(1));
          } catch (Exception ex) {}
    }
    
    @Override
    public void NetRecive(Socket socket, String data) {
        switch(data.charAt(0)){
            case '0':ActivityRequest(socket,data.charAt(0),data.substring(1)) ; 
                break;
            case '1':UpdateFtime(socket,data.charAt(0),data.substring(1));
                break;
            case '2':PackageOperation(socket,data.charAt(0),data.substring(1));
                break;
        }
    }


}

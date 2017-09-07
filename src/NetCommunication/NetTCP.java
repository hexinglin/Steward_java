/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package NetCommunication;

import Steward.StewardServer;
import StudyEnglish.OperationWord;
import TCP.ServersBaseClass;
import java.io.IOException;
import java.net.Socket;
import java.util.HashMap;

/**
 *
 * @author 11315
 */
public class NetTCP extends ServersBaseClass{
    
    private final HashMap<Character,AdhibitionBase> adhibitionlMap;
    
    private final OperationWord operationWord = new OperationWord(this);
    
    public NetTCP() throws IOException {
        tool.SocketTool.IsDebug = true;
        this.CreatServer(6000, this);
        this.SetUTF8Mode();
        adhibitionlMap = new HashMap<>();
        adhibitionlMap.put('1', new StewardServer('1',this));
    }
    
    @Override
    protected void ClientExit(Socket socket) {
        System.out.println("连接中断");
       
    }
   

    @Override
    public void Receive(Socket client, String Data) {
        System.out.println(Data);
        if(adhibitionlMap.get(Data.charAt(0))!=null)
            adhibitionlMap.get(Data.charAt(0)).NetRecive(client, Data.substring(1));
    }

    
    @Override
    public void Receive(Socket socket, byte[] bytes, int i) {
       
    }

    @Override
    public void Accept(Socket client) {
         System.out.println("连接");
         
    }
    
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package NetCommunication;

import TCP.ServersBaseClass;
import java.net.Socket;

/**
 *
 * @author TOKGO
 */
public abstract class AdhibitionBase {
    
    protected final char adhibitionFlag;
    protected final ServersBaseClass netCom;

    
    public abstract void NetRecive(Socket socket ,String data);
    
    public AdhibitionBase(char adhibitionFlag, ServersBaseClass netCom) {
        this.adhibitionFlag = adhibitionFlag;
        this.netCom = netCom;
    }
    
    public boolean Send(Socket socket,String sendString){
        return netCom.Send(socket, ""+adhibitionFlag+sendString);
    }
    public boolean Send(Socket socket,char flag,String sendString){
        return netCom.Send(socket, ""+adhibitionFlag+flag+sendString);
    }

    public char getAdhibitionFlag() {
        return adhibitionFlag;
    }
    
}

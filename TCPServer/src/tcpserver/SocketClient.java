/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tcpserver;

import java.net.Socket;

/**
 *
 * @author 12205008
 */
public class SocketClient implements Runnable{
    private Socket connectionSocket;
    
    public SocketClient(Socket connectionSocket){
        this.connectionSocket = connectionSocket;
    }
    
    @Override
    public void run() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}

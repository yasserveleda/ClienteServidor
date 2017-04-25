/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tcpserver;

/**
 *
 * @author sumatra
 */
import java.io.*;
import java.net.*;

class TCPServer {

    private static final int BUFFER_SIZE = 4096; // 4KB

    public static void main(String argv[]) throws Exception {

        /* Cria socket do servidor */
        ServerSocket welcomeSocket = new ServerSocket(6790);
        //welcomeSocket.close();
        while (true) {
            /* Aguarda o recebimento de uma conexão. O servidor fica aguardando neste ponto até que nova conexão seja aceita. */
            Socket connectionSocket = welcomeSocket.accept();
            Thread socketClient = new Thread( new SocketClient(connectionSocket));
            socketClient.start();
        }

    }
}

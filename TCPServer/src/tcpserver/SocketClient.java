/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tcpserver;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author 12205008
 */
public class SocketClient implements Runnable {

    private Socket connectionSocket;
    private static final int BUFFER_SIZE = 1024; // 1KB

    public SocketClient(Socket connectionSocket) {
        this.connectionSocket = connectionSocket;
    }

    @Override
    public void run() {

        String clientSentence;
        String echo;

        try {
            /* Cria uma stream de entrada para receber os dados do cliente */
            BufferedReader inFromClient = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));

            /* Cria uma stream de saída para enviar dados para o cliente */
            DataOutputStream outToClient = new DataOutputStream(connectionSocket.getOutputStream());

            /* Aguarda o envio de uma mensagem do cliente. Esta mensagem deve ser terminada em \n ou \r 
            Neste exemplo espera-se que a mensagem seja textual (string). Para ler dados não textuais tente a chamada read() */
            clientSentence = inFromClient.readLine();

            /* Determina o IP e Porta de origem */
            InetAddress IPAddress = connectionSocket.getInetAddress();
            int port = connectionSocket.getPort();

            //Determina o path onde o servidor guarda os arquivos
            String pathFile = "/Users/yasser/Desktop/";

            //Recebe o nome do arquivo e adiciona no path
            String inputFile = pathFile + clientSentence;
            String respostaCliente;

            try {
                InputStream inputStream = new FileInputStream(inputFile);
                respostaCliente = "Tamanho do arquivo: " + inputStream.available();

                /* Adiciona o \n para que o cliente também possa ler usando readLine() */
                echo = respostaCliente + "\n";

                /* Envia mensagem para o cliente*/
                respostaCliente = "Tamanho do arquivo: " + inputStream.available();

                /* Adiciona o \n para que o cliente também possa ler usando readLine() */
                echo = respostaCliente + "\n";

                /* Envia mensagem para o cliente*/
                outToClient.writeBytes(echo);

                byte[] buffer = new byte[BUFFER_SIZE];

                OutputStream out = connectionSocket.getOutputStream();
                OutputStreamWriter osw = new OutputStreamWriter(out);
                BufferedWriter writer = new BufferedWriter(osw);

                while (inputStream.read(buffer) != -1) {
                    outToClient.write(buffer);
                    System.out.println(buffer);
                }

                /* Exibe, IP:port => msg */
                System.out.println(IPAddress.getHostAddress() + ":" + port + " => " + clientSentence);

                /* Encerra socket do cliente */
                connectionSocket.close();

                System.out.println("");

            } catch (IOException ex) {
                respostaCliente = "Arquivo nao encontrado";

                /* Adiciona o \n para que o cliente também possa ler usando readLine() */
                echo = respostaCliente + "\n";

                /* Envia mensagem para o cliente*/
                outToClient.writeBytes(echo);

                /* Exibe, IP:port => msg */
                System.out.println(IPAddress.getHostAddress() + ":" + port + " => " + clientSentence);

                /* Encerra socket do cliente */
                connectionSocket.close();

                System.out.println("");
            }

        } catch (IOException ex) {
            Logger.getLogger(SocketClient.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Erro de conexao");
        }

    }

}

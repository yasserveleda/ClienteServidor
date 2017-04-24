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
        String clientSentence;
        String echo;

        /* Cria socket do servidor */
        ServerSocket welcomeSocket = new ServerSocket(6790);
        //welcomeSocket.close();
        while (true) {

            /* Aguarda o recebimento de uma conexão. O servidor fica aguardando neste ponto até que nova conexão seja aceita. */
            Socket connectionSocket = welcomeSocket.accept();

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

            try (
                    InputStream inputStream = new FileInputStream(inputFile);) {
                
                respostaCliente = "Tamanho do arquivo: " + inputStream.available();
                
                /* Adiciona o \n para que o cliente também possa ler usando readLine() */
                echo = respostaCliente + "\n";
                /* Envia mensagem para o cliente*/
                outToClient.writeBytes(echo);
                
                byte[] buffer = new byte[BUFFER_SIZE];
                
                OutputStream out = connectionSocket.getOutputStream();      
                OutputStreamWriter osw = new OutputStreamWriter(out);
                BufferedWriter writer = new BufferedWriter(osw);

                writer.write(respostaCliente + "\n");
                writer.flush();    
                
                while (inputStream.read(buffer) != -1) { 
                    outToClient.write(buffer);
                    System.out.println(buffer);                  
                }

            } catch (IOException ex) {
                respostaCliente = "Arquivo nao existe";
            }
           
            /* Exibe, IP:port => msg */
            System.out.println(IPAddress.getHostAddress() + ":" + port + " => " + clientSentence);

            /* Encerra socket do cliente */
            connectionSocket.close();

            System.out.println("");
        }

    }
}

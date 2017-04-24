# ClienteServidor
Cópia de arquivos via Cliente Servidor (Java)

Servidor de arquivo multithreading

Para este exercício deve ser implementado um servidor de arquivos multithreading utilizando o protocolo da camada de transporte TCP. O servidor deverá aceitar conexões de um ou mais clientes simultaneamente e transferir os arquivos solicitados. A transferência de arquivos deve seguir o protocolo especificado abaixo:
1) O cliente inicia a conexão com o servidor.
2) Cliente envia o nome do arquivo desejado em formato string (terminando com
delimitador de linha \n);
3) Servidor servidor determina o tamanho do arquivo em bytes e envia esta informação em
formato string para o cliente (terminando com delimitador de linha \n);
4) O Servidor inicia o envio dos bytes do arquivo em porções de 1024 bytes até a conclusão
do envio do arquivo.
a) Enquanto isso o cliente recebe os bytes enviados e salva em um arquivo local.
5) Ao término da transferência do arquivo, cliente e servidor encerram a conexão. O servidor deve aguardar novas conexões.
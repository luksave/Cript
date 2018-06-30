package servidor;

/**
 *
 * @author Bruno e Lucas
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/**
 * Servidor TCP que executa na porta 9090. 
 * Quando um cliente se conecta, o servidor recupera o ID e a Chave pública do cliente
 * armazenando-a dentro de um vetor de chaves
 */
public class KeyServer {
    
    public static List<IDChave> storage = new ArrayList<>();
    
    /**
     * Executa o server.
     * FIca escutando a porta 9090
     * @param args
     * @throws java.io.IOException
     */
    public static void main(String[] args) throws IOException {
        int clientes = 0;
        
        ServerSocket listener = new ServerSocket(9090);
        
        System.out.print("Executando o servidor de chaves \nClientes:" +clientes);
        
        try {
            while (true) {
                new Gerenciador(listener.accept(), clientes++).start();
            
            }
            
        }
        finally {
            listener.close();

        }
        
    }

    private static class Gerenciador extends Thread{
        private final Socket socket;
        private final int clientNumber;

        public Gerenciador(Socket socket, int clientNumber) {
            this.socket = socket;
            this.clientNumber = clientNumber;
            
            System.out.print
                ("\n"
                +"\nNova conexão:"
                +"\nCliente " + clientNumber + "\nSocket: " + socket + "\n");
        }
        
        //Para esse cliente/thread faça:
        @Override
        public void run(){
            IDChave idchave = new IDChave();
            
            try{
                BufferedReader in = new BufferedReader(
                        new InputStreamReader(socket.getInputStream()));
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

                //Mensagem de boas vindas.
                out.println("Cliente: " + clientNumber + "\n"
                          +"Para armazenar chave:\n\t 1-ID-CHAVE\n"
                          +"Para buscar chave:   \n\t 2-ID");
                    
                //Receber a mensagem do cliente
                if (true) {
                    String[] dados;
                    String input = in.readLine();
                    
                    if (input != null){
                        //Separa a String em ID e Chave usando o caractere - como 
                        //separador
                        dados = input.split("-");
                        
                        if("1".equals(dados[0])){
                        
                            //Insere os valores lidos dentro do objeto idchave
                            idchave.setID   (dados[1]);
                            idchave.setChave(dados[2]);

                            storage.add(idchave);

                            //Informa no prompt do servidor quem foi armazenado
                            System.out.print("\nChave armazenada:"
                                           + "\nID     - " + storage.get(clientNumber).getID()
                                           + "\nChave - "  + storage.get(clientNumber).getChave() 
                                           + "\n");

                            //Mandando a mensagem para o cliente
                            out.println(  "\nChave armazenada:"
                                        + "\nID     - " + storage.get(clientNumber).getID()
                                        + "\nChave - "  + storage.get(clientNumber).getChave());

                    
                        }else if("2".equals(dados[0])){
                            
                            //Para retornar uma chave salva
                            String id = dados[1];
                            
                            for(int i = 0; i < storage.size(); i++){
                                if(id.equals(storage.get(i).getID())){
                                    //Informa no prompt do servidor quem foi armazenado
                                    System.out.print("\nChave requisitada:"
                                                   + "\nID     - " + storage.get(i).getID()
                                                   + "\nChave - "  + storage.get(i).getChave() 
                                                   + "\n");

                                    //Mandando a mensagem para o cliente
                                    out.println(  "\nChave requisitada:"
                                                + "\nID     - " + storage.get(i).getID()
                                                + "\nChave - "  + storage.get(i).getChave());

                                }
                            }
                            
                        }
                    }
                }
            }
            catch(IOException e){
                System.out.print("Erro ao lidar com o cliente "+clientNumber+"\n");
            
            }
            finally {
                try {
                    socket.close();
                
                } 
                catch (IOException e) {
                    System.out.print("Erro ao fechar socket, que que houve?\n");
                
                }    
                
                System.out.print("Conexão com o cliente " + clientNumber + " fechada");
            
            }
            
        }
    
    }

}
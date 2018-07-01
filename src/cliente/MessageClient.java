package cliente;

/**
 *
 * @author Lucas
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.security.NoSuchAlgorithmException;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import criptografia.*;
import java.security.PublicKey;


public class MessageClient {

    private BufferedReader in;
    private PrintWriter out;
    private final JFrame frame = new JFrame("Cliente de Chaves");
    private static PublicKey pKey;

    public MessageClient(String option) throws NoSuchAlgorithmException{
    
        String response;
        
        if(option.equals("1")){
            String identificador = JOptionPane.showInputDialog("Digite o identificador do remetente");
            
            StringBuffer    envio = new StringBuffer();
                        envio.append(option);
                        envio.append('-');
                        envio.append(identificador);
                        envio.append('-');
                        envio.append(AsymmetricCript.par.getPublicKey());

            out.println(envio);

            try{
                response = in.readLine();

                if(response == null || response.equals(""))
                    System.exit(0);

            }catch (IOException ex) {
                   response = "Error: " + ex;

            }

            pKey = ConvertStringToKey.stringToPublicKey(response);
        
        }else if(option.equals("2")){
            String identificador = JOptionPane.showInputDialog("Digite o identificador do usuário desejado");
            
            StringBuffer envio = new StringBuffer();
                         envio.append(option);
                         envio.append('-');
                         envio.append(identificador);

            out.println(envio);

            try{
                response = in.readLine();

                if(response == null || response.equals(""))
                    System.exit(0);

            }catch (IOException ex) {
                   response = "Error: " + ex;

            }

            pKey = ConvertStringToKey.stringToPublicKey(response);
        
        }
        
    }

    
    
    // 1 - Para enviar ID-CHAVE
    // 2 - Para resgatar CHAVE através de um ID
    public static void main(String op) throws IOException, NoSuchAlgorithmException {
        MessageClient client = new MessageClient(op);
        client.connectToServer();
        
    }
    
    public void connectToServer() throws IOException {

        //Obtem o IP do servidor.
        String serverAddress = JOptionPane.showInputDialog(frame,
            "Informe o endereço IP do servidor:",
            "Bem vindo ao servidor de chaves públicas",
            JOptionPane.QUESTION_MESSAGE);
        
        //Faz a conexão e inicializa as streams
        Socket socket = new Socket(serverAddress, 9090);
       
        in = new BufferedReader(
           new InputStreamReader(socket.getInputStream()));

        out = new PrintWriter(socket.getOutputStream(), true);      
        
    }
    
    public static PublicKey getpKey() {
        return pKey;
    
    }
    
}

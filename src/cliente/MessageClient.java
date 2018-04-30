package cliente;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 *
 * @author lucas
 */
public class MessageClient {
    
    private BufferedReader in;
    private PrintWriter out;
    private final JFrame frame = new JFrame("Cliente de Chaves");
    private JTextField dataField = new JTextField(40);
    private JTextArea messageArea = new JTextArea(8, 60);
    /**
     * Executa a aplicação Cliente. Mostra um diálogo pedindo ao cliente
     * o seu ID e sua chave pública e então se conecta com o servidor
     * para que esse armazene-as.
     */
    
    public MessageClient(){
    
        // Layout GUI
        messageArea.setEditable(false);
        frame.getContentPane().add(dataField, "North");
        frame.getContentPane().add(new JScrollPane(messageArea), "Center");
        frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        frame.setVisible(true);
        frame.setSize(200, 200);
        
        // Add Listeners
        dataField.addActionListener(new ActionListener() {
            /**
             * O usuário insere as informações no campo de texto e 
             * apertou Enter. As informações digitadas são enviadas
             * para o servidor e o usuário recebe a resposta no campo
             * messageArea.
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                out.println(dataField.getText());
                   String response;
                
                try {
                    response = in.readLine();
                 
                } catch (IOException ex) {
                       response = "Error: " + ex;
                
                }
                
                messageArea.append(response + "\n");
                
                dataField.selectAll();
                
                System.exit(0);
            }
        });
        
    }
    
    public static void main(String[] args) throws IOException {
    
        MessageClient client = new MessageClient();
        client.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        client.frame.pack();
        client.frame.setVisible(true);
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

        //Obtem a saída do servidor e coloca dentro do campo de texto 
        //messageArea
        for (int i = 0; i < 3; i++) {
            messageArea.append(in.readLine() + "\n");
        }
        
        
    }
    
}

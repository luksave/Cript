package email;

/**
 *
 * @author lucas
 */
public class Box {

    private String destinatario;
    private String assunto;
    
    //TODO - ActionEvent de login
        String User = "O que foi recuperado do frame";
        String Pass = "O que foi recuperado do frame";

        Gmail login = new Gmail(User, Pass);
    
    //TODO - Fazer o ActionEvent do envio
        //this.destinatario = "O que foi recuperado do frame";
        //this.assunto      = "O que foi recuperado do frame";
        //O construtor já realiza o envio do email
        SendEmail enviar = new SendEmail(login, destinatario, assunto);
        
    
    //TODO - Fazer o ActionEvent do recebimento
        //O construtor já faz o recebimento do email
        ReceiveEmail receber = new ReceiveEmail(login);
        
}
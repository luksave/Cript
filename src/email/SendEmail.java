package email;

import javax.activation.*;
import javax.mail.*;
import javax.mail.internet.*;

/**
 *
 * @author Bruno e Lucas
 */
public class SendEmail {
    
    public SendEmail(Gmail remetente, String destinatario, String assunto){
        
        //Destinatário
        String to  = destinatario;            
        //Assunto do Email
        String sub = assunto;
            
        try{
        
            //Cria mensagem com múltiplas partes
            MimeMessage message      = new MimeMessage(remetente.getSession());
            //Cria o corpo da mensagem
            BodyPart messageBodyPart = new MimeBodyPart();
            //Cria uma parte nova da mensagem
            Multipart multipart      = new MimeMultipart();

            //Set Remetente
            message.setFrom(new InternetAddress(remetente.getUser()));
            //Set Destinatário
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
            //Set Assunto 
            message.setSubject(sub);
            //Set "Corpo" (texto do email)
            messageBodyPart.setText("Email teste");
            //Adiciona uma parte da mensagem
            multipart.addBodyPart(messageBodyPart);

            //Anexo da mensagem:
            messageBodyPart = new MimeBodyPart();
            //Nomes dos arquivos que serão adicionados:
            String msgAES = "msgAES.txt";
            String chvAES = "chvAES.txt";
            String msgMD5 = "msgMD5.txt";
            
            //Adicionando Anexo msgAES.txt
            DataSource source = new FileDataSource(msgAES);
            messageBodyPart.setDataHandler(new DataHandler(source));
            messageBodyPart.setFileName(msgAES);
            multipart.addBodyPart(messageBodyPart);

            //Adicionando Anexo chvAES.txt
            source = new FileDataSource(chvAES);
            messageBodyPart.setDataHandler(new DataHandler(source));
            messageBodyPart.setFileName(chvAES);
            multipart.addBodyPart(messageBodyPart);

            //Adicionando Anexo msgMD5.txt
            source = new FileDataSource(msgMD5);
            messageBodyPart.setDataHandler(new DataHandler(source));
            messageBodyPart.setFileName(msgMD5);
            multipart.addBodyPart(messageBodyPart);
            
            //Anexa todas as partes
            message.setContent(multipart );

            //Envia a mensage
            Transport.send(message);
            System.out.println("Mensagem enviada\n");
            
        }

        catch(Exception e){
            System.out.print("Erro: " + e +"\n");
        }
        
    }

}

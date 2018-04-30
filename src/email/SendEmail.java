package email;

import java.util.Properties;
import javax.activation.*;
import javax.mail.*;
import javax.mail.internet.*;

/**
 *
 * @author lucas
 */
public class SendEmail {
    
    public SendEmail(Gmail remetente, String destinatario, String assunto){
        
        //Destinatário
        String to  = destinatario;            

        //Assunto do Email
        String sub = assunto;
        
        Properties prop = new Properties();
                
        prop.put("mail.smtp.ssl.trust", "smtp.gmail.com");
        prop.put("mail.smtp.auth", true);
        prop.put("mail.smtp.starttls.enable", true);
        prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", "587");
       
        Session session = Session.getInstance(prop, new javax.mail.Authenticator(){
  
            @Override
            protected javax.mail.PasswordAuthentication getPasswordAuthentication(){
           
                return new javax.mail.PasswordAuthentication(remetente.getUser(), remetente.getPass());
                
            }
 
        });
            
        try{
        
            //Cria mensagem com múltiplas partes
            MimeMessage message      = new MimeMessage(session);
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

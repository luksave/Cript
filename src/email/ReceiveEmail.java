package email;

import java.io.*;
import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;

/**
 *
 * @author lucas
 */
public class ReceiveEmail {

    private String receivingHost;
                
    public ReceiveEmail(){
    
        Properties prop = new Properties();
        
        LoginGmail login = new LoginGmail();
        
        this.receivingHost = "imaps.gmail.com";
        
        prop.put("mail.smtp.ssl.trust", "smtp.gmail.com");
        prop.put("mail.smtp.auth", true);
        prop.put("mail.smtp.starttls.enable", true);
        prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", "587");
        prop.put("mail.store.protocol", "imaps");
        
        Session session = Session.getInstance(prop, new javax.mail.Authenticator(){

            protected javax.mail.PasswordAuthentication getPasswordAuthentication(){

                return new javax.mail.PasswordAuthentication(login.getUser(), login.getPass());

            }

        });
    
        try {
            
            Store store = session.getStore("imaps");
            
            store.connect(this.receivingHost, login.getUser(), login.getPass());
            
            Folder folder = store.getFolder("https://mail.google.com/mail/u/0/#inbox");
            
            folder.open(Folder.READ_ONLY);
            
            Message message[] = folder.getMessages();
            
            for (Message message1 : message) {
                
                String contentType = message1.getContentType();
                
                //Esse email pode conter anexos
                if (contentType.contains("multipart")) {
                    Multipart multiPart = (Multipart) message1.getContent();
                
                    //Imprime o assunto do email
                    System.out.println(message1.getSubject());
                    
                    for (int j = 0; j < multiPart.getCount(); j++) {
                   
                        MimeBodyPart part = (MimeBodyPart) multiPart.getBodyPart(j);
                        
                        if (Part.ATTACHMENT.equalsIgnoreCase(part.getDisposition())) {
                            
                            //Salvando o anexo como um  arquivo
                            String caminhoAnexos = "../Anexos/" + part.getFileName();
 
                            FileOutputStream saida = new FileOutputStream(caminhoAnexos);

                            InputStream entrada = part.getInputStream();

                            byte[] buffer = new byte[4096];

                            int byteLido;
                            
                            while ((byteLido = entrada.read(buffer)) != -1) 
                                saida.write(buffer, 0, byteLido);
                            
                            saida.close();
                            
                        }
                    }
                }
            }
            
            //Fechando as conexões
            folder.close();
            store.close();
            
        } 
        catch (NoSuchProviderException ex) {
        
            System.out.print("Provedor não existe\n");
        
        } catch (MessagingException ex) {
            
            System.out.print(ex.toString());
            
        } catch (IOException ex) {
            
            System.out.print(ex.toString());
        
        }
    
    }
    
}
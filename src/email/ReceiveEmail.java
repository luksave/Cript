package email;

/**
 *
 * @author Bruno e Lucas
 */
import java.io.*;
import javax.mail.*;
import javax.mail.internet.*;

public class ReceiveEmail {
   
    public ReceiveEmail(Gmail login){
    
        try {
            
            try (Store store = login.getSession().getStore("imaps")) {
                store.connect(login.getReceivingHost(), login.getUser(), login.getPass());
                
                try (Folder folder = store.getFolder("https://mail.google.com/mail/u/0/#inbox")) {
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
                }
            }
            
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
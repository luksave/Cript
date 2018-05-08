package email;

import java.util.*;
import javax.mail.*;

/**
 *
 * @author Bruno e Lucas
 */
public class Gmail {
    
    private String user;
    private String pass;
    private String receivingHost;
    private final Session session;
    
    public Gmail(String U, String P){
        this.user = U;
        this.pass = P;
   
        Properties prop = new Properties();
        
        this.receivingHost = "imaps.gmail.com";
        
        prop.put("mail.smtp.ssl.trust", "smtp.gmail.com");
        prop.put("mail.smtp.auth", true);
        prop.put("mail.smtp.starttls.enable", true);
        prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", "587");
        prop.put("mail.store.protocol", "imaps");
        
        session = Session.getInstance(prop, new javax.mail.Authenticator(){

            @Override
            protected javax.mail.PasswordAuthentication getPasswordAuthentication(){

                return new javax.mail.PasswordAuthentication(user, pass);

            }

        });
        
    
    }

    public boolean getLogged(){
        if(session != null)
            return true;
        else
            return false;
    }
    
    public String getReceivingHost() {
        return receivingHost;
    }

    public void setReceivingHost(String receivingHost) {
        this.receivingHost = receivingHost;
    }
    
    public void setUser(String user) {
        this.user = user;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }
   
    public String getUser() {
        return user;
    }

    public String getPass() {
        return pass;
    }
    
    public Session getSession(){
        return session;
    }
    
}

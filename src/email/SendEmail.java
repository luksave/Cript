package email;

/**
 *
 * @author lucas
 */
public class SendEmail {

    private String user = "lucasclashms@gmail.com";
    private String pass = "Senha";
    
    SendMail(String Dest, String Sub, String msg){
        Properties prop = new Properties();
        prop.put("mail.smtp.ssl.trust", "smtp.gmail.com");
        prop.put("mail.smtp.auth", true);
        prop.put("mail.smtp.starttls.enable", true);
        prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", "587");
    }
    
}

package email;

/**
 *
 * @author lucas
 */
public class Gmail {
    
    private String user;
    private String pass;

    public Gmail(String U, String P){
        this.user = U;
        this.pass = P;
    
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
    
}

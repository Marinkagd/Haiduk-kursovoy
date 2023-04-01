package request.tdo;

import java.io.Serializable;

public class AuthorisationTDO implements Serializable{
    private String login;
    private byte[]  encryptedPassword;
    public AuthorisationTDO(String login, byte[] encryptedPassword) {
        this.login = login;
        this.encryptedPassword = encryptedPassword;
    }
    public String getLogin() {
        return login;
    }
    public byte[] getEncryptedPassword() {
        return encryptedPassword;
    }
    
}

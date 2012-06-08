/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package enviandoemailjava;
import java.io.IOException;
import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import mail.Email;

/**
 *
 * @author Pamela
 */
public class EnviandoEmailJava {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws NoSuchProviderException, MessagingException, IOException {
        Email.pegarEmails();
        
    }
}

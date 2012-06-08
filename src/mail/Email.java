/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mail;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.search.*;

/**
 *
 * @author Pamela
 */

public class Email
{
      public static void enviaEmail(){
            Properties props = new Properties();
            /** Parâmetros de conexão com servidor Gmail */
            props.put("mail.smtp.host", "smtp.gmail.com");
            props.put("mail.smtp.socketFactory.port", "465");
            props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.port", "465");

            Session session = Session.getDefaultInstance(props,
                        new javax.mail.Authenticator() {
                             protected PasswordAuthentication getPasswordAuthentication()
                             {
                                   return new PasswordAuthentication("pampam3434@gmail.com", "wartully");
                             }
                        });

            /** Ativa Debug para sessão */
            session.setDebug(true);

            try {

                  Message message = new MimeMessage(session);
                  message.setFrom(new InternetAddress("pampam3434@gmail.com")); //Remetente

                  Address[] toUser = InternetAddress //Destinatário(s)
                             .parse("hjhenriquesesi@gmail.com, pamela_wartully@hotmail.com");

                  message.setRecipients(Message.RecipientType.TO, toUser);
                  message.setSubject("Enviando email com JavaMail");//Assunto
                  message.setText("Enviei este email utilizando JavaMail com minha conta GMail!");
                  /**Método para enviar a mensagem criada*/
                  Transport.send(message);

                  System.out.println("Feito!!!");

             } catch (MessagingException e) {
                  throw new RuntimeException(e);
            }
      }
      
      public static void pegarEmails() throws NoSuchProviderException, MessagingException, IOException{
          // Create empty properties
            Properties props = new Properties();

            // Get session
            Session session = Session.getDefaultInstance(props, null);

            // Get the store
            Store store = session.getStore("pop3");
            store.connect("mail.construsitebrasil.com.br", "registro23@construsitebrasil.com.br", "registro23");

            // Get folder
            Folder folder = store.getFolder("INBOX");
            folder.open(Folder.READ_ONLY);

            // Get directory
            Calendar a = Calendar.getInstance();
                
            a.set(Calendar.DAY_OF_MONTH, a.get(Calendar.DAY_OF_MONTH)-1);
            a.set(Calendar.HOUR, 0);
            a.set(Calendar.MINUTE, 0);
            a.set(Calendar.MILLISECOND, 0);
            a.set(Calendar.SECOND, 0);
            
            
            SearchTerm termo = new SubjectTerm("Lembrete de renovacao");
            Message message[] = folder.search(termo, folder.getMessages(3600, 3663));

            for (int i=0, n=message.length; i<n; i++) {
                
                Calendar b = Calendar.getInstance();
                
                b.setTime(message[i].getSentDate());
                b.set(Calendar.HOUR, 0);
                b.set(Calendar.MINUTE, 0);
                b.set(Calendar.MILLISECOND, 0);
                b.set(Calendar.SECOND, 0);
                
                if(a.compareTo(b) == 0)
                    System.out.println(message[i].getSubject() + " - Data: " + new SimpleDateFormat("dd/MM/yyyy").format(message[i].getSentDate()));//+ " - Mensagem: "+message[i].getContent()
            }

            // Close connection 
            folder.close(false);
            store.close();
      }
}



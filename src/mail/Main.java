package mail;

import javax.mail.*;
import javax.mail.internet.*;
import java.util.Properties;

public class Main {
    public static void main(String[] args) throws Exception {

        Properties properties = new Properties();

        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "465");
        properties.put("mail.smtp.ssl.enable", "true");
        properties.put("mail.smtp.auth", "true");

        String username = "rixsiboyevabdurahim32@gmail.com";
        String password = "uuyljozibwzuvhks";

        Session session = getSession(properties, username, password);

        Message message = new MimeMessage(session);

        message.setSubject("This is Subject");
//        message.setText("Body of mail");  //  Text jo'natish uchun.
        message.setContent("<h1 style=\"color:green;\"> This is Test Massage !</h1>", "text/html");
        message.setFrom(new InternetAddress(username));
        message.setRecipient(Message.RecipientType.TO, new InternetAddress("rixsiboyevabdurahim215@gmail.com"));

        Transport.send(message);

    }

    private static Session getSession(Properties properties, String username, String password) {
        return Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });
    }
}
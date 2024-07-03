package practice;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Base64;
import java.util.Properties;

public class SendMassage {
    public static void main(String[] args) throws Exception {

        Properties properties = new Properties();

        properties.put("mail.smtp.host", "smtp.mailtrap.io");
        properties.put("mail.smtp.port", "465");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.auth", "true");

        String username = "6e34fe315334d2";
        String password = "c0a5bc9d1afb90";

        String email = "rixsiboyevabdurahim215@gmail.com";

        Authenticator authenticator = new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        };

        Session session = Session.getInstance(properties, authenticator);

        Message message = new MimeMessage(session);

        String audio = "E:\\Java_5_Module\\mail\\q95j7.mp3";
        String video = "E:\\Java_5_Module\\mail\\Yoshi.mp4";
        String image = "E:\\Java_5_Module\\mail\\uzb.jpg";

        String html = """
                <div>
                <h1 style=\"color:green;\"> Assalomu alaykum %s ! </h1>
                <img src="data:image/jpg;base64,%s" width=400 >
                <audio controls="controls">
                       <source src="data:audio/wav;base64, %s "/>
                </audio>
                <video width="320" height="240" controls>
                    <source src="data:video/mp4;base64, %s " type="video/mp4">
                </div>""".formatted(email, getBase64(image), getBase64(audio), getBase64(video));


        message.setContent(html, "text/html");
        message.setFrom(new InternetAddress(username));
        message.setRecipient(Message.RecipientType.TO, new InternetAddress(email));


        Transport.send(message);

    }

    public static String getBase64(String str) throws IOException {
        Path path = Path.of(str);
        byte[] bytes = Files.readAllBytes(path);
        return Base64.getEncoder().encodeToString(bytes);
    }

}

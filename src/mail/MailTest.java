package mail;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Base64;
import java.util.Properties;

public class MailTest {
    public static void main(String[] args) throws Exception {
        Properties properties = getProperties();

        String username = "6e34fe315334d2";
        String password = "c0a5bc9d1afb90";

        Session session = getSession(properties, username, password);

        Message message = new MimeMessage(session);

        Multipart multipart1 = new MimeMultipart();
        MimeBodyPart contentMassage1 = new MimeBodyPart();
        MimeBodyPart part1 = new MimeBodyPart();
        part1.setFileName("MyCv.txt");
        part1.setDataHandler(new DataHandler(new FileDataSource("E:\\Java_5_Module\\mail\\src\\cv.txt")));
        contentMassage1.setContent("<h1 style=\"color:green;\"> Hello World !</h1>", "text/html");


        MimeBodyPart part2 = new MimeBodyPart();
        MimeBodyPart contentMassage2 = new MimeBodyPart();
        part2.setFileName("Exam.txt");
        part2.setDataHandler(new DataHandler(new FileDataSource("E:\\Java_5_Module\\mail\\src\\exam.txt")));
        String html = """
                <div>
                <h1 style=\"color:green;\"> Hello World !</h1>
                <img src="data:image/jpg;base64,%s" width=400 >
                </div>""".formatted(getImageBase64());
        contentMassage2.setContent(html, "text/html");

        message.setSubject("This is Subject");
        message.setFrom(new InternetAddress(username));
        message.setRecipient(Message.RecipientType.TO, new InternetAddress("rixsiboyevabdurahim32@gmail.com"));


        multipart1.addBodyPart(part1);

        multipart1.addBodyPart(contentMassage1);
        message.setContent(multipart1);

        multipart1.addBodyPart(part2);
        multipart1.addBodyPart(contentMassage2);


        Transport.send(message);

    }

    private static String getImageBase64() throws IOException {
        Base64.Encoder encoder = Base64.getEncoder();
        Path path = Path.of("C:\\Users\\user\\Pictures\\Фоновые изображения рабочего стола\\3. Леопард.jpg");
        byte[] allBytes = Files.readAllBytes(path);
        return encoder.encodeToString(allBytes);
    }

    private static Properties getProperties() {
        Properties properties = new Properties();

        properties.put("mail.smtp.host", "smtp.mailtrap.io");
        properties.put("mail.smtp.port", "465");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.auth", "true");
        return properties;
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

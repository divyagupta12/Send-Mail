# Send-Mail
Sends mail using Java Mail API

import com.beust.jcommander.JCommander;
import com.beust.jcommander.ParameterException;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import java.io.Console;
import java.util.Properties;
import javax.mail.Transport;
import javax.activation.DataSource;
import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.internet.MimeMultipart;
import java.io.File;

public class SendEmail {

    public static void main(String args[]) {
        final Arguments aa = new Arguments();
        JCommander commands = new JCommander(aa);
        Console cnsl = System.console();
        final String password;
        String filename;

        try {
            commands.parse(args);
        } catch (ParameterException e) {
            System.out.println(e.getMessage());
            commands.usage();
            System.exit(-1);
        }
        final Properties properties = new Properties();
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.transport.protocol", "smtp");
        properties.put("mail.smtp.port", "587");


        System.out.print("password:");
        char passtring[] = cnsl.readPassword();
        password = new String(passtring);
        filename=aa.getAttachment();

        PasswordAuthentication pa = new PasswordAuthentication(aa.from, password);
        Session ses = Session.getDefaultInstance(properties, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(aa.from, password);
            }
        });

        try {
            MimeMessage msg = new MimeMessage(ses);
            msg.setFrom(new InternetAddress(aa.getFrom()));

            msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(aa.getTo()));

            msg.setSubject(aa.getSubject());

            // msg.setText(aa.getContent());
            //msg.setText(aa.getContent());

            BodyPart BodyPart = new MimeBodyPart();

            BodyPart.setText(aa.getContent());

            MimeBodyPart mimeBodyPart = new MimeBodyPart();

            DataSource source = new FileDataSource(filename);

            mimeBodyPart.setDataHandler(new DataHandler(source));

            mimeBodyPart.setFileName(filename);

            Multipart multipart = new MimeMultipart();

            multipart.addBodyPart(BodyPart);

            multipart.addBodyPart(mimeBodyPart);

            msg.setContent(multipart);

            Transport.send(msg);

            System.out.println("sent message successfully!!");
        } catch (AuthenticationFailedException ae) {
            System.out.println("username or password not correct");
        } catch (MessagingException e) {
            if (new File(filename).isDirectory()) {
                System.out.println("Can't send a  folder.Please select a file");
            } else {
                System.out.println("this file can't be sent because some file types are blocked in gmail ");
            }
            e.printStackTrace();
        }
    }
}





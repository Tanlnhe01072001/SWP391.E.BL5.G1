package controller;

import java.security.SecureRandom;
import java.util.Properties;
import java.util.Random;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import model.UserC;

/**
 *
 * @author Admin
 */
public class SendEmailCode {

    // pass:hqhg edip bajd foln
//    public String getRandom() {
//        Random rnd = new Random();
//        int number = rnd.nextInt(999999);
//        return String.format("%06d", number);
//    }
    public String getRandom() {
        // Độ dài của mã xác nhận
        int codeLength = 6;

        // Mảng chứa ký tự cho việc tạo mã xác nhận
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

        // Tạo một đối tượng StringBuilder để xây dựng mã xác nhận
        StringBuilder sb = new StringBuilder(codeLength);

        // Tạo một SecureRandom object để tạo số ngẫu nhiên
        SecureRandom random = new SecureRandom();

        // Tạo mã xác nhận ngẫu nhiên bằng cách chọn ngẫu nhiên các ký tự từ chuỗi characters
        for (int i = 0; i < codeLength; i++) {
            // Chọn một ký tự ngẫu nhiên từ chuỗi characters
            char randomChar = characters.charAt(random.nextInt(characters.length()));
            // Thêm ký tự ngẫu nhiên vào StringBuilder
            sb.append(randomChar);
        }

        // Trả về mã xác nhận đã tạo
        return sb.toString();
    }

    public boolean sendEmail1(UserC userc) {
        boolean test = false;
        String fromEmail = "nguyendangkien17999@gmail.com";
        String password = "hlfa bbpv enjc zxmj";
        String toEmail = userc.getEmail();
        try {
            Properties pr = new Properties();
            pr.setProperty("mail.smtp.host", "smtp.gmail.com"); // Thay đổi thành máy chủ SMTP thực tế
            pr.setProperty("mail.smtp.port", "587");
            pr.setProperty("mail.smtp.auth", "true");
            pr.setProperty("mail.smtp.starttls.enable", "true");
            pr.setProperty("mail.smtp.socketFactory.port", "587");
            pr.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
            //get sesion
            Session session;
            session = Session.getInstance(pr, new Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(fromEmail, password);
                }
            });
            //mesage
            Message mess = new MimeMessage(session);
            mess.setFrom(new InternetAddress(fromEmail));
            mess.setRecipient(Message.RecipientType.TO, new InternetAddress(toEmail));
            mess.setSubject("User Email Verification");
            mess.setText("Registered successfully.Please verify your account using this code " + userc.getCode());
            Transport.send(mess);
            test = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return test;
    }

    public boolean sendEmail2(UserC userc) {
        boolean test = false;
//        String fromEmail = "anpk2300@gmail.com";
//        String password = "hqhg edip bajd foln";
        String fromEmail = "nguyendangkien17999@gmail.com";
        String password = "hlfa bbpv enjc zxmj";
        String toEmail = userc.getEmail();
        try {
            Properties pr = new Properties();
            pr.setProperty("mail.smtp.host", "smtp.gmail.com"); // Thay đổi thành máy chủ SMTP thực tế
            pr.setProperty("mail.smtp.port", "587");
            pr.setProperty("mail.smtp.auth", "true");
            pr.setProperty("mail.smtp.starttls.enable", "true");
            pr.setProperty("mail.smtp.socketFactory.port", "587");
            pr.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
            //get sesion
            Session session;
            session = Session.getInstance(pr, new Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(fromEmail, password);
                }
            });
            //mesage
            Message mess = new MimeMessage(session);
            mess.setFrom(new InternetAddress(fromEmail));
            mess.setRecipient(Message.RecipientType.TO, new InternetAddress(toEmail));
            mess.setSubject("User Email Verification");
            mess.setText("Update new password successfully.Please your account using this code: " + userc.getCode());
            Transport.send(mess);
            test = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return test;
    }
}

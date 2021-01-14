/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nmjava.flight.Utility;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import nmjava.flight.SearchFlightPanel;
import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;

/**
 *
 * @author Administrator
 */
public class Mail {

    private class MailConfig {

        public static final String HOST_NAME = "smtp.gmail.com";

        public static final int SSL_PORT = 465; // Port for SSL

        public static final int TSL_PORT = 587; // Port for TLS/STARTTLS

        public static final String APP_EMAIL = "vemaybaydaily1@gmail.com"; // your email

        public static final String APP_PASSWORD = "Admin1412"; // your password
    }

    public static void send(String title, String content, String Receive_Email) {
        // Tạo đối tượng Email
        Email email = new SimpleEmail();

        Thread thread = new Thread() {
            @Override
            public synchronized void start() {
                super.start(); //To change body of generated methods, choose Tools | Templates.

                // Cấu hình thông tin Email Server
                email.setHostName(MailConfig.HOST_NAME);
                email.setSmtpPort(MailConfig.SSL_PORT);
                email.setAuthenticator(new DefaultAuthenticator(MailConfig.APP_EMAIL, MailConfig.APP_PASSWORD));
                email.setSSLOnConnect(true);

                try {
                    // Người gửi
                    email.setFrom(MailConfig.APP_EMAIL);

                    // Người nhận
                    email.addTo(Receive_Email);

                    // Tiêu đề
                    email.setSubject(title);

                    // Nội dung email
                    email.setMsg(content);
                } catch (EmailException ex) {
                    Logger.getLogger(Mail.class.getName()).log(Level.SEVERE, null, ex);
                    this.interrupt();
                }
            }

            @Override
            public void run() {
                super.run(); //To change body of generated methods, choose Tools | Templates.
                try {
                    // send message
                    email.send();
                    System.out.println("Message sent successfully");
                } catch (EmailException ex) {
                    Logger.getLogger(Mail.class.getName()).log(Level.SEVERE, null, ex);
                }

                this.interrupt();
            }

            @Override
            public void interrupt() {
                super.interrupt(); //To change body of generated methods, choose Tools | Templates.
                System.out.println("interupt process send mail");
            }
        };
        thread.start();
    }
}

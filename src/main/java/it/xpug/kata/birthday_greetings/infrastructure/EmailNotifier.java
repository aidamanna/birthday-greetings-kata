package it.xpug.kata.birthday_greetings.infrastructure;

import it.xpug.kata.birthday_greetings.application.MessageNotifier;
import it.xpug.kata.birthday_greetings.domain.Employee;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class EmailNotifier implements MessageNotifier {

  private final String smtpHost;
  private final int smtpPort;

  public EmailNotifier(String smtpHost, int smtpPort) {
    this.smtpHost = smtpHost;
    this.smtpPort = smtpPort;
  }


  public void send(Employee employee) {
    EmailMessage emailMessage= EmailMessage.generateFor(employee);
    Session session = createSession();

    try {
      Message message = buildMessage(emailMessage, session);
      Transport.send(message);
    } catch (MessagingException e) {
      //Generate a log instead
      System.out.println("Error sending the message");
    }
  }

  private Session createSession() {
    java.util.Properties props = new java.util.Properties();
    props.put("mail.smtp.host", smtpHost);
    props.put("mail.smtp.port", "" + smtpPort);
    return Session.getInstance(props, null);
  }

  private Message buildMessage(EmailMessage emailMessage, Session session)
      throws MessagingException {
    Message message = new MimeMessage(session);
    message.setFrom(new InternetAddress(emailMessage.getSender()));
    message.setRecipient(Message.RecipientType.TO,
        new InternetAddress(emailMessage.getRecipient()));
    message.setSubject(emailMessage.getSubject());
    message.setText(emailMessage.getBody());

    return message;
  }
}

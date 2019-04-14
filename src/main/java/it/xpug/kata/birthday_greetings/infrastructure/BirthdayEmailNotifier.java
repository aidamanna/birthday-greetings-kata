package it.xpug.kata.birthday_greetings.infrastructure;

import it.xpug.kata.birthday_greetings.domain.BirthdayNotifier;
import it.xpug.kata.birthday_greetings.domain.Employee;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BirthdayEmailNotifier implements BirthdayNotifier {

  private final String smtpHost;
  private final int smtpPort;

  private static Logger logger = LoggerFactory.getLogger(BirthdayEmailNotifier.class);

  public BirthdayEmailNotifier(String smtpHost, int smtpPort) {
    this.smtpHost = smtpHost;
    this.smtpPort = smtpPort;
  }


  public void send(Employee employee) {
    BirthdayEmailMessage birthdayEmailMessage = BirthdayEmailMessage.generateFor(employee);
    Session session = createSession();

    try {
      Message message = buildMessage(birthdayEmailMessage, session);
      Transport.send(message);
    } catch (MessagingException e) {
      logger.error("Error sending the message to the employee");
    }
  }

  private Session createSession() {
    java.util.Properties props = new java.util.Properties();
    props.put("mail.smtp.host", smtpHost);
    props.put("mail.smtp.port", "" + smtpPort);
    return Session.getInstance(props, null);
  }

  private Message buildMessage(BirthdayEmailMessage birthdayEmailMessage, Session session)
      throws MessagingException {
    Message message = new MimeMessage(session);
    message.setFrom(new InternetAddress(birthdayEmailMessage.getSender()));
    message.setRecipient(Message.RecipientType.TO,
        new InternetAddress(birthdayEmailMessage.getRecipient()));
    message.setSubject(birthdayEmailMessage.getSubject());
    message.setText(birthdayEmailMessage.getBody());

    return message;
  }
}

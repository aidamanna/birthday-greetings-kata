package it.xpug.kata.birthday_greetings.infrastructure;

import it.xpug.kata.birthday_greetings.domain.Employee;

public class EmailMessage {

  private final String recipient;
  private final String sender;
  private final String subject;
  private final String body;

  public static final String SENDER_EMAIL = "sender@here.com";
  public static final String SUBJECT = "Happy Birthday!";

  private EmailMessage(String recipient, String sender, String subject, String body) {
    this.recipient = recipient;
    this.sender = sender;
    this.subject = subject;
    this.body = body;
  }

  public static EmailMessage generateFor(Employee employee) {
    return new EmailMessage(
        employee.getEmail(),
        SENDER_EMAIL,
        SUBJECT,
        greetingBody(employee)
    );
  }

  public String getRecipient() {
    return recipient;
  }

  public String getSender() {
    return sender;
  }

  public String getSubject() {
    return subject;
  }

  public String getBody() {
    return body;
  }

  private static String greetingBody(Employee employee) {
    return "Happy Birthday, dear %NAME%!".replace("%NAME%", employee.getFirstName());
  }
}

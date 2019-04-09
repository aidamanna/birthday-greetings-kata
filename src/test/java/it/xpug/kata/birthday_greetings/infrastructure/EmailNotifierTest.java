package it.xpug.kata.birthday_greetings.infrastructure;

import static org.junit.Assert.assertEquals;

import com.dumbster.smtp.SimpleSmtpServer;
import com.dumbster.smtp.SmtpMessage;
import it.xpug.kata.birthday_greetings.domain.Employee;
import it.xpug.kata.birthday_greetings.domain.XDate;
import javax.mail.MessagingException;
import org.junit.Ignore;
import org.junit.Test;

public class EmailNotifierTest {

  private static final int NONSTANDARD_PORT = 9999;

  @Test
  public void sendsEmail() {
    SimpleSmtpServer mailServer = SimpleSmtpServer.start(NONSTANDARD_PORT);
    EmailNotifier emailNotifier = new EmailNotifier("localhost", NONSTANDARD_PORT);

    Employee employee = new Employee("Maria",
        "Medina",
        XDate.from("1990/01/31"),
        "maria@medina.com");

    emailNotifier.send(employee);

    SmtpMessage message = (SmtpMessage) mailServer.getReceivedEmail().next();
    String[] recipients = message.getHeaderValues("To");
    String header = message.getHeaderValue("Subject");
    String body = message.getBody();

    assertEquals(1, recipients.length);
    assertEquals("maria@medina.com", recipients[0]);
    assertEquals("Happy GreetBirthdays!", header);
    assertEquals("Happy GreetBirthdays, dear Maria!", body);
  }

  @Ignore
  @Test(expected = MessagingException.class)
  public void logsErrorIfEmailCannotBeSent() throws Exception {
    EmailNotifier emailNotifier = new EmailNotifier("localhost", NONSTANDARD_PORT);

    Employee employee = new Employee("Maria",
        "Medina",
        XDate.from("1990/01/31"),
        "maria@medina.com");

    emailNotifier.send(employee);
  }
}
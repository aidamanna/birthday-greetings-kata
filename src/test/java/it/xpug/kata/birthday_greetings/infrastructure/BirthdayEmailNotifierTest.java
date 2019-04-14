package it.xpug.kata.birthday_greetings.infrastructure;

import static org.junit.Assert.assertEquals;

import com.dumbster.smtp.SimpleSmtpServer;
import com.dumbster.smtp.SmtpMessage;
import it.xpug.kata.birthday_greetings.domain.Employee;
import java.time.LocalDate;
import javax.mail.MessagingException;
import org.junit.Ignore;
import org.junit.Test;

public class BirthdayEmailNotifierTest {

  private static final int NONSTANDARD_PORT = 9999;

  @Test
  public void sendsEmail() {
    SimpleSmtpServer mailServer = SimpleSmtpServer.start(NONSTANDARD_PORT);
    BirthdayEmailNotifier birthdayEmailNotifier = new BirthdayEmailNotifier("localhost", NONSTANDARD_PORT);

    Employee employee = new Employee("Maria",
        "Medina",
        LocalDate.of(1990, 1, 31),
        "maria@medina.com");

    birthdayEmailNotifier.send(employee);

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
  public void logsErrorIfEmailCannotBeSent() {
    BirthdayEmailNotifier birthdayEmailNotifier = new BirthdayEmailNotifier("localhost", NONSTANDARD_PORT);

    Employee employee = new Employee("Maria",
        "Medina",
        LocalDate.of(1990, 1, 31),
        "a");
    birthdayEmailNotifier.send(employee);
  }
}
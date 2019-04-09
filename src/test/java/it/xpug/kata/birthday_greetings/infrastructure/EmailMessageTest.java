package it.xpug.kata.birthday_greetings.infrastructure;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

import it.xpug.kata.birthday_greetings.domain.Employee;
import it.xpug.kata.birthday_greetings.domain.XDate;
import org.junit.Test;

public class EmailMessageTest {

  public static final String RECIPIENT_EMAIL = "maria@medina.com";


  @Test
  public void generatesEmailMessageForEmployee() {
    Employee employee = new Employee("Maria",
        "Medina",
        XDate.from("1990/01/31"),
        RECIPIENT_EMAIL);

    EmailMessage message = EmailMessage.generateFor(employee);

    assertThat(message.getRecipient(), is(RECIPIENT_EMAIL));
    assertThat(message.getSender(), is("sender@here.com"));
    assertThat(message.getSubject(), is("Happy GreetBirthdays!"));
    assertThat(message.getBody(), is("Happy GreetBirthdays, dear Maria!"));
  }
}
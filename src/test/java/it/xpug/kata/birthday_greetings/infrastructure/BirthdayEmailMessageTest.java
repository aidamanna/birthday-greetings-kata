package it.xpug.kata.birthday_greetings.infrastructure;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

import it.xpug.kata.birthday_greetings.domain.Employee;
import java.time.LocalDate;
import org.junit.Test;

public class BirthdayEmailMessageTest {

  public static final String RECIPIENT_EMAIL = "maria@medina.com";


  @Test
  public void generatesEmailMessageForEmployee() {
    Employee employee = new Employee("Maria",
        "Medina",
        LocalDate.of(1990, 1, 31),
        RECIPIENT_EMAIL);

    BirthdayEmailMessage message = BirthdayEmailMessage.generateFor(employee);

    assertThat(message.getRecipient(), is(RECIPIENT_EMAIL));
    assertThat(message.getSender(), is("sender@here.com"));
    assertThat(message.getSubject(), is("Happy GreetBirthdays!"));
    assertThat(message.getBody(), is("Happy GreetBirthdays, dear Maria!"));
  }
}
package it.xpug.kata.birthday_greetings.application;

import it.xpug.kata.birthday_greetings.domain.Employee;
import javax.mail.MessagingException;

public interface MessageNotifier {

  void send(Employee employee) throws MessagingException;
}

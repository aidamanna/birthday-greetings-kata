package it.xpug.kata.birthday_greetings.application;

import it.xpug.kata.birthday_greetings.domain.Employee;
import it.xpug.kata.birthday_greetings.domain.EmployeeNotFound;

public interface MessageNotifier {

  void send(Employee employee) throws EmployeeNotFound;
}

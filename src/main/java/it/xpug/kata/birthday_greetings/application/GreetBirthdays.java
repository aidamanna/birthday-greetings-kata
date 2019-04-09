package it.xpug.kata.birthday_greetings.application;

import it.xpug.kata.birthday_greetings.domain.Employee;
import it.xpug.kata.birthday_greetings.domain.EmployeeNotFound;
import it.xpug.kata.birthday_greetings.domain.EmployeeRepository;
import it.xpug.kata.birthday_greetings.domain.XDate;
import java.util.List;

public class GreetBirthdays {

  private EmployeeRepository employeeRepository;
  private MessageNotifier messageNotifier;

  public GreetBirthdays(EmployeeRepository employeeRepository, MessageNotifier messageNotifier) {
    this.employeeRepository = employeeRepository;
    this.messageNotifier = messageNotifier;
  }

  public void forDay(XDate xDate) throws EmployeeNotFound {
    List<Employee> employees = employeeRepository.list();

    for (Employee employee : employees) {
      if (employee.isBirthday(xDate)) {
        messageNotifier.send(employee);
      }
    }
	}
}

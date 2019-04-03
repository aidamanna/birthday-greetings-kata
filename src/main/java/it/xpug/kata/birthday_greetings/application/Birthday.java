package it.xpug.kata.birthday_greetings.application;

import it.xpug.kata.birthday_greetings.domain.Employee;
import it.xpug.kata.birthday_greetings.domain.EmployeeNotFound;
import it.xpug.kata.birthday_greetings.domain.EmployeeRepository;
import it.xpug.kata.birthday_greetings.domain.XDate;
import it.xpug.kata.birthday_greetings.infrastructure.EmailNotifier;
import java.util.List;

public class Birthday {

  private EmployeeRepository employeeRepository;
  private EmailNotifier emailNotifier;

  public Birthday(EmployeeRepository employeeRepository, EmailNotifier emailNotifier) {
    this.employeeRepository = employeeRepository;
    this.emailNotifier = emailNotifier;
  }

  public void sendGreetings(XDate xDate) throws EmployeeNotFound {
    List<Employee> employees = employeeRepository.list();

    for (Employee employee : employees) {
      if (employee.isBirthday(xDate)) {
        emailNotifier.send(employee);
      }
    }
	}
}

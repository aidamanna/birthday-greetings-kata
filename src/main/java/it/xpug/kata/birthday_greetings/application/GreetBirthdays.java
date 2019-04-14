package it.xpug.kata.birthday_greetings.application;

import it.xpug.kata.birthday_greetings.domain.BirthdayNotifier;
import it.xpug.kata.birthday_greetings.domain.CannotListEmployees;
import it.xpug.kata.birthday_greetings.domain.Employee;
import it.xpug.kata.birthday_greetings.domain.EmployeeRepository;
import java.time.Clock;
import java.time.LocalDate;
import java.util.List;

public class GreetBirthdays {

  private EmployeeRepository employeeRepository;
  private BirthdayNotifier birthdayNotifier;
  private Clock clock;

  public GreetBirthdays(EmployeeRepository employeeRepository, BirthdayNotifier birthdayNotifier,
      Clock clock) {
    this.employeeRepository = employeeRepository;
    this.birthdayNotifier = birthdayNotifier;
    this.clock = clock;
  }

  public void ofToday() throws CannotListEmployees {
    List<Employee> employees = employeeRepository.all();
    LocalDate today = LocalDate.now(clock);

    for (Employee employee : employees) {
        if (employee.isBirthday(today)) {
          birthdayNotifier.send(employee);
        }
    }
	}
}

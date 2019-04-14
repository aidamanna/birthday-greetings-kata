package it.xpug.kata.birthday_greetings.infrastructure;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

import it.xpug.kata.birthday_greetings.domain.CannotListEmployees;
import it.xpug.kata.birthday_greetings.domain.Employee;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import org.junit.Test;

public class EmployeeFileRepositoryTest {

  @Test
  public void returnsAllEmployees() throws CannotListEmployees {
    EmployeeFileRepository employeeRepository =
        new EmployeeFileRepository("employee_data.txt");

    List<Employee> employees = employeeRepository.all();

    assertThat(employees, is(expectedEmployees()));

  }

  @Test(expected = CannotListEmployees.class)
  public void throwsCannotListEmployeesExceptionIfItCannotReadEmployeesFile() throws CannotListEmployees {
    EmployeeFileRepository employeeRepository =
        new EmployeeFileRepository("_employee_data.txt");

    employeeRepository.all();
  }

  private List<Employee> expectedEmployees() {
    return Arrays.asList(
        new Employee("John",
            "Doe",
            LocalDate.of(1982, 10, 8),
            "john.doe@foobar.com"),
        new Employee("Mary",
            "Ann",
            LocalDate.of(1975, 03, 11),
            "mary.ann@foobar.com"));
  }
}
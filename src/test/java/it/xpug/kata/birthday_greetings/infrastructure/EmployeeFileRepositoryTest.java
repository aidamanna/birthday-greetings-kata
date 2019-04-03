package it.xpug.kata.birthday_greetings.infrastructure;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

import it.xpug.kata.birthday_greetings.domain.Employee;
import it.xpug.kata.birthday_greetings.domain.EmployeeNotFound;
import it.xpug.kata.birthday_greetings.domain.XDate;
import java.util.Arrays;
import java.util.List;
import org.junit.Test;

public class EmployeeFileRepositoryTest {

  @Test
  public void returnsTheEmployeeList() throws EmployeeNotFound {
    EmployeeFileRepository employeeRepository =
        new EmployeeFileRepository("employee_data.txt");

    List<Employee> employees = employeeRepository.list();

    assertThat(employees, is(expectedEmployees()));

  }

  @Test(expected = EmployeeNotFound.class)
  public void throwsEmployeeNotFoundExceptionIfItCannotReadEmployessFile() throws EmployeeNotFound {
    EmployeeFileRepository employeeRepository =
        new EmployeeFileRepository("_employee_data.txt");

    employeeRepository.list();
  }

  private List<Employee> expectedEmployees() {
    return Arrays.asList(
        new Employee("John",
            "Doe",
            XDate.from("1982/10/08"),
            "john.doe@foobar.com"),
        new Employee("Mary",
            "Ann",
            XDate.from("1975/03/11"),
            "mary.ann@foobar.com"));
  }
}
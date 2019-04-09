package it.xpug.kata.birthday_greetings.infrastructure;

import it.xpug.kata.birthday_greetings.domain.Employee;
import it.xpug.kata.birthday_greetings.domain.EmployeeNotFound;
import it.xpug.kata.birthday_greetings.domain.EmployeeRepository;
import it.xpug.kata.birthday_greetings.domain.XDate;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class EmployeeFileRepository implements EmployeeRepository {

  private String fileName;

  public EmployeeFileRepository(String fileName) {
    this.fileName = fileName;
  }

  public List<Employee> list() throws EmployeeNotFound {
    List<Employee> employees = new ArrayList<>();

    try {
      BufferedReader file = new BufferedReader(new FileReader(fileName));
      file.readLine(); // skip header

      String line;

      while ((line = file.readLine()) != null) {
        String[] employeeData = line.split(", ");
        Employee employee = new Employee(
            employeeData[1],
            employeeData[0],
            XDate.from(employeeData[2]),
            employeeData[3]);

        employees.add(employee);
      }
    } catch (FileNotFoundException exception) {
      throw  new EmployeeNotFound("Cannot find the employees file", exception);
    } catch (IOException exception) {
      throw  new EmployeeNotFound("Cannot read the employee file", exception);
    }

    return employees;
  }
}

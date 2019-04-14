package it.xpug.kata.birthday_greetings.infrastructure;

import it.xpug.kata.birthday_greetings.domain.CannotListEmployees;
import it.xpug.kata.birthday_greetings.domain.Employee;
import it.xpug.kata.birthday_greetings.domain.EmployeeRepository;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class EmployeeFileRepository implements EmployeeRepository {

  private String fileName;

  public EmployeeFileRepository(String fileName) {
    this.fileName = fileName;
  }

  public List<Employee> all() throws CannotListEmployees {
    List<Employee> employees = new ArrayList<>();

    BufferedReader file = getFile();
    String line;

    while ((line = getNextLine(file)) != null) {
      String[] employeeData = line.split(", ");

      Employee employee = new Employee(
          employeeData[1],
          employeeData[0],
          getBirthday(employeeData[2]),
          employeeData[3]);

      employees.add(employee);
    }

    return employees;
  }

  private BufferedReader getFile() throws CannotListEmployees {
    try {
      BufferedReader file = new BufferedReader(new FileReader(fileName));
      getNextLine(file);
      return file;
    } catch (FileNotFoundException exception) {
      throw new CannotListEmployees("Cannot find the employees file");
    }
  }

  private String getNextLine(BufferedReader file) throws CannotListEmployees {
    try {
      return file.readLine();
    } catch (IOException exception) {
      throw new CannotListEmployees("Cannot read the line");
    }
  }

  private LocalDate getBirthday(String stringDate) {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
    return LocalDate.parse(stringDate, formatter);
  }
}

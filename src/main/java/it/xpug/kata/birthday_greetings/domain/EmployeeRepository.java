package it.xpug.kata.birthday_greetings.domain;

import java.util.List;

public interface EmployeeRepository {

  List<Employee> list() throws EmployeeNotFound;
}

package it.xpug.kata.birthday_greetings.domain;

public class EmployeeNotFound extends Exception {

  public EmployeeNotFound(String message, Throwable cause) {
    super(message, cause);
  }
}

package it.xpug.kata.birthday_greetings.domain;

public interface BirthdayNotifier {

  void send(Employee employee) throws CannotListEmployees;
}

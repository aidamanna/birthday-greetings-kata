package it.xpug.kata.birthday_greetings;

import it.xpug.kata.birthday_greetings.application.GreetBirthdays;
import it.xpug.kata.birthday_greetings.application.MessageNotifier;
import it.xpug.kata.birthday_greetings.domain.EmployeeRepository;
import it.xpug.kata.birthday_greetings.domain.XDate;
import it.xpug.kata.birthday_greetings.infrastructure.EmailNotifier;
import it.xpug.kata.birthday_greetings.infrastructure.EmployeeFileRepository;

public class Main {

	public static void main(String[] args) throws Exception {
		EmployeeRepository employeeRepository = new EmployeeFileRepository("employee_data.txt");
		MessageNotifier messageNotifier = new EmailNotifier("localhost", 9999);
		GreetBirthdays greetBirthdays = new GreetBirthdays(employeeRepository, messageNotifier);

		greetBirthdays.forDay(XDate.today());
	}

}

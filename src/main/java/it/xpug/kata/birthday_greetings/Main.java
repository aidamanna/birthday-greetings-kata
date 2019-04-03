package it.xpug.kata.birthday_greetings;

import it.xpug.kata.birthday_greetings.application.Birthday;
import it.xpug.kata.birthday_greetings.domain.EmployeeRepository;
import it.xpug.kata.birthday_greetings.domain.XDate;
import it.xpug.kata.birthday_greetings.infrastructure.EmailNotifier;
import it.xpug.kata.birthday_greetings.infrastructure.EmployeeFileRepository;

public class Main {

	public static void main(String[] args) throws Exception {
		EmployeeRepository employeeRepository = new EmployeeFileRepository("employee_data.txt");
		EmailNotifier emailNotifier = new EmailNotifier("localhost", 9999);
		Birthday service = new Birthday(employeeRepository, emailNotifier);

		service.sendGreetings(XDate.today());
	}

}

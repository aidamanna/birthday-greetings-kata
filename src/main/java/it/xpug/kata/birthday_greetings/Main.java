package it.xpug.kata.birthday_greetings;

import it.xpug.kata.birthday_greetings.application.GreetBirthdays;
import it.xpug.kata.birthday_greetings.domain.BirthdayNotifier;
import it.xpug.kata.birthday_greetings.domain.EmployeeRepository;
import it.xpug.kata.birthday_greetings.infrastructure.BirthdayEmailNotifier;
import it.xpug.kata.birthday_greetings.infrastructure.EmployeeFileRepository;
import java.time.Clock;

public class Main {

	public static void main(String[] args) throws Exception {
		EmployeeRepository employeeRepository =
				new EmployeeFileRepository("employee_data.txt");
		BirthdayNotifier birthdayNotifier =
				new BirthdayEmailNotifier("localhost", 9998);
		GreetBirthdays greetBirthdays =
				new GreetBirthdays(employeeRepository, birthdayNotifier, Clock.systemDefaultZone());

		greetBirthdays.ofToday();
	}

}

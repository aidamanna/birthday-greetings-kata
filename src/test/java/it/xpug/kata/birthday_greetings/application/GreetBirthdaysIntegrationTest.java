package it.xpug.kata.birthday_greetings.application;

import static org.junit.Assert.assertEquals;

import com.dumbster.smtp.SimpleSmtpServer;
import it.xpug.kata.birthday_greetings.domain.EmployeeNotFound;
import it.xpug.kata.birthday_greetings.domain.EmployeeRepository;
import it.xpug.kata.birthday_greetings.domain.XDate;
import it.xpug.kata.birthday_greetings.infrastructure.EmailNotifier;
import it.xpug.kata.birthday_greetings.infrastructure.EmployeeFileRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;


public class GreetBirthdaysIntegrationTest {

	private GreetBirthdays greetBirthdays;
	private EmailNotifier emailNotifier;
	private EmployeeRepository employeeRepository;
	private SimpleSmtpServer mailServer;

	private static final int NONSTANDARD_PORT = 9999;

	@Before
	public void setUp() {
		mailServer = SimpleSmtpServer.start(NONSTANDARD_PORT);
		employeeRepository = new EmployeeFileRepository("employee_data.txt");
		emailNotifier = new EmailNotifier("localhost", NONSTANDARD_PORT);
		greetBirthdays = new GreetBirthdays(employeeRepository, emailNotifier);
	}

	@After
	public void tearDown() throws Exception {
		mailServer.stop();
		Thread.sleep(200);
	}

	@Test
	public void sendsGreetingEmailWhenSomeonesBirthday() throws EmployeeNotFound {
		greetBirthdays.forDay(XDate.from("2008/10/08"));

		assertEquals(1, mailServer.getReceivedEmailSize());
	}

	@Test
	public void doesNotSendGreetingEmailWhenNoOnesBirthday() throws EmployeeNotFound {
		greetBirthdays.forDay(XDate.from("2008/01/01"));

		assertEquals(0, mailServer.getReceivedEmailSize());
	}
}

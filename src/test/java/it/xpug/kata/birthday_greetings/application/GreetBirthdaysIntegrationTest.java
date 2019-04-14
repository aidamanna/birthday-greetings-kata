package it.xpug.kata.birthday_greetings.application;

import static org.junit.Assert.assertEquals;

import com.dumbster.smtp.SimpleSmtpServer;
import it.xpug.kata.birthday_greetings.domain.CannotListEmployees;
import it.xpug.kata.birthday_greetings.domain.EmployeeRepository;
import it.xpug.kata.birthday_greetings.infrastructure.BirthdayEmailNotifier;
import it.xpug.kata.birthday_greetings.infrastructure.EmployeeFileRepository;
import java.time.Clock;
import java.time.Instant;
import java.time.ZoneId;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;


public class GreetBirthdaysIntegrationTest {

	private GreetBirthdays greetBirthdays;
	private BirthdayEmailNotifier birthdayEmailNotifier;
	private EmployeeRepository employeeRepository;
	private SimpleSmtpServer mailServer;
	private Clock clock;

	private static final int NONSTANDARD_PORT = 9998;

	@Before
	public void setUp() {
		mailServer = SimpleSmtpServer.start(NONSTANDARD_PORT);
		employeeRepository = new EmployeeFileRepository("employee_data.txt");
		birthdayEmailNotifier = new BirthdayEmailNotifier("localhost", NONSTANDARD_PORT);
	}

	@After
	public void tearDown() throws Exception {
		mailServer.stop();
		Thread.sleep(200);
	}

	@Test
	public void sendsGreetingEmailWhenSomeonesBirthday() throws CannotListEmployees {
		clock = Clock.fixed(Instant.parse("2018-10-08T10:00:00.00Z"),
				ZoneId.of("Europe/Madrid"));
		greetBirthdays = new GreetBirthdays(employeeRepository, birthdayEmailNotifier, clock);

		greetBirthdays.ofToday();

		assertEquals(1, mailServer.getReceivedEmailSize());
	}

	@Test
	public void doesNotSendGreetingEmailWhenNoOnesBirthday() throws CannotListEmployees {
		clock = Clock.fixed(Instant.parse("2008-01-01T10:00:00.00Z"),
				ZoneId.of("Europe/Madrid"));
		greetBirthdays = new GreetBirthdays(employeeRepository, birthdayEmailNotifier, clock);

		greetBirthdays.ofToday();

		assertEquals(0, mailServer.getReceivedEmailSize());
	}
}

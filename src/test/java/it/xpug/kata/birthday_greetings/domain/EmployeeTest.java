package it.xpug.kata.birthday_greetings.domain;
import static org.junit.Assert.*;

import java.time.LocalDate;
import org.junit.*;

public class EmployeeTest {

	@Test
	public void returnsTrueIfIsEmployeeBirthday() {
		Employee employee = anEmployee();
		LocalDate today = LocalDate.of(2008, 1, 12);

		assertTrue(employee.isBirthday(today));
	}

  @Test
	public void returnsFalseIfIsNotEmployeeBirthdayBecauseDayIsDifferent() {
		Employee employee = anEmployee();
		LocalDate today = LocalDate.of(2008, 1, 30);

		assertFalse(employee.isBirthday(today));
	}

	@Test
	public void returnsFalseIfIsNotEmployeeBirthdayBecauseMonthIsDifferent() {
		Employee employee = anEmployee();
		LocalDate today = LocalDate.of(2008, 2, 12);

		assertFalse(employee.isBirthday(today));
	}

  private Employee anEmployee() {
    return new Employee("Maria",
        "Medina",
        LocalDate.of(1990, 1, 12),
        "maria@medina.com");
  }
}

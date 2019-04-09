package it.xpug.kata.birthday_greetings.domain;
import static org.junit.Assert.*;

import org.junit.*;

public class EmployeeTest {

	@Test
	public void returnsTrueIfIsEmployeeBirthday() {
		Employee employee = anEmployee("1990/01/31");

		assertTrue(employee.isBirthday(XDate.from("2008/01/31")));
	}

  @Test
	public void returnsFalseIfIsNotEmployeeBirthdayBecauseDayIsDifferent() {
		Employee employee = anEmployee("1990/01/31");

		assertFalse(employee.isBirthday(XDate.from("2008/01/30")));
	}

	@Test
	public void returnsFalseIfIsNotEmployeeBirthdayBecauseMonthIsDifferent() {
		Employee employee = anEmployee("1990/01/12");

		assertFalse(employee.isBirthday(XDate.from("2008/02/12")));
	}

	@Test
	public void returnsFalseIfEmployeeBirthdayIsNotDefined() {
		Employee employee = anEmployee("1990/01");

		assertFalse(employee.isBirthday(XDate.from("2008/01/30")));
	}

  private Employee anEmployee(String birthday) {
    return new Employee("Maria",
        "Medina",
        XDate.from(birthday),
        "maria@medina.com");
  }
}

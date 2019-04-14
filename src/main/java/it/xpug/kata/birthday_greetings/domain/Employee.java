package it.xpug.kata.birthday_greetings.domain;

import java.time.LocalDate;
import java.util.Objects;

public class Employee {

	private LocalDate birthDate;
	private String lastName;
	private String firstName;
	private String email;

	public Employee(String firstName, String lastName, LocalDate birthDate, String email) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.birthDate = birthDate;
		this.email = email;
	}

	public boolean isBirthday(LocalDate today) {
		return birthDate.getDayOfMonth() == today.getDayOfMonth() &&
				birthDate.getMonth() == today.getMonth();
	}

	public String getEmail() {
		return email;
	}

	public String getFirstName() {
		return firstName;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		Employee employee = (Employee) o;
		return Objects.equals(birthDate, employee.birthDate) &&
				Objects.equals(lastName, employee.lastName) &&
				Objects.equals(firstName, employee.firstName) &&
				Objects.equals(email, employee.email);
	}

	@Override
	public int hashCode() {
		return Objects.hash(birthDate, lastName, firstName, email);
	}

	@Override
	public String toString() {
		return "Employee " + firstName + " " + lastName + " <" + email + "> born " + birthDate;
	}
}

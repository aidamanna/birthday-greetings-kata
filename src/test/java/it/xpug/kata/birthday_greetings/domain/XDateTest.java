package it.xpug.kata.birthday_greetings.domain;

import static junit.framework.TestCase.assertNull;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class XDateTest {

	@Test
	public void returnsADateFromAString() {
		XDate date = XDate.from("1789/01/24");

		assertNotNull(date);
	}

  @Test
  public void returnsAnEmptyDateFromAStringWithWrongDateFormat() {
    XDate date = XDate.from("1789/01");

    assertNull(date);
  }

  @Test
	public void returnsDayWhenAskingDateDay() {
		XDate date = XDate.from("1789/01/24");

		assertEquals(24, date.getDay());
	}

  @Test
  public void returnsMonthWhenAskingDateMonth() {
    XDate date = XDate.from("1789/01/24");

    assertEquals(1, date.getMonth());
  }

  @Test
  public void returnsTrueIfIsBirthday() {
    XDate birthDay = XDate.from("1789/01/24");
		XDate today = XDate.from("2001/01/24");

		assertTrue(today.isSameDayMonth(birthDay));
  }

  @Test
  public void returnsFalseIfIsNotBirthdayBecauseDayIsDiffernt() {
    XDate birthDay = XDate.from("1789/01/24");
    XDate today = XDate.from("2001/01/25");

    assertFalse(today.isSameDayMonth(birthDay));
  }

  @Test
  public void returnsFalseIfIsNotBirthdayBecauseMonthIsDifferent() {
    XDate birthDay = XDate.from("1789/01/24");
    XDate today = XDate.from("2001/03/24");

    assertFalse(today.isSameDayMonth(birthDay));
  }

  @Test
  public void returnsFalseIfBirthdayNotDefined() {
    XDate birthDay = XDate.from("1789/01");
    XDate today = XDate.from("2001/03/24");

    assertFalse(today.isSameDayMonth(birthDay));
  }
}

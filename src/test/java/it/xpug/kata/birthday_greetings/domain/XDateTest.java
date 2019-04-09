package it.xpug.kata.birthday_greetings.domain;

import static junit.framework.TestCase.assertNull;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

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
}

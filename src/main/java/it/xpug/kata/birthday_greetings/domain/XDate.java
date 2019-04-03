package it.xpug.kata.birthday_greetings.domain;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

public class XDate {

	private Date date;

	public static XDate from(String stringDate) {
		try {
			return new XDate(new SimpleDateFormat("yyyy/MM/dd").parse(stringDate));
		} catch (ParseException exception) {
			return null;
		}
	}

	public static XDate today() {
		return new XDate(new Date());
	}

	private XDate(Date date) {
		this.date = date;
	}

	public int getDay() {
		return getPartOfDate(GregorianCalendar.DAY_OF_MONTH);
	}

	public int getMonth() {
		return 1 + getPartOfDate(GregorianCalendar.MONTH);
	}

	public boolean isSameDayMonth(XDate otherDate) {
	  if (otherDate == null) {
	    return false;
    }

		return otherDate.getDay() == this.getDay() && otherDate.getMonth() == this.getMonth();
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof XDate))
			return false;
		XDate other = (XDate) obj;
		return other.date.equals(this.date);
	}

	@Override
	public int hashCode() {
		return date.hashCode();
	}

	@Override
	public String toString() {
		return "XDate{" +
				"date=" + date +
				'}';
	}

	private int getPartOfDate(int part) {
		GregorianCalendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		return calendar.get(part);
	}
}

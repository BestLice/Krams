package Utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {
	private static final String DEFAULT_LOG_FORMAT = "yyyy-MM-dd_HHmmss";
	private static final String DEFAULT_FORMAT = "dd.MM.yyyy";

	public static Date string2date(String str) {
		return string2date(str, DEFAULT_FORMAT);
	}

	public static Date string2dateLOG(String str) {
		return string2date(str, DEFAULT_LOG_FORMAT);
	}

	public static String date2string(Date date) {
		return date2string(date, DEFAULT_FORMAT);
	}

	public static Date string2date(String str, String format) {
		try {
			DateFormat dateformat = new SimpleDateFormat(format);
			return dateformat.parse(str);
		} catch (ParseException e) {
			return null;
		}
	}

	public static String date2string(Date date, String format) {
		DateFormat dateformat = new SimpleDateFormat(format);
		return dateformat.format(date);
	}
}

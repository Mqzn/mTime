package dev.mqzen.time;

/**
 * The main class that is used to parse time strings inputs
 * The algorithm is fully written by me (aka Mqzen)
 * Make sure to know just when to use it properly :D !
 *
 * @author Mqzen
 */
public final class TimeParser {

	private long days, hours, minutes, seconds;

	private TimeParser(long millis) {
		this.days =  (millis / 86400000L);
		this.hours =  (millis / 3600000L % 24L);
		this.minutes =  (millis / 60000L % 60L);
		this.seconds =  (millis / 1000L % 60L);
	}

	private TimeParser(String timePeriod) {

		char[] chars = timePeriod.toCharArray();

		for (int i = 0; i < timePeriod.length(); i++) {
			
			if(Character.isDigit(chars[i])) {
				
				StringBuilder digitToCollect = new StringBuilder();
				int start = i;
				while (Character.isDigit(chars[start])) {
					digitToCollect.append(chars[start]);
					start++;
				}
				
				//the current index is the end of the digit to collect
				//so the current index is that of a unit char
				char unit = chars[start];
				int digit = Integer.parseInt(digitToCollect.toString());
				switch (unit) {
				case 'd': case 'D':
					this.days += digit;
					break;
				case 'h': case 'H':
					this.hours += digit;
					break;
				case 'm': case 'M':
					this.minutes += digit;
					break;
				case 's': case 'S':
					this.seconds += digit;
					break;
				}

				i = start;
			}
			
		}

	}

	public static TimeParser parse(String timePeriod) {
		return new TimeParser(timePeriod);
	}

	public static TimeParser parse(long millis) {
		return new TimeParser(millis);
	}

	public long getDays() {
		return days;
	}

	public long getHours() {
		return hours;
	}


	public long getMinutes() {
		return minutes;
	}

	public long getSeconds() {
		return seconds;
	}
}
package dev.mqzen.time.utility;

import dev.mqzen.time.base.IDuration;
import dev.mqzen.time.base.TimeFormatter;
import dev.mqzen.time.base.Unit;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.format.NamedTextColor;
import org.checkerframework.checker.nullness.qual.NonNull;

import java.time.LocalDateTime;

/**
 * The class used to provide extremely useful
 * methods that are utilized to provide
 * fancy formats or vital calculations to make
 * stuff easier
 *
 * @author Mqzen
 * @see IDuration
 */
public class TimeUtility {


	/**
	 * @param str checking if the input contains single unit stated
	 * @return whether if time input shows only a single unit
	 * along with its value
	 * @deprecated Pretty useless...but may be useful for others, so I kept it
	 */
	@Deprecated
	public static boolean isSingleTime(String str) {

		for (Unit u : Unit.values()) {
			for (String id : u.getIdentifiers()) {
				str = str.replace(id, "");
			}
		}

		try {
			Long.parseLong(str);
			return true;
		} catch (NumberFormatException ex) {
			return false;
		}

	}

	/**
	 * Counts how many time units are stated in a time input
	 *
	 * @param format the time input obviously
	 * @return the number of time units in the time input
	 */
	public static int countTimeUnits(String format) {
		char[] chars = format.toCharArray();
		int c = 0;
		for (int i = 0, x = i + 1; i < chars.length && x < chars.length; i++, x++) {
			if (Character.isDigit(chars[i]) && !Character.isDigit(chars[x])) {
				c++;
			}
		}

		return c;
	}

	/**
	 * @param format checking if the input contains single unit stated
	 * @return whether if time input shows only a single unit
	 * along with its value
	 * @deprecated Pretty useless...but may be useful for others, so I kept it
	 */
	@Deprecated
	public static boolean isMultiTime(String format) {
		return countTimeUnits(format) > 1;
	}

	/**
	 * Format the difference in time in the parameter
	 * into a text component that is fancy to your eyes
	 *
	 * @param differenceInTime the time difference to format
	 * @return the formatted text component
	 */
	public static TextComponent formatDifference(long differenceInTime) {
		int days = (int) (differenceInTime / (1000 * 60 * 60 * 24));
		int hours = (int) ((differenceInTime % (1000 * 60 * 60 * 24)) / (1000 * 60 * 60));
		int minutes = (int) ((differenceInTime % (1000 * 60 * 60)) / (1000 * 60));
		int seconds = (int) ((differenceInTime % (1000 * 60)) / 1000);

		return TimeFormatter.of(days, hours, minutes, seconds)
						.format(false, NamedTextColor.YELLOW, NamedTextColor.GOLD);
	}

	/**
	 * Formatting the difference in time from the totalDuration point
	 *
	 * @param totalDuration the start in which it will calculate the difference from
	 * @return the formatted remaining time from that point ('totalDuration')
	 */
	public static TextComponent formatRemainingTime(long totalDuration) {
		long differenceInTime = totalDuration - System.currentTimeMillis();
		return formatDifference(differenceInTime);
	}

	/**
	 * @param startDuration the starting point but in the
	 *                      form of a duration object
	 * @return the format of the difference in
	 * time between duration in the future and now
	 */
	public static TextComponent formatRemainingTime(@NonNull IDuration startDuration) {
		return formatRemainingTime(startDuration.getTotalDuration());
	}


	/**
	 * Calculates the difference in time between now
	 * and a time/date in the past
	 *
	 * @param past the past date when something occurred
	 * @return the format of the difference in time
	 */
	public static TextComponent formatTimePastFromNow(LocalDateTime past) {
		LocalDateTime now = LocalDateTime.now();
		long differenceInTime = (java.sql.Date.valueOf(now.toLocalDate()).getTime()
						+ java.sql.Time.valueOf(now.toLocalTime()).getTime())
						- (java.sql.Date.valueOf(past.toLocalDate()).getTime()
						+ java.sql.Time.valueOf(past.toLocalTime()).getTime());

		//convert differenceInTime into days, hours, minutes, seconds
		//get the number of days between two local date times in java

		int days = (int) (differenceInTime / (1000 * 60 * 60 * 24));
		int hours = (int) ((differenceInTime % (1000 * 60 * 60 * 24)) / (1000 * 60 * 60));
		int minutes = (int) ((differenceInTime % (1000 * 60 * 60)) / (1000 * 60));
		int seconds = (int) ((differenceInTime % (1000 * 60)) / 1000);

		return TimeFormatter.of(days, hours, minutes, seconds).format(true, NamedTextColor.BLUE, NamedTextColor.BLUE);

	}

}

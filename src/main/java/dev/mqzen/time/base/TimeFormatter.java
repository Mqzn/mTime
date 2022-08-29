package dev.mqzen.time.base;

import dev.mqzen.time.TimeParser;
import lombok.Getter;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.format.NamedTextColor;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.checker.nullness.qual.Nullable;
import java.time.LocalDateTime;

/**
 * A simple class to format the results
 * from a time parsing which is usually
 * from the class {@link TimeParser}
 * @see TimeParser
 *
 * @author Mqzen
 */
@Getter
public final class TimeFormatter {

	private @Getter long days, hours, minutes, seconds;

	public static TimeFormatter of(String period) {
		return new TimeFormatter(period);
	}

	private TimeFormatter(@Nullable String period) {
		//period are like "1d2h3m4s"
		if(period == null) {
			return;
		}

		TimeParser parser = TimeParser.parse(period);
		this.days = parser.getDays();
		this.hours = parser.getHours();
		this.minutes = parser.getMinutes();
		this.seconds = parser.getSeconds();

	}
	private TimeFormatter(LocalDateTime localDateTime) {
		this.days = localDateTime.getDayOfMonth();
		this.hours = localDateTime.getHour();
		this.minutes = localDateTime.getMinute();
		this.seconds = localDateTime.getSecond();
	}

	private TimeFormatter(int days, int hours, int minutes, int seconds) {
		this.days = days;
		this.hours = hours;
		this.minutes = minutes;
		this.seconds = seconds;
	}

	public static @NonNull TimeFormatter of(int days, int hours, int minutes, int seconds) {
		return new TimeFormatter(days, hours, minutes, seconds);
	}


	public static TimeFormatter of(LocalDateTime localDateTime) {
		return new TimeFormatter(localDateTime);
	}


	/**
	 *
	 * Simple method to format the time data
	 * collected from the parser {@link TimeParser}
	 *
	 * @param showLargestOnly whether to show the time unit
	 * with the largest value or not
	 * @param primaryColor the main color which is showed on
	 * the values of each unit
	 * @param secondaryColor the other color which is showed on
	 * the names of each unit
	 *
	 *
	 * @return the full format of the time
	 */
	public @NonNull TextComponent format(boolean showLargestOnly,
	                                     NamedTextColor primaryColor,
	                                     NamedTextColor secondaryColor) {
		if(days == 0 && hours == 0 && minutes == 0 && seconds == 0) {
			return Component.text("Forever", primaryColor);
		}

		TextComponent formattedDays = Component.text(days, primaryColor)
						.append(Component.text(days > 1 ? " Days " : " Day ", secondaryColor));

		TextComponent formattedHours = Component.text(hours, primaryColor)
						.append(Component.text(hours > 1 ? " Hours " : " Hour ", secondaryColor));

		TextComponent formattedMinutes = Component.text(minutes, primaryColor)

						.append(Component.text(minutes > 1 ? " Minutes " : " Minute ", secondaryColor));

		TextComponent formattedSeconds = Component.text(seconds, primaryColor)
						.append(Component.text(seconds > 1 ? " Seconds " : " Second ", secondaryColor));

		TextComponent res = Component.text().build();
		if(showLargestOnly) {
			if (days >= 1) {
				res = res.append(formattedDays);
			} else if (hours >= 1) {
				res = res.append(formattedHours);
			} else if (minutes >= 1) {
				res = res.append(formattedMinutes);
			} else if (seconds >= 1) {
				res = res.append(formattedSeconds);
			}

		}else {

			if(days >= 1) {
				res = res.append(formattedDays);
			}

			if(hours >= 1) {
				res = res.append(formattedHours);
			}

			if(minutes >= 1) {
				res = res.append(formattedMinutes);
			}

			if(seconds >= 1) {
				res = res.append(formattedSeconds);
			}

		}
		return res;
	}

}

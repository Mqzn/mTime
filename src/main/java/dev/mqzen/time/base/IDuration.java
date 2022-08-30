package dev.mqzen.time.base;

import net.kyori.adventure.text.TextComponent;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.checker.nullness.qual.Nullable;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 * An interface to represent durations
 * and other info about
 * it also holds more info that can be very useful
 * when trying to make a punishments plugin or even cooldowns
 * As they say, simple but powerful and useful
 *
 * @author Mqzen
 */
public interface IDuration {

	@Nullable String getPeriodFormat();

	boolean hasExpired();

	boolean isPermanent();

	@NonNull TextComponent getTimeLeft();

	@NonNull TextComponent getTimeFromNow();

	@NonNull LocalDateTime getCreatedAt();

	long getTotalDuration();

	default LocalDate getDate() {
		return getCreatedAt().toLocalDate();
	}

	default LocalTime getTime() {
		return getCreatedAt().toLocalTime();
	}

}

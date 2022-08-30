package dev.mqzen.time.base;

import dev.mqzen.time.utility.TimeUtility;
import lombok.Getter;
import lombok.NonNull;
import net.kyori.adventure.text.TextComponent;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.jetbrains.annotations.NotNull;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDateTime;

/**
 * A basic implementation of the duration interface {@link IDuration}
 *
 * @author Mqzen
 * @see IDuration
 */

public final class SimpleDuration implements IDuration {

	private final long totalDuration;
	@Getter
	private final @NonNull LocalDateTime createdAt;

	private final @Nullable String timePeriodFormat;

	public SimpleDuration(@Nullable String formatUsed, long totalDuration, @NonNull LocalDateTime createdAt) {
		this.totalDuration = totalDuration;
		this.createdAt = createdAt;
		this.timePeriodFormat = formatUsed;
	}

	public SimpleDuration(@Nullable String format, long totalDuration, @NonNull Date date, @NonNull Time time) {
		this.totalDuration = totalDuration;
		this.createdAt = LocalDateTime.of(date.toLocalDate(), time.toLocalTime());
		this.timePeriodFormat = format;

	}

	@Override
	public String getPeriodFormat() {
		return timePeriodFormat;
	}

	@Override
	public boolean hasExpired() {
		long currentSeconds = System.currentTimeMillis() / 1000;
		long totalSeconds = totalDuration / 1000;
		return (currentSeconds - totalSeconds >= 0L);
	}

	@Override
	public boolean isPermanent() {
		return totalDuration <= 0L;
	}

	@Override
	public @NotNull TextComponent getTimeLeft() {
		return TimeUtility.formatRemainingTime(this);
	}


	@Override
	public @NotNull TextComponent getTimeFromNow() {
		return TimeUtility.formatTimePastFromNow(this.createdAt);
	}

	@Override
	public long getTotalDuration() {
		return totalDuration;
	}


}

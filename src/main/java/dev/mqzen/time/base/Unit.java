package dev.mqzen.time.base;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;
import java.util.List;

/**
 * An enum that's used to represent
 * all the time units needed for parsing
 * and formatting , with the benefits of having
 * a multiple identifiers in case of different inputs
 * that may have more than one identifier for each unit
 *
 * @author Mqzen
 * <p>
 * Made by passion <3
 * @see dev.mqzen.time.utility.TimeUtility
 * @see dev.mqzen.time.TimeParser
 * <p>
 * <p>
 * Note: an identifier is a string that identifies that
 * type of the time unit stated
 */
public enum Unit {

	SECONDS("Second(s)", "s", "sec", "secs", "seconds"),
	MINUTES("Minute(s)", "m", "min", "mins", "minutes"),
	HOURS("Hour(s)", "h", "hour", "hours"),
	DAYS("Day(s)", "d", "day", "days");

	private final @NotNull List<String> identifiers;
	private final @NotNull String name;

	Unit(@NotNull String name, @NotNull String... identifiers) {
		this.identifiers = Arrays.asList(identifiers);
		this.name = name;
	}

	public static @Nullable Unit fromString(String str) {
		if (str == null || str.isEmpty()) return null;

		for (Unit u : Unit.values()) {
			if (u.getIdentifiers().contains(str)) {
				return u;
			}
		}

		return null;
	}

	public @NotNull String getName() {
		return name;
	}

	public @NotNull List<String> getIdentifiers() {
		return identifiers;
	}


}

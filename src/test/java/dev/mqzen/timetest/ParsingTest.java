package dev.mqzen.timetest;


import dev.mqzen.time.TimeParser;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ParsingTest {

	@Test
	public void singleTimeInput() {

		String input = "14d";

		TimeParser parser = TimeParser.of(input);
		Assertions.assertEquals(14,parser.getDays());

	}

	@Test
	public void multipleTimeInput() {

		String input = "12d13h14m5s";

		TimeParser parser = TimeParser.of(input);
		Assertions.assertEquals(12,parser.getDays());
		Assertions.assertEquals(13, parser.getHours());
		Assertions.assertEquals(14, parser.getMinutes());
		Assertions.assertEquals(5, parser.getSeconds());

	}

	@Test
	public void complexMultipleTimeInput() {
		String input = "3654234d, 600hours, 700minutes, 911sec";
		TimeParser parser = TimeParser.of(input);
		Assertions.assertEquals(3654234,parser.getDays());
		Assertions.assertEquals(600, parser.getHours());
		Assertions.assertEquals(700, parser.getMinutes());
		Assertions.assertEquals(991, parser.getSeconds());

		System.out.println("DAYS: " + parser.getDays());
	}


}

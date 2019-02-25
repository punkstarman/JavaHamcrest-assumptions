package net.namrats.commons.hamcrest.assumptions;

import org.hamcrest.Matcher;
import org.opentest4j.TestAbortedException;

public class MatcherAssume {

	public static <T> void assumeThat(T actual, Matcher<T> matcher) {
		if (!matcher.matches(actual)) {
			throw new TestAbortedException();
		}
	}

}

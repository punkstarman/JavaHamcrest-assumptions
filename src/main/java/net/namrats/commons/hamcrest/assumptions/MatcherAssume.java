package net.namrats.commons.hamcrest.assumptions;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.StringDescription;
import org.opentest4j.TestAbortedException;

public class MatcherAssume {

	/**
	 * Assume a precondition holds for a certain value.
	 * 
	 * Does nothing more if the precondition holds; otherwise throws an exception which,
	 * when used in a test, causes the test to be marked as skipped.
	 * 
	 * @param actual  a value
	 * @param matcher  the precondition to be matched over the given value
	 * 
	 * @throws TestAbortedException  if the precondition does not hold
	 */
	public static <T> void assumeThat(T actual, Matcher<? super T> matcher) {
		if (!matcher.matches(actual)) {
			Description description = new StringDescription();
			description.appendText("Expected: ");
			description.appendDescriptionOf(matcher);
			throw new TestAbortedException(description.toString());
		}
	}

}

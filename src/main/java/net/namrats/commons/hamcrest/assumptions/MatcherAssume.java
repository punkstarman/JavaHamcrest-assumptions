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
		assumeThat("", actual, matcher);
	}
	
	/**
	 * Assume a precondition holds for a certain value.
	 * 
	 * Does nothing more if the precondition holds; otherwise throws an exception which,
	 * when used in a test, causes the test to be marked as skipped.
	 *
	 * @param reason  a more specific description of the precondition
	 * @param actual  a value
	 * @param matcher  the precondition to be matched over the given value
	 * 
	 * @throws TestAbortedException  if the precondition does not hold
	 */
	public static <T> void assumeThat(String reason, T actual, Matcher<? super T> matcher) {
		if (!matcher.matches(actual)) {
			Description description = new StringDescription();
			description.appendText(reason);
			description.appendText("\nExpected: ");
			description.appendDescriptionOf(matcher);
			description.appendText("\n     but: ");
			matcher.describeMismatch(actual, description);
			throw new TestAbortedException(description.toString());
		}
	}

}

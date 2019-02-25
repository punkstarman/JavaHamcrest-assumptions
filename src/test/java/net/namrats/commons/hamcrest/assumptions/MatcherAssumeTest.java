package net.namrats.commons.hamcrest.assumptions;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.opentest4j.TestAbortedException;

@DisplayName("Matcher assumption")
public class MatcherAssumeTest {
	
	@Test
	@DisplayName("Assumption OK")
	public void matcherOK() {
		try {
			MatcherAssume.assumeThat("abcdefg", Matchers.containsString("cde"));
		} catch (TestAbortedException e) {
			Assertions.fail("assumeThat unexpectedly aborted test");
		}
	}
	
	@Test
	@DisplayName("Assumption KO")
	public void matcherKO() {
		TestAbortedException exception = assertThrows(TestAbortedException.class, () -> {
			MatcherAssume.assumeThat("abcdefg", Matchers.containsString("xyz"));
		});
	}
}

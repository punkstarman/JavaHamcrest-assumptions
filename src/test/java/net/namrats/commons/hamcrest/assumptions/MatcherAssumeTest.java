package net.namrats.commons.hamcrest.assumptions;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

import org.hamcrest.Matchers;
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
			fail("assumeThat unexpectedly aborted test");
		}
	}
	
	@Test
	@DisplayName("Assumption KO")
	public void matcherKO() {
		TestAbortedException exception = assertThrows(TestAbortedException.class, () -> {
			MatcherAssume.assumeThat("abcdefg", Matchers.containsString("xyz"));
		});
		
		assertAll(
				() -> assertThat(exception.getMessage(), containsString("Expected: a string containing \"xyz\"")),
				() -> assertThat(exception.getMessage(), containsString("but: was \"abcdefg\""))
		);
	}
	
	@Test
	@DisplayName("Assumption with reason KO")
	public void assumeThatWithReasonKO() {
		TestAbortedException exception = assertThrows(TestAbortedException.class, () -> {
			MatcherAssume.assumeThat("For this reason: ", "abcdefg", Matchers.containsString("xyz"));
		});
		
		assertAll(
				() -> assertThat(exception.getMessage(), containsString("Expected: a string containing \"xyz\"")),
				() -> assertThat(exception.getMessage(), containsString("but: was \"abcdefg\"")),
				() -> assertThat(exception.getMessage(), containsString("For this reason: "))
		);
	}

}

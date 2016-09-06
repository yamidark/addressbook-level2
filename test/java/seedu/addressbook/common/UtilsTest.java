package seedu.addressbook.common;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

public class UtilsTest {

	@Test
	public void isAnyNull_returnsFalse() {
		final Object[] testInputs = { "first", "second", "third", "fourth" };
		assertFalse(Utils.isAnyNull(testInputs));
	}
	
	@Test
	public void isAnyNull_returnsTrue() {
		final Object[] testInputs = { "first", null, "third", "fourth" };
		assertTrue(Utils.isAnyNull(testInputs));
	}
	
	public void elementsAreUnique_returnsFalse() {
		ArrayList<String> testInputs = new ArrayList<String>();
		testInputs.add("first");
		testInputs.add("second");
		testInputs.add("third");
		testInputs.add("fourth");
		testInputs.add("first");
		assertFalse(Utils.elementsAreUnique(testInputs));
	}
	
	public void elementsAreUnique_returnsTrue() {
		ArrayList<String> testInputs = new ArrayList<String>();
		testInputs.add("first");
		testInputs.add("second");
		testInputs.add("third");
		testInputs.add("fourth");
		assertTrue(Utils.elementsAreUnique(testInputs));
	}
}

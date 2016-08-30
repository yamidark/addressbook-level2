package seedu.addressbook.data.person;

import seedu.addressbook.data.exception.IllegalValueException;

/**
 * Represents a Person's address in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidAddress(String)}
 */
public class Street {

    public static final String EXAMPLE = "Clementi Ave 3";
    public static final String MESSAGE_STREET_CONSTRAINTS = "Street must be in letters and may have numbers";
    public static final String STREET_VALIDATION_REGEX = "[\\w\\s]+";

    public final String streetName;

    /**
     * Validates given address.
     *
     * @throws IllegalValueException if given address string is invalid.
     */
    public Street(String streetName) throws IllegalValueException {
    	if (!isValidStreet(streetName)) {
    		throw new IllegalValueException(MESSAGE_STREET_CONSTRAINTS);
    	}
        this.streetName = streetName;
    }

    private boolean isValidStreet(String test) {
		return test.matches(STREET_VALIDATION_REGEX);
	}

	@Override
    public String toString() {
        return streetName;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Street // instanceof handles nulls
                && this.streetName.equals(((Street) other).toString())); // state check
    }

    @Override
    public int hashCode() {
        return streetName.hashCode();
    }
}
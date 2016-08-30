package seedu.addressbook.data.person;

import seedu.addressbook.data.exception.IllegalValueException;

/**
 * Represents a Person's address in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidAddress(String)}
 */
public class Unit {

    public static final String EXAMPLE = "#12-34";
    public static final String MESSAGE_UNIT_CONSTRAINTS = "Unit must start with # followed by numbers with a - in between";
    public static final String UNIT_VALIDATION_REGEX = "#[\\d]+-[\\d]+";

    public final String unitNumber;

    /**
     * Validates given address.
     *
     * @throws IllegalValueException if given address string is invalid.
     */
    public Unit(String unitNumber) throws IllegalValueException {
    	if (!isValidUnit(unitNumber)) {
    		throw new IllegalValueException(MESSAGE_UNIT_CONSTRAINTS);
    	}
        this.unitNumber = unitNumber;
    }

    private boolean isValidUnit(String test) {
		return test.matches(UNIT_VALIDATION_REGEX);
	}

	@Override
    public String toString() {
        return unitNumber;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Unit // instanceof handles nulls
                && this.unitNumber.equals(((Unit) other).toString())); // state check
    }

    @Override
    public int hashCode() {
        return unitNumber.hashCode();
    }
}
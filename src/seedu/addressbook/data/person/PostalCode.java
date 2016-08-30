package seedu.addressbook.data.person;

import seedu.addressbook.data.exception.IllegalValueException;

/**
 * Represents a Person's address in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidAddress(String)}
 */
public class PostalCode {

    public static final String EXAMPLE = "123, some street";
    public static final String MESSAGE_POSTAL_CODE_CONSTRAINTS = "Postal Code must be in numbers";
    public static final String POSTAL_CODE_VALIDATION_REGEX = "\\d+";

    public final String postalCode;

    /**
     * Validates given address.
     *
     * @throws IllegalValueException if given address string is invalid.
     */
    public PostalCode(String postalCode) throws IllegalValueException {
    	if (!isValidPostalCode(postalCode)) {
    		throw new IllegalValueException(MESSAGE_POSTAL_CODE_CONSTRAINTS);
    	}
        this.postalCode = postalCode;
    }

    private boolean isValidPostalCode(String test) {
		return test.matches(POSTAL_CODE_VALIDATION_REGEX);
	}

	@Override
    public String toString() {
        return postalCode;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof PostalCode // instanceof handles nulls
                && this.postalCode.equals(((PostalCode) other).toString())); // state check
    }

    @Override
    public int hashCode() {
        return postalCode.hashCode();
    }
}
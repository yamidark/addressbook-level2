package seedu.addressbook.data.person;

import seedu.addressbook.data.exception.IllegalValueException;

/**
 * Represents a Person's contact in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidContact(String)}
 */
public class Contact {
	
	public static final String EXAMPLE = "123, some street";
    public static final String MESSAGE_CONTACT_CONSTRAINTS = "Person contact can be in any format";
    public static final String CONTACT_VALIDATION_REGEX = ".+";
    
	public final String value;
    protected boolean isPrivate;
    
	public Contact(String contactDetails, boolean isPrivate) throws IllegalValueException {
		this.value = contactDetails;
		this.isPrivate = isPrivate;
		if (!isValidContact(contactDetails, CONTACT_VALIDATION_REGEX)) {
            throw new IllegalValueException(MESSAGE_CONTACT_CONSTRAINTS);
        }
	}
	
	public static boolean isValidContact(String test, String validationRegex) {
        return test.matches(validationRegex);
    }
	
	@Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Contact // instanceof handles nulls
                && this.value.equals(((Contact) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }


    public boolean isPrivate() {
        return isPrivate;
    }
}

package seedu.addressbook.data.person;

import seedu.addressbook.data.exception.IllegalValueException;

/**
 * Represents a Person's address in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidAddress(String)}
 */
public class Block {

    public static final String EXAMPLE = "123";
    public static final String MESSAGE_BLOCK_CONSTRAINTS = "Block must be in numbers";
    public static final String BLOCK_VALIDATION_REGEX = "[\\d]+";

    public final String blockNumber;

    /**
     * Validates given address.
     *
     * @throws IllegalValueException if given address string is invalid.
     */
    public Block(String blockNumber) throws IllegalValueException {
    	if (!isValidBlock(blockNumber)) {
    		throw new IllegalValueException(MESSAGE_BLOCK_CONSTRAINTS);
        }
        this.blockNumber = blockNumber; 
    }

    private boolean isValidBlock(String test) {
		// TODO Auto-generated method stub
		return test.matches(BLOCK_VALIDATION_REGEX);
	}

	@Override
    public String toString() {
        return blockNumber;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Block // instanceof handles nulls
                && this.blockNumber.equals(((Block) other).toString())); // state check
    }

    @Override
    public int hashCode() {
        return blockNumber.hashCode();
    }
}
package seedu.addressbook.data.person;

import java.util.regex.Pattern;
import java.util.regex.Matcher;
import seedu.addressbook.data.exception.IllegalValueException;

/**
 * Represents a Person's address in the address book. Guarantees: immutable; is
 * valid as declared in {@link #isValidAddress(String)}
 */
public class Address {

	public static final String EXAMPLE = "123, Clementi Ave 3, #12-34, 231534";
	public static final String MESSAGE_ADDRESS_CONSTRAINTS = "Person addresses must have a block, street, unit and postal code seperated by ,";
	public static final Pattern ADDRESS_VALIDATION_REGEX = Pattern.compile("(?<block>[^/]+)" 
			+ ", (?<street>[^/]+)" 
			+ ", (?<unit>[^/]+)" 
			+ ", (?<postalCode>[^/]+)");

	private final Block blockNumber;
	private final Street streetName;
	private final Unit unitNumber;
	private final PostalCode postalCode;
	private boolean isPrivate;

	/**
	 * Validates given address.
	 *
	 * @throws IllegalValueException
	 *             if given address string is invalid.
	 */
	public Address(String address, boolean isPrivate) throws IllegalValueException {
		this.isPrivate = isPrivate;
		final Matcher matcher = ADDRESS_VALIDATION_REGEX.matcher(address);
		if (!isValidAddress(matcher)) {
			throw new IllegalValueException(MESSAGE_ADDRESS_CONSTRAINTS);
		}
		this.blockNumber = new Block(matcher.group("block"));
		this.streetName = new Street(matcher.group("street"));
		this.unitNumber = new Unit(matcher.group("unit"));
		this.postalCode = new PostalCode(matcher.group("postalCode"));
	}

	/**
	 * Returns true if a given string is a valid person address.
	 */
	public static boolean isValidAddress(Matcher matcher) {
		return matcher.matches();
	}

	@Override
	public String toString() {
		return blockNumber.toString() + ", " + streetName.toString() + ", " + unitNumber.toString() + ", "
				+ postalCode.toString();
	}

	@Override
	public boolean equals(Object other) {
		return other == this // short circuit if same object
				|| (other instanceof Address // instanceof handles nulls
						&& this.blockNumber.equals(((Address) other).blockNumber) // state check
						&& this.streetName.equals(((Address) other).streetName)
						&& this.unitNumber.equals(((Address) other).unitNumber)
						&& this.postalCode.equals(((Address) other).postalCode));
	}

	@Override
	public int hashCode() {
		return this.toString().hashCode();
	}

	public boolean isPrivate() {
		return isPrivate;
	}
}
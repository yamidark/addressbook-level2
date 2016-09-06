package seedu.addressbook.data.person;

import seedu.addressbook.data.tag.Tag;
import seedu.addressbook.data.tag.UniqueTagList;

/**
 * A read-only immutable interface for a Person in the addressbook.
 * Implementations should guarantee: details are present and not null, field values are validated.
 */
public interface ReadOnlyPerson {

    Name getName();
    Phone getPhone();
    Email getEmail();
    Address getAddress();
    
    static final int NAME_INDEX = 0;
    static final int PHONE_INDEX = 1;
    static final int EMAIL_INDEX = 2;
    static final int ADDRESS_INDEX = 3;
    /**
     * The returned TagList is a deep copy of the internal TagList,
     * changes on the returned list will not affect the person's internal tags.
     */
    UniqueTagList getTags();

    /**
     * Returns true if the values inside this object is same as those of the other (Note: interfaces cannot override .equals)
     */
    default boolean isSameStateAs(ReadOnlyPerson other) {
        return other == this // short circuit if same object
                || (other != null // this is first to avoid NPE below
                && other.getName().equals(this.getName()) // state checks here onwards
                && other.getPhone().equals(this.getPhone())
                && other.getEmail().equals(this.getEmail())
                && other.getAddress().equals(this.getAddress()));
    }

    /**
     * Formats the person as text, showing all contact details.
     */
    default String getAsTextShowAll() {
        final StringBuilder builder = new StringBuilder();
        final String detailIsPrivate = "(private) ";
        builder.append(getName())
                .append(" Phone: ");
        if (getPhone().isPrivate()) {
            builder.append(detailIsPrivate);
        }
        builder.append(getPhone())
                .append(" Email: ");
        if (getEmail().isPrivate()) {
            builder.append(detailIsPrivate);
        }
        builder.append(getEmail())
                .append(" Address: ");
        if (getAddress().isPrivate()) {
            builder.append(detailIsPrivate);
        }
        builder.append(getAddress())
                .append(" Tags: ");
        for (Tag tag : getTags()) {
            builder.append(tag);
        }
        return builder.toString();
    }

    /**
     * Formats a person as text, showing only non-private contact details.
     */
    default String getAsTextHidePrivate() {
        final StringBuilder builder = new StringBuilder();
        Printable[] printablesToPrint = new Printable[4];
        printablesToPrint[NAME_INDEX] = getName();
        if (!getPhone().isPrivate()) {
        	printablesToPrint[PHONE_INDEX] = getPhone();
        }
        if (!getEmail().isPrivate()) {
        	printablesToPrint[EMAIL_INDEX] = getEmail();
        }
        if (!getAddress().isPrivate()) {
        	printablesToPrint[ADDRESS_INDEX] = getAddress();
        }
        builder.append(getPrintableString(printablesToPrint));
        builder.append("Tags: ");
        for (Tag tag : getTags()) {
            builder.append(tag);
        }
        return builder.toString();
    }
    
    /**
     * Returns a concatenated version of the printable strings of each object.
     */
   static String getPrintableString(Printable... printables){
	   final StringBuilder printablesBuilder = new StringBuilder();
	   for(Printable item: printables) {
		   if (item == null) {
			   continue;
		   }
		   printablesBuilder.append(item.getPrintableString()).append(", ");
	   }
	   return printablesBuilder.toString();
   }
}

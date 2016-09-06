package seedu.addressbook.data.tag;

import java.util.ArrayList;

import seedu.addressbook.data.person.Person;

/**
 * 
 * stores a list of tags added or removed in the session
 *
 */
public class Tagging {
	
	private static final ArrayList<String> TAGGINGLIST = new ArrayList<String>();
	
	/**
	 * stores the tag added as a string in the TAGGINGLIST
	 * @param person Person object whom had tag updated
	 * @param tagValue tag updated
	 */
	public static void addToTaggingList(Person person, String tagValue, boolean toAdd) {
		if (toAdd) {
			TAGGINGLIST.add("+ " + person.getName().toString() + " " + tagValue);
		} else {
			TAGGINGLIST.add("- " + person.getName().toString() + " " + tagValue);
		}
	}
	
	
	/**
	 * 
	 * @return the TAGGINGLIST 
	 */
	public static ArrayList<String> getTaggingList() {
		return TAGGINGLIST;
	}
}

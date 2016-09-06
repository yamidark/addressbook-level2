package seedu.addressbook.data.tag;

import java.util.ArrayList;

import seedu.addressbook.data.person.Person;

public class Tagging {
	
	private static final ArrayList<String> TAGGINGLIST = new ArrayList<String>();
	
	
	public static void addAddingTagging(Person person, String tagValue) {
		TAGGINGLIST.add("+ " + person.getName().toString() + " " + tagValue);
	}
	
	public static void addRemovingTagging(Person person, String tagValue) {
		TAGGINGLIST.add("- " + person.getName().toString() + " " + tagValue);
	}
	
	public static ArrayList<String> getTaggingList() {
		return TAGGINGLIST;
	}
}

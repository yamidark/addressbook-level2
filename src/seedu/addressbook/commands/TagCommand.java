package seedu.addressbook.commands;

import seedu.addressbook.common.Messages;
import seedu.addressbook.data.exception.IllegalValueException;
import seedu.addressbook.data.person.Person;
import seedu.addressbook.data.person.ReadOnlyPerson;
import seedu.addressbook.data.person.UniquePersonList.DuplicatePersonException;
import seedu.addressbook.data.person.UniquePersonList.PersonNotFoundException;
import seedu.addressbook.data.tag.Tag;
import seedu.addressbook.data.tag.Tagging;
import seedu.addressbook.data.tag.UniqueTagList;
import seedu.addressbook.data.tag.UniqueTagList.DuplicateTagException;
import seedu.addressbook.data.tag.UniqueTagList.TagNotFoundException;

public class TagCommand extends Command {
	
	public static final String COMMAND_WORD = "tag";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds or removes a tag to the person given "
            + "Parameters: +/- index tag"
            + "Example: " + COMMAND_WORD + " + i/2 t/friend";

	private static final String MESSAGE_UPDATED_TAG_SUCCESS = "Successfully updated %1$s tag";
    
    private final String tagValue;
    private final boolean toAdd;
    
    public TagCommand(int targetVisibleIndex, String tagValue, boolean toAdd) {
    	super(targetVisibleIndex);
    	this.tagValue = tagValue;
    	this.toAdd = toAdd;
    }

	@Override
	public CommandResult execute() {
		try {
			final ReadOnlyPerson oldPerson = getTargetPerson();
			final Person updatedPerson = new Person(oldPerson);
			UniqueTagList updatedTags = oldPerson.getTags();
			if (toAdd) {
				updatedTags.add(new Tag(tagValue));
				Tagging.addAddingTagging(updatedPerson, tagValue);
			} else {
				updatedTags.remove(new Tag(tagValue));
				Tagging.addRemovingTagging(updatedPerson, tagValue);
			}
			addressBook.removePerson(oldPerson);
			updatedPerson.setTags(updatedTags);
			addressBook.addPerson(updatedPerson);
			return new CommandResult(String.format(MESSAGE_UPDATED_TAG_SUCCESS, updatedPerson.getName().toString()));
		} catch (PersonNotFoundException e) {
			return new CommandResult(Messages.MESSAGE_PERSON_NOT_IN_ADDRESSBOOK);
		} catch (DuplicateTagException e) {
			return new CommandResult(e.getMessage());
		} catch (IndexOutOfBoundsException e) {
			return new CommandResult(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
		} catch (TagNotFoundException e) {
			return new CommandResult(e.getMessage());
		} catch (DuplicatePersonException e) {
			return new CommandResult(e.getMessage());
		} catch (IllegalValueException e) {
			return new CommandResult("Illegal value given");
		} 
	}
}

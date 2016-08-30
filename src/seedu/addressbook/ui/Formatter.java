package seedu.addressbook.ui;

import static seedu.addressbook.common.Messages.MESSAGE_GOODBYE;
import static seedu.addressbook.common.Messages.MESSAGE_INIT_FAILED;
import static seedu.addressbook.common.Messages.MESSAGE_PROGRAM_LAUNCH_ARGS_USAGE;
import static seedu.addressbook.common.Messages.MESSAGE_USING_STORAGE_FILE;
import static seedu.addressbook.common.Messages.MESSAGE_WELCOME;

import java.util.List;
import seedu.addressbook.commands.CommandResult;


public class Formatter {
	
	private static final String LINE_PREFIX = "|| ";

    /** A platform independent line separator. */
    private static final String LS = System.lineSeparator();

    private static final String DIVIDER = "===================================================";

    /** Format of indexed list item */
    private static final String MESSAGE_INDEXED_LIST_ITEM = "\t%1$d. %2$s";


    /** Offset required to convert between 1-indexing and 0-indexing.  */
    public static final int DISPLAYED_INDEX_OFFSET = 1;
    
	public Formatter() {
		
	}

	public String[] formatWelcomeMessage(String version, String storageFilePath) {
		String storageFileInfo = String.format(MESSAGE_USING_STORAGE_FILE, storageFilePath);
		return new String[] {DIVIDER, DIVIDER, MESSAGE_WELCOME, version, MESSAGE_PROGRAM_LAUNCH_ARGS_USAGE, storageFileInfo,
				DIVIDER};
	}
	
	public String[] formatGoodByeMessage() {
		return new String[] {MESSAGE_GOODBYE, DIVIDER, DIVIDER};
	}
	
	public String[] formatInitFailedMessage() {
		return new String[]{MESSAGE_INIT_FAILED, DIVIDER, DIVIDER};
	}
	
	public String[] formatResultMessage(CommandResult result) {
        return new String[]{result.feedbackToUser, DIVIDER};
	}
	
	public String formatIndexedList(List<String> listItems) {
		final StringBuilder formatted = new StringBuilder();
        int displayIndex = 0 + DISPLAYED_INDEX_OFFSET;
        for (String listItem : listItems) {
            formatted.append(formatIndexedListItem(displayIndex, listItem)).append("\n");
            displayIndex++;
        }
        return formatted.toString();
	}
	
	public String formatIndexedListItem(int visibleIndex, String listItem) {
		return String.format(MESSAGE_INDEXED_LIST_ITEM, visibleIndex, listItem);
	}
	
	public String formatUserMessage(String message) {
		return LINE_PREFIX + message.replace("\n", LS + LINE_PREFIX);
	}
	
	public String formatUserCommand() {
		return LINE_PREFIX + "Enter command: ";
	}
}

package seedu.addressbook;

import seedu.addressbook.data.person.ReadOnlyPerson;
import seedu.addressbook.storage.StorageFile.*;

import seedu.addressbook.commands.*;
import seedu.addressbook.data.AddressBook;
import seedu.addressbook.parser.Parser;
import seedu.addressbook.storage.StorageFile;
import seedu.addressbook.ui.TextUi;

import java.io.FileNotFoundException;
import java.nio.file.Files;
import java.util.Collections;
import java.util.List;
import java.util.Optional;


/**
 * Entry point of the Address Book application.
 * Initializes the application and starts the interaction with the user.
 */
public class Main {

    /** Version info of the program. */
    public static final String VERSION = "AddessBook Level 2 - Version 1.0";

    private TextUi ui;
    private StorageFile storage;
    private AddressBook addressBook;

    /** The list of person shown to the user most recently.  */
    private List<? extends ReadOnlyPerson> lastShownList = Collections.emptyList();

    public static final String FILE_RECREATED_MESSAGE = "Storage file has been recreated.";
    
    public static void main(String... launchArgs) {
        new Main().run(launchArgs);
    }

    /** Runs the program until termination.  */
    public void run(String[] launchArgs) {
        start(launchArgs);
        runCommandLoopUntilExitCommand();
        exit();
    }

    /**
     * Sets up the required objects, loads up the data from the storage file, and prints the welcome message.
     *
     * @param launchArgs arguments supplied by the user at program launch
     *
     */
    private void start(String[] launchArgs) {
        try {
            this.ui = new TextUi();
            this.storage = initializeStorage(launchArgs);
            this.addressBook = storage.load();
            ui.showWelcomeMessage(VERSION, storage.getPath());

        } catch (InvalidStorageFilePathException | StorageOperationException e) {
            ui.showInitFailedMessage();
            /*
             * ==============NOTE TO STUDENTS=========================================================================
             * We are throwing a RuntimeException which is an 'unchecked' exception. Unchecked exceptions do not need
             * to be declared in the method signature.
             * The reason we are using an unchecked exception here is because the caller cannot reasonably be expected
             * to recover from an exception.
             * Cf https://docs.oracle.com/javase/tutorial/essential/exceptions/runtime.html
             * =======================================================================================================
             */
            throw new RuntimeException(e);
        }
    }

    /** Prints the Goodbye message and exits. */
    private void exit() {
        ui.showGoodbyeMessage();
        System.exit(0);
    }

    /** Reads the user command and executes it, until the user issues the exit command.  */
    private void runCommandLoopUntilExitCommand() {
        Command command;
        do {
            String userCommandText = ui.getUserCommand();
            command = new Parser().parseCommand(userCommandText);
            CommandResult result = executeCommand(command);
            recordResult(result);
            ui.showResultToUser(result);

        } while (!ExitCommand.isExit(command));
    }

    /** Updates the {@link #lastShownList} if the result contains a list of Persons. */
    private void recordResult(CommandResult result) {
        final Optional<List<? extends ReadOnlyPerson>> personList = result.getRelevantPersons();
        if (personList.isPresent()) {
            lastShownList = personList.get();
        }
    }

    /**
     * Executes the command and returns the result.
     * 
     * @param command user command
     * @return result of the command
     */
    private CommandResult executeCommand(Command command)  {
        try {
        	storage.checkFileStillPresent();
            command.setData(addressBook, lastShownList);
            CommandResult result = command.execute(); 
            storage.save(addressBook);
            return result;
        } catch (FileNotFoundException e) {
        	return resolveDeletedStorageFile(command, e);
        } catch (Exception e) {
            ui.showToUser(e.getMessage());
            throw new RuntimeException(e);
        }
    }

	private CommandResult resolveDeletedStorageFile(Command command, FileNotFoundException errorMessage) {
		try {
			ui.showToUser(errorMessage.getMessage());
			storage.recreateStorageFile(addressBook);
			ui.showToUser(FILE_RECREATED_MESSAGE);
			return executeCommand(command);
		} catch (StorageOperationException e) {
            ui.showToUser(e.getMessage());
            throw new RuntimeException(e);
        }
	}
    
	/**
     * Creates the StorageFile object based on the user specified path (if any) or the default storage path.
     * @param launchArgs arguments supplied by the user at program launch
     * @throws InvalidStorageFilePathException if the target file path is incorrect.
     */
    private StorageFile initializeStorage(String[] launchArgs) throws InvalidStorageFilePathException {
        boolean isStorageFileSpecifiedByUser = launchArgs.length > 0;
        return isStorageFileSpecifiedByUser ? new StorageFile(launchArgs[0]) : new StorageFile();
    }
   

}
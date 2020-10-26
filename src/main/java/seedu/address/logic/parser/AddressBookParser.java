package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;

import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.commands.ClearCommand;
import seedu.address.logic.commands.ClientAddCommand;
import seedu.address.logic.commands.ClientDeleteCommand;
import seedu.address.logic.commands.ClientEditCommand;
import seedu.address.logic.commands.ClientFindCommand;
import seedu.address.logic.commands.ClientNoteAddCommand;
import seedu.address.logic.commands.ClientNoteDeleteCommand;
import seedu.address.logic.commands.ClientViewCommand;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CountryFilterCommand;
import seedu.address.logic.commands.CountryNoteAddCommand;
import seedu.address.logic.commands.CountryNoteDeleteCommand;
import seedu.address.logic.commands.CountryNoteViewCommand;
import seedu.address.logic.commands.ExitCommand;
import seedu.address.logic.commands.HelpCommand;
import seedu.address.logic.commands.ListCommand;
import seedu.address.logic.commands.SuggestCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.note.TagNoteMap;

/**
 * Parses user input.
 */
public class AddressBookParser {

    private static final Logger logger = LogsCenter.getLogger(AddressBookParser.class);

    /**
     * Different command type separators
     */
    private static final String CLIENT_TYPE = "client";
    private static final String COUNTRY_TYPE = "country";
    private static final String COUNTRY_NOTE_TYPE = "country note";
    private static final String NOTE_TYPE = "note";

    /**
     * Used for separation of command type and rest of command.
     */
    private static final Pattern BASIC_COMMAND_FORMAT = Pattern.compile("(?<commandType>\\S+)(?<restOfCommand>.*)");

    private final TagNoteMap tagNoteMap;

    public AddressBookParser(TagNoteMap tagNoteMap) {
        this.tagNoteMap = tagNoteMap;
    }

    /**
     * Parses user input into command for execution.
     *
     * @param userInput full user input string
     * @return the command based on the user input
     * @throws ParseException if the user input does not conform the expected format
     */
    public Command parseCommand(String userInput) throws ParseException {
        final Matcher matcher = BASIC_COMMAND_FORMAT.matcher(userInput.trim());
        if (!matcher.matches()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE));
        }

        final String commandType = matcher.group("commandType");
        final String restOfCommand = matcher.group("restOfCommand");
        logger.info("Command Type: " + commandType + " Rest of command: " + restOfCommand);
        switch (commandType) {
        case CLIENT_TYPE:
            return parseClientCommands(restOfCommand);

        case COUNTRY_TYPE:
            return parseCountryCommands(restOfCommand);

        case SuggestCommand.COMMAND_WORD:
            return new SuggestCommandParser().parse(restOfCommand);

        case ClearCommand.COMMAND_WORD:
            return new ClearCommand();

        case ListCommand.COMMAND_WORD:
            return new ListCommand();

        case ExitCommand.COMMAND_WORD:
            return new ExitCommand();

        case HelpCommand.COMMAND_WORD:
            return new HelpCommand();

        default:
            throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
        }
    }


    /**
     * Parses input given that command is of COUNTRY_TYPE (starts with "country")
     *
     * @param input user input with "client" stripped
     * @return command relating to client functions
     * @throws ParseException if input does not conform to expected format
     */
    private Command parseCountryCommands(String input) throws ParseException {
        final Matcher matcher = BASIC_COMMAND_FORMAT.matcher(input.trim());
        if (!matcher.matches()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE));
        }

        final String commandType = matcher.group("commandType");
        final String restOfCommand = matcher.group("restOfCommand");
        logger.info("Command Type: " + commandType + " Rest of command: " + restOfCommand);

        String commandWord = COUNTRY_TYPE + " " + commandType;
        switch (commandWord) {
        case COUNTRY_NOTE_TYPE:
            return parseCountryNoteCommands(restOfCommand);

        case CountryFilterCommand.COMMAND_WORD:
            return new CountryFilterCommandParser().parse(restOfCommand);

        default:
            throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
        }
    }

    /**
     * Parses input given that command is of COUNTRY_NOTE_TYPE (starts with "country note")
     *
     * @param input user input with "country note" stripped
     * @return command relating to country note functions
     * @throws ParseException if input does not conform to expected format
     */
    private Command parseCountryNoteCommands(String input) throws ParseException {
        final Matcher matcher = BASIC_COMMAND_FORMAT.matcher(input.trim());
        if (!matcher.matches()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE));
        }

        final String commandType = matcher.group("commandType");
        final String restOfCommand = matcher.group("restOfCommand");
        logger.info("Command Type: " + commandType + " Rest of command: " + restOfCommand);

        String commandWord = COUNTRY_NOTE_TYPE + " " + commandType;
        switch (commandWord) {
        case CountryNoteAddCommand.COMMAND_WORD:
            return new CountryNoteAddCommandParser().parse(restOfCommand);

        case CountryNoteViewCommand.COMMAND_WORD:
            return new CountryNoteViewCommandParser().parse(restOfCommand);

        case CountryNoteDeleteCommand.COMMAND_WORD:
            return new CountryNoteDeleteCommandParser().parse(restOfCommand);

        default:
            throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
        }
    }

    /**
     * Parses input given that command is of CLIENT_TYPE (starts with "client")
     *
     * @param input user input with "client" stripped
     * @return command relating to client functions
     * @throws ParseException if input does not conform to expected format
     */
    private Command parseClientCommands(String input) throws ParseException {
        final Matcher matcher = BASIC_COMMAND_FORMAT.matcher(input.trim());
        if (!matcher.matches()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE));
        }

        final String commandType = matcher.group("commandType");
        final String restOfCommand = matcher.group("restOfCommand");
        logger.info("Command Type: " + commandType + " Rest of command: " + restOfCommand);

        if (commandType.equals(NOTE_TYPE)) {
            return parseClientNoteCommands(restOfCommand);
        }

        String commandWord = CLIENT_TYPE + " " + commandType;

        switch (commandWord) {
        case ClientAddCommand.COMMAND_WORD:
            return new ClientAddCommandParser().parse(restOfCommand);

        case ClientEditCommand.COMMAND_WORD:
            return new ClientEditCommandParser().parse(restOfCommand);

        case ClientDeleteCommand.COMMAND_WORD:
            return new ClientDeleteCommandParser().parse(restOfCommand);

        case ClientFindCommand.COMMAND_WORD:
            return new ClientFindCommandParser().parse(restOfCommand);

        case ClientViewCommand.COMMAND_WORD:
            return new ClientViewCommandParser().parse(restOfCommand);

        default:
            throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
        }
    }

    /**
     * Parses input given that command is of CLIENT_TYPE + NOTE_TYPE (starts with "client note")
     *
     * @param input user input with "client note" stripped
     * @return command relating to client note functions
     * @throws ParseException if input does not conform to expected format
     */
    private Command parseClientNoteCommands(String input) throws ParseException {
        final Matcher matcher = BASIC_COMMAND_FORMAT.matcher(input.trim());
        if (!matcher.matches()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE));
        }

        final String commandType = matcher.group("commandType");
        final String restOfCommand = matcher.group("restOfCommand");
        logger.info("Command Type: " + commandType + " Rest of command: " + restOfCommand);

        String commandWord = CLIENT_TYPE + " " + NOTE_TYPE + " " + commandType;

        switch (commandWord) {
        case ClientNoteAddCommand.COMMAND_WORD:
            return new ClientNoteAddCommandParser(tagNoteMap).parse(restOfCommand);
        case ClientNoteDeleteCommand.COMMAND_WORD:
            return new ClientNoteDeleteCommandParser().parse(restOfCommand);
        default:
            throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
        }
    }
}

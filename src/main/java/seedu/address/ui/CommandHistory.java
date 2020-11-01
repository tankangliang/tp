package seedu.address.ui;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;

/**
 * A class to encapsulate the history of commands entered by the user.
 */
public class CommandHistory {
    private static final Logger logger = LogsCenter.getLogger(CommandHistory.class);
    private final List<String> history;
    private int pointer;
    private String temp;

    /**
     * Constructor for the history of commands entered.
     *
     * @param history
     */
    private CommandHistory(List<String> history) {
        logger.info(history.toString());
        this.history = history;
        pointer = history.size();
    }

    public static CommandHistory init() {
        return new CommandHistory(new ArrayList<String>());
    }

    /**
     * Adds the given command to the history of commands.
     *
     * @param command
     */
    public void add(String command) {
        requireAllNonNull(command);

        clearTemp();
        history.add(command);
        pointer = history.size();
        clearTemp();
    }

    /**
     * Gets the next command in history.
     *
     * @return The next command.
     */
    public String getNext() {
        if (hasNext()) {
            return history.get(moveForward());
        } else {
            if (hasRudimentary()) {
                return getRudimentary();
            }
            throw new NoSuchElementException();

        }
    }

    /**
     * Gets the previous command in history.
     *
     * @return The previous command.
     */
    public String getPrevious(String command) {
        rudimentaryAdd(command);

        if (hasPrevious()) {
            return history.get(moveBack());
        } else {
            throw new NoSuchElementException();
        }
    }

    /**
     * Checks if there is any command next.
     *
     * @return True if there is another command after the current one.
     */
    public boolean hasNext() {
        return pointer + 1 < history.size();
    }

    /**
     * Checks if there is a rudimentary command in the history.
     *
     * @return True if there is a rudimentary command that is currently in the temporary history.
     */
    public boolean hasRudimentary() {
        return temp != null;
    }

    /**
     * Checks if there is any command previously.
     *
     * @return True if there is another command previous to the current one.
     */
    public boolean hasPrevious() {
        return pointer - 1 >= 0;
    }

    /**
     * Moves the pointer backwards.
     *
     * @return Decremented pointer,
     */
    private int moveBack() {
        assert pointer > 0;
        return --pointer;
    }

    /**
     * Moves the pointer forward.
     *
     * @return Incremented pointer.
     */
    private int moveForward() {
        assert pointer < history.size() - 1;
        return ++pointer;
    }

    /**
     * Adds the current incomplete command to a temp
     * history,
     */
    private void rudimentaryAdd(String command) {
        if (pointer == history.size()) {
            temp = command;
        }
    }

    /**
     * Gets the temp command.
     */
    private String getRudimentary() {
        pointer = history.size();
        return temp;
    }

    /**
     * Clears the temp history.
     */
    private void clearTemp() {
        temp = null;
    }

}

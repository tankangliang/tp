package seedu.address.ui;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.List;
import java.util.NoSuchElementException;

public class CommandHistory {
    private final List<String> history;
    private int pointer;

    /**
     * Constructor for the history of commands entered.
     *
     * @param history
     */
    public CommandHistory(List<String> history) {
        this.history = history;
        pointer = 0;
    }

    /**
     * Adds the given command to the history of commands.
     *
     * @param command
     */
    public void add(String command) {
        requireAllNonNull(command);
        history.add(command);
    }

    /**
     * Gets the current command in history.
     *
     * @return The current command.
     */
    public String getCurrent() {
        return history.get(pointer);
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
            throw new NoSuchElementException();
        }
    }

    /**
     * Gets the previous command in history.
     *
     * @return The previous command.
     */
    public String getPrevious() {
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
     * Checks if there is any command previously.
     *
     * @return True if there is another command previous to the current one.
     */
    public boolean hasPrevious() {
        return pointer - 1 >= 0;
    }

    private int moveBack() {
        assert pointer > 0;
        return --pointer;
    }

    private int moveForward() {
        assert pointer < history.size() - 1;
        return ++pointer;
    }


}

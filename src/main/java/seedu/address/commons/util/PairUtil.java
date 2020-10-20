package seedu.address.commons.util;

import java.util.Objects;

/**
 * A generic Pair utility class that associates two entities.
 *
 * @param <T> The first entity.
 * @param <V> The second entity.
 */
public class PairUtil<T, V> {

    private final T first;
    private final V second;

    /**
     * Initializes a PairUtil with the first and second entity.
     *
     * @param first  The first entity.
     * @param second The second entity.
     */
    public PairUtil(T first, V second) {
        this.first = first;
        this.second = second;
    }

    /**
     * Gets the first entity.
     *
     * @return The first entitiy.
     */
    public T getFirst() {
        return first;
    }

    /**
     * Gets the second entity.
     *
     * @return The second entitiy.
     */
    public V getSecond() {
        return second;
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof PairUtil)) {
            return false;
        }

        // state check
        PairUtil p = (PairUtil) other;

        return first.equals(p.first) && second.equals(p.second);
    }

    @Override
    public int hashCode() {
        return Objects.hash(first, second);
    }
}

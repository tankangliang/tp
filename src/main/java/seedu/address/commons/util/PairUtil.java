package seedu.address.commons.util;

import java.util.Objects;

public class PairUtil<T, V> {
    private final T first;
    private final V second;

    public PairUtil(T first, V second) {
        this.first = first;
        this.second = second;
    }

    public T getFirst() {
        return first;
    }

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

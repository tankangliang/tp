package seedu.address.model.client;

import java.util.function.Predicate;

/**
 * Predicate to check for client availability in their timezone
 */
public class SuggestAvailabilityPredicate implements Predicate<Client> {
    private static final int AVAILABLE_STARTING_HOUR = 18;
    private static final int AVAILABLE_ENDING_HOUR = 22;

    @Override
    public boolean test(Client client) {
        int currHour = client.getTimezone().getCurrHourInTimezone();
        return (currHour >= AVAILABLE_STARTING_HOUR && currHour <= AVAILABLE_ENDING_HOUR);
    }

}

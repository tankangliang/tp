package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.Collections;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.SuggestCommand;
import seedu.address.model.client.Client;
import seedu.address.model.client.SuggestionType;

public class SuggestCommandParserTest {

    private final SuggestCommandParser parser = new SuggestCommandParser();

    @Test
    public void parse_validArgs_returnsSuggestCommand() {
        Predicate<Client> availablePredicate = new SuggestionType("available").getSuggestionPredicate();
        assertParseSuccess(parser, "suggest by/available",
                new SuggestCommand(Collections.singletonList(availablePredicate)));

        // TODO: Add back these tests after contract and frequency predicates have been added
        /*Predicate<Client> contractPredicate = new SuggestionType("contract").getSuggestionPredicate();
        assertParseSuccess(parser, "suggest by/available by/contract",
               new SuggestCommand(Arrays.asList(availablePredicate, contractPredicate)));

        Predicate<Client> frequencyPredicate = new SuggestionType("frequency").getSuggestionPredicate();
        assertParseSuccess(parser, "suggest by/available by/contract by/frequency",
               new SuggestCommand(Arrays.asList(availablePredicate, contractPredicate, frequencyPredicate)));*/
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, "suggest by/availability", SuggestionType.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, "suggest by/", SuggestionType.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, "suggest available",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, SuggestCommand.MESSAGE_USAGE));
    }

}

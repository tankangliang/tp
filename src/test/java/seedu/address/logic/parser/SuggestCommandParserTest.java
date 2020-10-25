package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.LinkedHashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.SuggestCommand;
import seedu.address.model.client.SuggestionType;

public class SuggestCommandParserTest {

    private final SuggestCommandParser parser = new SuggestCommandParser();

    @Test
    public void parse_validArgs_returnsSuggestCommand() {
        Set<SuggestionType> suggestionTypes = new LinkedHashSet<>();
        suggestionTypes.add(new SuggestionType(SuggestionType.BY_AVAILABLE));
        assertParseSuccess(parser, " by/available", new SuggestCommand(suggestionTypes));

        suggestionTypes.add(new SuggestionType(SuggestionType.BY_CONTRACT));
        assertParseSuccess(parser, " by/available by/contract", new SuggestCommand(suggestionTypes));

        /* TODO: Add this back if frequency gets a predicate
        suggestionTypes.add(new SuggestionType(SuggestionType.BY_FREQUENCY));
        assertParseSuccess(parser, " by/available by/contract by/frequency",
               new SuggestCommand(suggestionTypes));*/
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, " by/availability", SuggestionType.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, " by/", SuggestionType.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, " available",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, SuggestCommand.MESSAGE_USAGE));
    }

}

package seedu.address.commons.core;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.nio.file.Paths;
import java.util.logging.Level;

import org.junit.jupiter.api.Test;

public class ConfigTest {

    private final Config defaultConfig = new Config();

    @Test
    public void getLogLevel_returnsSetLogLevel() {
        defaultConfig.setLogLevel(Level.WARNING);
        assertEquals(Level.WARNING, defaultConfig.getLogLevel());
    }

    @Test
    public void getUserPrefsFilePath_returnsSetUserPrefsFilePath() {
        defaultConfig.setUserPrefsFilePath(Paths.get("prefs.json"));
        assertEquals(Paths.get("prefs.json"), defaultConfig.getUserPrefsFilePath());
    }

    @Test
    public void toString_defaultObject_stringReturned() {
        String defaultConfigAsString = "Current log level : INFO\n"
                + "Preference file Location : preferences.json";

        assertEquals(defaultConfigAsString, defaultConfig.toString());
    }

    @Test
    public void equalsMethod() {
        assertNotNull(defaultConfig);
        assertTrue(defaultConfig.equals(defaultConfig));
    }

}

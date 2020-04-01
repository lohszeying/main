package seedu.nova.model;

import java.nio.file.Path;

import seedu.nova.commons.core.GuiSettings;

/**
 * Unmodifiable view of user prefs.
 */
public interface ReadOnlyUserPrefs {

    GuiSettings getGuiSettings();

    Path getNovaFilePath();

}

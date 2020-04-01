package seedu.nova.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.nova.commons.core.LogsCenter;
import seedu.nova.commons.exceptions.DataConversionException;
import seedu.nova.model.Nova;
import seedu.nova.model.ReadOnlyUserPrefs;
import seedu.nova.model.UserPrefs;

/**
 * Manages storage of AddressBook data in local storage.
 */
public class StorageManager implements Storage {

    private static final Logger logger = LogsCenter.getLogger(StorageManager.class);
    private NovaStorage novaStorage;
    private UserPrefsStorage userPrefsStorage;


    public StorageManager(NovaStorage novaStorage, UserPrefsStorage userPrefsStorage) {
        super();
        this.novaStorage = novaStorage;
        this.userPrefsStorage = userPrefsStorage;
    }

    // ================ UserPrefs methods ==============================

    @Override
    public Path getUserPrefsFilePath() {
        return userPrefsStorage.getUserPrefsFilePath();
    }

    @Override
    public Optional<UserPrefs> readUserPrefs() throws DataConversionException, IOException {
        return userPrefsStorage.readUserPrefs();
    }

    @Override
    public void saveUserPrefs(ReadOnlyUserPrefs userPrefs) throws IOException {
        userPrefsStorage.saveUserPrefs(userPrefs);
    }


    // ================ Nova methods ==============================

    @Override
    public Path getNovaFilePath() {
        return novaStorage.getNovaFilePath();
    }

    @Override
    public Optional<Nova> readNova() throws DataConversionException, IOException {
        return readNova(novaStorage.getNovaFilePath());
    }

    @Override
    public Optional<Nova> readNova(Path filePath) throws DataConversionException, IOException {
        logger.fine("Attempting to read data from file: " + filePath);
        return novaStorage.readNova(filePath);
    }

    @Override
    public void saveNova(Nova nova) throws IOException {
        saveNova(nova, novaStorage.getNovaFilePath());
    }

    @Override
    public void saveNova(Nova nova, Path filePath) throws IOException {
        logger.fine("Attempting to write to data file: " + filePath);
        novaStorage.saveNova(nova, filePath);
    }

}

package seedu.nova.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.nova.commons.exceptions.DataConversionException;
import seedu.nova.model.AddressBook;
import seedu.nova.model.Nova;
import seedu.nova.model.ReadOnlyAddressBook;


/**
 * Represents a storage for {@link AddressBook}.
 */
public interface NovaStorage {

    /**
     * Returns the file path of the data file.
     */
    Path getNovaFilePath();

    /**
     * Returns AddressBook data as a {@link ReadOnlyAddressBook}.
     *   Returns {@code Optional.empty()} if storage file is not found.
     * @throws DataConversionException if the data in storage is not in the expected format.
     * @throws IOException if there was any problem when reading from the storage.
     */
    Optional<Nova> readNova() throws DataConversionException, IOException;

    /**
     * @see #getNovaFilePath()
     */
    Optional<Nova> readNova(Path filePath) throws DataConversionException, IOException;

    /**
     * Saves the given {@link ReadOnlyAddressBook} to the storage.
     * @param nova cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    void saveNova(Nova nova) throws IOException;

    /**
     * @see #saveNova(Nova)
     */
    void saveNova(Nova nova, Path filePath) throws IOException;

}

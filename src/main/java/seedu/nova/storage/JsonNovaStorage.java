package seedu.nova.storage;

import static java.util.Objects.requireNonNull;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.nova.commons.core.LogsCenter;
import seedu.nova.commons.exceptions.DataConversionException;
import seedu.nova.commons.exceptions.IllegalValueException;
import seedu.nova.commons.util.FileUtil;
import seedu.nova.commons.util.JsonUtil;
import seedu.nova.model.Nova;

/**
 * A class to access Nova data stored as a json file on the hard disk.
 */
public class JsonNovaStorage implements NovaStorage {

    private static final Logger logger = LogsCenter.getLogger(JsonNovaStorage.class);

    private Path filePath;

    public JsonNovaStorage(Path filePath) {
        this.filePath = filePath;
    }

    public Path getNovaFilePath() {
        return filePath;
    }

    @Override
    public Optional<Nova> readNova() throws DataConversionException {
        return readNova(filePath);
    }

    /**
     * Similar to {@link #readNova()}.
     *
     * @param filePath location of the data. Cannot be null.
     * @throws DataConversionException if the file is not in the correct format.
     */
    public Optional<Nova> readNova(Path filePath) throws DataConversionException {
        requireNonNull(filePath);

        Optional< JsonSerializableNova> jsonNova = JsonUtil.readJsonFile(
                filePath, JsonSerializableNova.class);
        if (!jsonNova.isPresent()) {
            return Optional.empty();
        }

        try {
            return Optional.of(jsonNova.get().toModelType());
        } catch (IllegalValueException ive) {
            logger.info("Illegal values found in " + filePath + ": " + ive.getMessage());
            throw new DataConversionException(ive);
        }
    }

    @Override
    public void saveNova(Nova nova) throws IOException {
        saveNova(nova, filePath);
    }

    /**
     * Similar to {@link #saveNova(Nova)}.
     *
     * @param filePath location of the data. Cannot be null.
     */
    public void saveNova(Nova nova, Path filePath) throws IOException {
        requireNonNull(nova);
        requireNonNull(filePath);

        FileUtil.createIfMissing(filePath);
        JsonUtil.saveJsonFile(new JsonSerializableNova(nova), filePath);
    }

}

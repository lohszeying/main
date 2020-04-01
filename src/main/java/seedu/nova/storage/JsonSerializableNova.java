package seedu.nova.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import seedu.nova.storage.StorageManager;
import seedu.nova.commons.exceptions.IllegalValueException;
import seedu.nova.model.AddressBook;
import seedu.nova.model.Nova;
import seedu.nova.model.ReadOnlyAddressBook;
import seedu.nova.model.VersionedAddressBook;
import seedu.nova.model.person.Person;
import seedu.nova.model.util.SampleDataUtil;

/**
 * An Immutable AddressBook that is serializable to JSON format.
 */
@JsonRootName(value = "nova")
class JsonSerializableNova {

    public static final String MESSAGE_DUPLICATE_PERSON = "Persons list contains duplicate person(s).";

    private final List<JsonAdaptedPerson> persons = new ArrayList<>();

    //add your list of adapted class objects here

    /**
     * Constructs a {@code JsonSerializableNova} with the given persons.
     */
    @JsonCreator
    public JsonSerializableNova(@JsonProperty("persons") List<JsonAdaptedPerson> persons) {
        this.persons.addAll(persons);
    }

    /**
     * Converts a given {@code ReadOnlyAddressBook} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableNova}.
     */
    public JsonSerializableNova(Nova source) {
        persons.addAll(source.getAddressBookNova().getPersonList().stream()
                .map(JsonAdaptedPerson::new).collect(Collectors.toList()));
    }

    /**
     * Javadoc
     * @return javadoc
     * @throws IllegalValueException
     */
    public Nova toModelType() throws IllegalValueException {
        Nova nova = new Nova();
        VersionedAddressBook ab = toModelTypeAb();
        //Call other toModelType();

        nova.setAddressBookNova(ab);
        //call other set methods

        return nova;
    }

    /**
     * Converts this address book into the model's {@code AddressBook} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public VersionedAddressBook toModelTypeAb() throws IllegalValueException {
        ReadOnlyAddressBook initialState;
        AddressBook ab = new AddressBook();

        for (JsonAdaptedPerson jsonAdaptedPerson : persons) {
            Person person = jsonAdaptedPerson.toModelType();
            if (ab.hasPerson(person)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_PERSON);
            }
            ab.addPerson(person);
        }
        initialState = ab;
        VersionedAddressBook addressBook = new VersionedAddressBook(initialState);

        return addressBook;
    }

    //Implement your own classes toModelType methods

}

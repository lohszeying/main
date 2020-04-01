package seedu.nova.logic.commands;

//import static seedu.nova.logic.commands.CommandTestUtil.assertCommandSuccess;
//import static seedu.nova.logic.commands.CommandTestUtil.showPersonAtIndex;
//import static seedu.nova.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
/*
import static seedu.nova.testutil.TypicalPersons.getTypicalAddressBook;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;

//import seedu.nova.logic.commands.abcommands.AbListCommand;

import seedu.nova.model.Model;
import seedu.nova.model.ModelManager;
import seedu.nova.model.Schedule;
import seedu.nova.model.UserPrefs;
 */

/**
 * Contains integration tests (interaction with the Model) and unit tests for AbListCommand.
 */
public class AbListCommandTest {
    /*
    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs(), new Schedule(LocalDate.of(2020, 1, 13),
                LocalDate.of(2020, 5, 3)));
        expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs(), new Schedule(LocalDate.of(2020, 1,
                13), LocalDate.of(2020, 5, 3)));
    }

    /*@Test
    public void execute_listIsNotFiltered_showsSameList() {
        assertCommandSuccess(new AbListCommand(), model, AbListCommand.MESSAGE_SUCCESS, expectedModel);
    }*/

    /*@Test
    public void execute_listIsFiltered_showsEverything() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);
        assertCommandSuccess(new AbListCommand(), model, AbListCommand.MESSAGE_SUCCESS, expectedModel);
    }*/
}

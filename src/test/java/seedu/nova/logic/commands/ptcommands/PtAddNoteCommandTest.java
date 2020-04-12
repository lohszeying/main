package seedu.nova.logic.commands.ptcommands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.nova.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.nova.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.nova.testutil.Assert.assertThrows;
import static seedu.nova.testutil.TypicalPersons.getTypicalAddressBook;
import static seedu.nova.testutil.TypicalPtTasks.getTypicalProgressTracker;

import org.junit.jupiter.api.Test;

import seedu.nova.model.Model;
import seedu.nova.model.ModelManager;
import seedu.nova.model.Nova;
import seedu.nova.model.UserPrefs;
import seedu.nova.model.VersionedAddressBook;
import seedu.nova.model.progresstracker.Ip;
import seedu.nova.model.progresstracker.ProgressTracker;
import seedu.nova.model.progresstracker.Project;
import seedu.nova.model.progresstracker.PtTask;
import seedu.nova.model.progresstracker.Tp;
import seedu.nova.testutil.PtTaskBuilder;

public class PtAddNoteCommandTest {

    private final Nova nova = new Nova();
    private final ProgressTracker pt = getTypicalProgressTracker();
    private final VersionedAddressBook ab = new VersionedAddressBook(getTypicalAddressBook());

    @Test
    public void constructor_nullProject_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new PtAddNoteCommand(1, 1, null,
                "new note"));
    }

    @Test
    public void constructor_nullNote_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new PtAddNoteCommand(1, 1, null,
                null));
    }

    @Test
    public void execute_validProjectAndWeekAndTaskIndex_success() {
        nova.setProgressTrackerNova(pt);
        nova.setAddressBookNova(ab);
        Model model = new ModelManager(nova, new UserPrefs());

        PtAddNoteCommand ptAddNoteCommand = new PtAddNoteCommand(2, 1, "ip", "new note");

        String expectedMessage = String.format(PtAddNoteCommand.MESSAGE_SUCCESS, 1, 2, "IP");

        ModelManager expectedModel = new ModelManager(model.getNova(), new UserPrefs());

        assertCommandSuccess(ptAddNoteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidWeekIndex_throwsCommandException() {
        nova.setProgressTrackerNova(pt);
        nova.setAddressBookNova(ab);
        Model model = new ModelManager(nova, new UserPrefs());

        PtAddNoteCommand ptAddNoteCommand1 = new PtAddNoteCommand(3, 1, "ip", "new note");

        assertCommandFailure(ptAddNoteCommand1, model, PtAddNoteCommand.MESSAGE_FAILURE);

        PtAddNoteCommand ptAddNoteCommand2 = new PtAddNoteCommand(14, 1, "ip", "new note");

        assertCommandFailure(ptAddNoteCommand2, model, PtAddNoteCommand.MESSAGE_NOWEEK);
    }

    @Test
    public void execute_invalidTaskIndex_throwsCommandException() {
        nova.setProgressTrackerNova(pt);
        nova.setAddressBookNova(ab);
        Model model = new ModelManager(nova, new UserPrefs());

        PtAddNoteCommand ptAddNoteCommand = new PtAddNoteCommand(1, 2, "ip", "new note");

        assertCommandFailure(ptAddNoteCommand, model, PtAddNoteCommand.MESSAGE_FAILURE);
    }

    @Test
    public void equals() {
        Project ipProject = new Ip();
        Project tpProject = new Tp();
        PtTask ipProjectTask = new PtTaskBuilder().withProject(ipProject).build();
        PtTask tpProjectTask = new PtTaskBuilder().withProject(tpProject).build();

        PtAddNoteCommand addNoteIpProjectTaskCommand = new PtAddNoteCommand(1, 1,
                ipProjectTask.getProjectName(), "new note");
        PtAddNoteCommand addNoteTpProjectTaskCommand = new PtAddNoteCommand(1, 1,
                tpProjectTask.getProjectName(), "new note");

        // same object -> returns true
        assertTrue(addNoteIpProjectTaskCommand.equals(addNoteIpProjectTaskCommand));

        // same values -> returns true
        PtAddNoteCommand addNoteIpProjectTaskCommandCopy = new PtAddNoteCommand(1, 1,
                ipProjectTask.getProjectName(), "new note");
        assertTrue(addNoteIpProjectTaskCommand.equals(addNoteIpProjectTaskCommandCopy));

        //different types -> returns false
        assertFalse(addNoteIpProjectTaskCommand.equals(1));

        // null -> returns false
        assertFalse(addNoteIpProjectTaskCommand.equals(null));

        // different person -> returns false
        assertFalse(addNoteIpProjectTaskCommand.equals(addNoteTpProjectTaskCommand));
    }
}

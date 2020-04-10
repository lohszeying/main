package seedu.nova.logic.commands.ptcommands;

import static java.util.Objects.requireNonNull;
import static seedu.nova.logic.parser.CliSyntax.PREFIX_DESC;
import static seedu.nova.logic.parser.CliSyntax.PREFIX_PROJECT;
import static seedu.nova.logic.parser.CliSyntax.PREFIX_WEEK;

import seedu.nova.logic.commands.Command;
import seedu.nova.logic.commands.CommandResult;
import seedu.nova.logic.commands.exceptions.CommandException;
import seedu.nova.model.Model;
import seedu.nova.model.progresstracker.ProgressTracker;
import seedu.nova.model.progresstracker.Project;
import seedu.nova.model.progresstracker.PtNote;
import seedu.nova.model.progresstracker.PtTask;
import seedu.nova.model.progresstracker.TaskDesc;

/**
 * Adds task to specified week
 */
public class PtAddCommand extends Command {
    public static final String COMMAND_WORD = "add";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds task in the "
            + "project in the specified week. "
            + "Parameters: "
            + PREFIX_PROJECT + "PROJECT "
            + PREFIX_WEEK + "WEEK "
            + PREFIX_DESC + "TASK DESCRIPTION \n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_PROJECT + "Ip "
            + PREFIX_WEEK + "2"
            + PREFIX_DESC + "Implement javafx";

    public static final String MESSAGE_NOWEEK = "No week beyond week 13";

    private int weekNum;
    private String project;
    private String taskDesc;

    public PtAddCommand(int weekNum, String project, String taskDesc) {
        this.weekNum = weekNum;
        this.project = project.trim().toLowerCase();
        this.taskDesc = taskDesc;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        boolean isOver13 = weekNum > 13;

        if (isOver13) {
            throw new CommandException(MESSAGE_NOWEEK);
        } else {
            ProgressTracker pt = model.getProgressTracker();
            Project project;
            boolean isIpProject = this.project.equals("ip");

            if (isIpProject) {
                project = pt.getIp();
            } else {
                project = pt.getTp();
            }

            //Create new task
            TaskDesc taskDesc = new TaskDesc(this.taskDesc);
            PtTask newTask = new PtTask(taskDesc, project, new PtNote(""), this.weekNum, false);

            model.addPtTask(this.project, weekNum, newTask);

            String projectName = this.project.toUpperCase();
            String result = "Added task to week " + weekNum + " of " + projectName;

            return new CommandResult(result, false, false);
        }
    }
}

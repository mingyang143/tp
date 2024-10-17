package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.Arrays;
import java.util.Comparator;

import javafx.collections.ObservableList;
import seedu.address.logic.CommandHistory;
import seedu.address.model.Model;
import seedu.address.model.person.Person;

/**
 * View the chart of tutees in increasing order.
 */
public class ViewTuteeChartCommand extends Command {
    public static final String COMMAND_WORD = "vtc";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": View the chart of tutees.\n"
            + "Example: " + COMMAND_WORD;

    public static final String MESSAGE_SUCCESS = "Here is the chart of tutees in increasing order.";

    /**
     * Executes the command and returns the result message.
     *
     * @param model {@code Model} which the command should operate on.
     * @param history {@code CommandHistory} which the command should operate on.
     * @return feedback message of the operation result for display
     */
    @Override
    public CommandResult execute(Model model, CommandHistory history) {
        requireNonNull(model);
        model.updateFilteredPersonList(Model.PREDICATE_SHOW_ALL_PERSONS);
        ObservableList<Person> tuteeList = model.getFilteredPersonList();
        Person[] tuteeArray = tuteeList.toArray(new Person[0]);
        Arrays.sort(tuteeArray, Comparator.comparing(Person -> Person.getHours().getHoursInt()));
        return new CommandResult(MESSAGE_SUCCESS, false, false,
                true, tuteeArray);
    }

}

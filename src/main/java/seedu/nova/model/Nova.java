package seedu.nova.model;

import seedu.nova.model.progresstracker.ProgressTracker;

/**
 * Nova class
 */
public class Nova {
    private AddressBook addressBook = new AddressBook();
    private Schedule schedule;
    private ProgressTracker progressTracker;

    public AddressBook getAddressBookNova() {
        return this.addressBook;
    }

    public Schedule getScheduleNova() {
        return this.schedule;
    }

    public ProgressTracker getProgressTracker() {
        return this.progressTracker;
    }

    public void setAddressBookNova(AddressBook addressBook) {
        this.addressBook = addressBook;
    }

    public void setScheduleNova(Schedule schedule) {
        this.schedule = schedule;
    }

    public void setProgressTrackerNova(ProgressTracker progressTracker) {
        this.progressTracker = progressTracker;
    }
}

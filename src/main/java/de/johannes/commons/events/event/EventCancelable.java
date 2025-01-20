package de.johannes.commons.events.event;

public class EventCancelable extends Event{

    private boolean canceled;


    public boolean isCanceled() {
        return canceled;
    }

    public void setCanceled(boolean canceled) {
        this.canceled = canceled;
    }

    public void cancel() {
        this.canceled = true;
    }
}

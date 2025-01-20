package de.johannes.commons.exceptions;

import de.johannes.commons.events.event.Event;

public class EventException extends RuntimeException {
    public EventException(Event event) {
        super("Error trying to call "+event.getClass().getSimpleName());
    }
}

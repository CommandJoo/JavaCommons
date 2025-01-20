package de.johannes.commons.events;

import de.johannes.commons.events.event.Event;

public class TestEvent extends Event {

    public String value;

    public TestEvent(String value) {
        this.value = value;
    }

}

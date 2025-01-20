package de.johannes.commons.events;

import de.johannes.commons.events.event.Event;
import de.johannes.commons.exceptions.EventException;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class EventManager {

    private final List<EventListener> listeners;

    public EventManager() {
        this.listeners = new ArrayList<>();
    }

    public void register(EventListener listener) {
        this.listeners.add((EventListener) listener);
    }

    public void unregister(EventListener listener) {
        this.listeners.remove(listener);
    }

    public void callEvent(Event event) {
        List<EventMethod> executioners = new ArrayList<>();
        for(EventListener listener : this.listeners) {
            for(Method method : listener.getClass().getDeclaredMethods()) {
                if(method.getParameterCount() == 1 && method.getParameterTypes()[0] == event.getClass()) {
                    executioners.add(new EventMethod(method, listener));
                }
            }
        }
        executioners.sort(Comparator.comparingInt(o -> -o.priority));
        executioners.forEach(ev -> {
            try {
                Method method = ev.method;
                if(!method.canAccess(ev.listener)) {
                    method.setAccessible(true);
                    method.invoke(ev.listener, event);
                    method.setAccessible(false);
                } else{
                    method.invoke(ev.listener, event);
                }
            } catch(Exception ex) {
                throw new EventException(event);
            }
        });
    }

    public static class EventMethod {
        final Method method;
        final EventListener listener;
        final byte priority;

        public EventMethod(Method method, EventListener listener) {
            this.method = method;
            this.listener = listener;
            this.priority = priority(method);
        }

        private byte priority(Method method) {
            if(!method.isAnnotationPresent(Priority.class)) return Priority.MEDIUM;
            else return method.getAnnotation(Priority.class).type();
        }
    }

}

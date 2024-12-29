package de.johannes.commons.util;

public class Timer {

    private long lastTime = 0;

    public Timer() {
        this.reset();
    }

    public void reset() {
        this.lastTime = System.nanoTime();
    }

    public long passed() {
        return System.nanoTime() - lastTime;
    }

    public boolean check(int time) {
        return passed() >= time;
    }

}
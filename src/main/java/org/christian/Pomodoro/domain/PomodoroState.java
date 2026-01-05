package org.christian.Pomodoro.domain;

public class PomodoroState {
    private final int minutes;
    private final int seconds;
    private final int initialSeconds;

    public PomodoroState(int minutes, int seconds, int initialSeconds) {
        this.minutes = minutes;
        this.seconds = seconds;
        this.initialSeconds = initialSeconds;
    }

    public int getMinutes() { return minutes; }
    public int getSeconds() { return seconds; }

    public double getProgress() {
        int currentTotal = (minutes * 60) + seconds;
        if (initialSeconds == 0) return 0;
        return (double) currentTotal / initialSeconds;
    }
}

package org.christian.Pomodoro.domain;

public class PomodoroState {

    private int minutes;
    private int seconds;
    private boolean isRunning;

    public PomodoroState() {
    }

    public PomodoroState(int minutes, int seconds, boolean isRunning) {
        this.minutes = minutes;
        this.seconds = seconds;
        this.isRunning = isRunning;
    }

    public int getMinutes() {
        return minutes;
    }

    public void setMinutes(int minutes) {
        this.minutes = minutes;
    }

    public int getSeconds() {
        return seconds;
    }

    public void setSeconds(int seconds) {
        this.seconds = seconds;
    }

    public boolean isRunning() {
        return isRunning;
    }

    public void setRunning(boolean running) {
        isRunning = running;
    }

    public double getProgress() {
       
        double totalSeconds = 25 * 60.0;
        double currentSeconds = (minutes * 60) + seconds;

      
        return currentSeconds / totalSeconds;
    }
}

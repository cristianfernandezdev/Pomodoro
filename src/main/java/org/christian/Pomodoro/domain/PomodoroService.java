package org.christian.Pomodoro.domain;

public interface PomodoroService {
    PomodoroState getState();
    void start();
    void stop();
    void reset();
    void setCustomTime(int minutes);
    void setListener(PomodoroListener listener);
}

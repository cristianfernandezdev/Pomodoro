package org.christian.Pomodoro.domain;

public interface PomodoroListener {
    void onTick(PomodoroState state);
    void onFinish();
}

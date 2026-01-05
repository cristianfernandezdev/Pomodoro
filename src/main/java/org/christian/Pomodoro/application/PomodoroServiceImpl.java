package org.christian.Pomodoro.application;

import org.christian.Pomodoro.domain.PomodoroListener;
import org.christian.Pomodoro.domain.PomodoroService;
import org.christian.Pomodoro.domain.PomodoroState;
import org.springframework.stereotype.Service;

import java.util.Timer;
import java.util.TimerTask;

@Service
public class PomodoroServiceImpl implements PomodoroService {

    private int minutes = 25;
    private int seconds = 0;
    private int initialTotalSeconds = 25 * 60;

    private Timer timer;
    private PomodoroListener listener;

    @Override
    public void setListener(PomodoroListener listener) {
        this.listener = listener;
    }

    @Override
    public void start() {
        if (timer != null) return;

        timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                tick();
            }
        }, 0, 1000);
    }

    private void tick() {
        if (seconds > 0) {
            seconds--;
        } else if (minutes > 0) {
            minutes--;
            seconds = 59;
        } else {
            stop();
            if (listener != null) listener.onFinish();
            return;
        }
        notifyUpdate();
    }

    private void notifyUpdate() {
        if (listener != null) {
            listener.onTick(new PomodoroState(minutes, seconds, initialTotalSeconds));
        }
    }

    @Override
    public void stop() {
        if (timer != null) {
            timer.cancel();
            timer = null;
        }
    }

    @Override
    public void reset() {
        stop();
        this.minutes = 25;
        this.seconds = 0;
        this.initialTotalSeconds = 25 * 60;
        notifyUpdate();
    }

    @Override
    public void setCustomTime(int newMinutes) {
        stop();
        this.minutes = newMinutes;
        this.seconds = 0;
        this.initialTotalSeconds = newMinutes * 60;
        notifyUpdate();
    }
}

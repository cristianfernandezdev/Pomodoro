package org.christian.Pomodoro.application;

import com.vaadin.flow.spring.annotation.UIScope;
import org.christian.Pomodoro.domain.PomodoroState;
import org.christian.Pomodoro.domain.PomodoroListener;
import org.christian.Pomodoro.domain.PomodoroService;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

@Service
@UIScope
public class PomodoroServiceImpl implements PomodoroService, DisposableBean {

    private final PomodoroState state;
    private Timer timer;
  
    private final List<PomodoroListener> listeners = new ArrayList<>();

    public PomodoroServiceImpl() {
        this.state = new PomodoroState();
        // Configuración inicial
        this.state.setMinutes(25);
        this.state.setSeconds(0);
        this.state.setRunning(false);
    }

    @Override
    public PomodoroState getState() {
        return state;
    }

    

    @Override
    public void start() { 
        if (timer != null) {
            timer.cancel();
        }

        timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                try {
                    tick();
                } catch (Exception e) {
                    System.out.println("Error ignorado en timer: " + e.getMessage());
                }
            }
        }, 0, 1000);

        state.setRunning(true);
        notifyUpdate();
    }

    @Override
    public void stop() { 
        if (timer != null) {
            timer.cancel();
            timer = null;
        }
        state.setRunning(false);
        notifyUpdate();
    }

    @Override
    public void reset() { 
        stop();
        state.setMinutes(25);
        state.setSeconds(0);
        notifyUpdate();
    }

    @Override
    public void setCustomTime(int minutes) {
        stop();
        state.setMinutes(minutes);
        state.setSeconds(0);
        notifyUpdate();
    }

    @Override
    public void setListener(PomodoroListener listener) {
        listeners.add(listener);
    }

   

    private void tick() {
        int min = state.getMinutes();
        int sec = state.getSeconds();

        if (sec > 0) {
            sec--;
        } else if (min > 0) {
            min--;
            sec = 59;
        } else {
            stop(); 
        }

        state.setMinutes(min);
        state.setSeconds(sec);
        notifyUpdate();
    }

    private void notifyUpdate() {
        for (PomodoroListener listener : listeners) {
            listener.onTick(state);
        }
    }

    @Override
    public void destroy() {
        stop();
    }
}

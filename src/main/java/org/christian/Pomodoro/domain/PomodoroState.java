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

    // --- NUEVO MÉTODO PARA QUE FUNCIONE LA BARRA CIRCULAR ---
    public double getProgress() {
        // Calculamos el % completado basándonos en 25 minutos (1500 segundos)
        // Si quieres que sea dinámico, habría que guardar el "tiempo total" en una variable.
        double totalSeconds = 25 * 60.0;
        double currentSeconds = (minutes * 60) + seconds;

        // Devuelve un valor entre 0.0 y 1.0
        return currentSeconds / totalSeconds;
    }
}
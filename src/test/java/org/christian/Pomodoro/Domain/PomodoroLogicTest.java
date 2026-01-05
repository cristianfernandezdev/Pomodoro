package org.christian.Pomodoro.Domain;

import org.christian.Pomodoro.application.PomodoroServiceImpl;
import org.christian.Pomodoro.domain.PomodoroState;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PomodoroLogicTest {

    @Test
    @DisplayName(" El reloj debe iniciar con 25 minutos y pausado")
    void testInitialState() {
        PomodoroServiceImpl service = new PomodoroServiceImpl();
        PomodoroState state = service.getState();

        assertEquals(25, state.getMinutes(), "Los minutos iniciales deben ser 25");
        assertEquals(0, state.getSeconds(), "Los segundos iniciales deben ser 0");
        assertFalse(state.isRunning(), "El reloj debe arrancar pausado");
    }

    @Test
    @DisplayName("Cambiar el tiempo personalizado debe actualizar el estado")
    void testCustomTime() {
        PomodoroServiceImpl service = new PomodoroServiceImpl();


        service.setCustomTime(50);

        assertEquals(50, service.getState().getMinutes());
        assertEquals(0, service.getState().getSeconds());
        assertFalse(service.getState().isRunning(), "Al cambiar tiempo, debe seguir pausado");
    }

    @Test
    @DisplayName(" El Reset debe devolver el reloj a 25 minutos")
    void testReset() {
        PomodoroServiceImpl service = new PomodoroServiceImpl();


        service.setCustomTime(10);


        service.reset();

        assertEquals(25, service.getState().getMinutes(), "El reset debe volver a 25");
    }

    @Test
    @DisplayName(" Cálculo de la barra de progreso (Matemáticas)")
    void testProgressCalculation() {


        PomodoroState state = new PomodoroState(12, 30, true);

        double progress = state.getProgress();

        assertEquals(0.5, progress, 0.01, "El progreso debería ser del 50% (0.5)");
    }

    @Test
    @DisplayName(" Cálculo de barra vacía (0 segundos)")
    void testProgressZero() {
        PomodoroState state = new PomodoroState(0, 0, false);
        assertEquals(0.0, state.getProgress(), "Si el tiempo es 0, el progreso es 0");
    }
}
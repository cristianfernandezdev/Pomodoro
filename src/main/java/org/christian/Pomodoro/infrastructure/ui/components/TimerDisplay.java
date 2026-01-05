package org.christian.Pomodoro.infrastructure.ui.components;

import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Span;
import org.christian.Pomodoro.domain.PomodoroState;

public class TimerDisplay extends Div {

    private final Span timeText;
    private final Div outerRing;

    public TimerDisplay() {
        timeText = new Span("25:00");
        timeText.getStyle().set("font-size", "90px").set("font-weight", "bold")
                .set("font-family", "'Courier New', monospace").set("color", "#ffca28")
                .set("text-shadow", "0px 0px 15px black");

        Div innerCircle = new Div(timeText);
        innerCircle.setWidth("300px"); innerCircle.setHeight("300px");
        innerCircle.addClassName("panel-madera");
        innerCircle.getStyle().set("border-radius", "50%")
                .set("display", "flex").set("align-items", "center").set("justify-content", "center");

        outerRing = new Div(innerCircle);
        outerRing.setWidth("340px"); outerRing.setHeight("340px");
        outerRing.getStyle().set("border-radius", "50%")
                .set("display", "flex").set("align-items", "center").set("justify-content", "center");

        // CAMBIA LA LÍNEA DEL ERROR POR ESTA:
        update(new PomodoroState(25, 0, false));
        add(outerRing);
    }

    public void update(PomodoroState state) {
        timeText.setText(String.format("%02d:%02d", state.getMinutes(), state.getSeconds()));

        double degrees = state.getProgress() * 360;
        outerRing.getStyle().set("background",
            "conic-gradient(#ffca28 " + degrees + "deg, rgba(0,0,0,0.5) 0deg)");
    }
}

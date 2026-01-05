package org.christian.Pomodoro.infrastructure.ui.components;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.details.Details;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.IFrame;
import com.vaadin.flow.component.html.Input;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

public class MusicPlayer extends Div {

    private int volumenActual = 50; // Empezamos al 50
    private final Input sliderVolumen;
    private final IFrame iframe;

    public MusicPlayer() {
        // 1. URL Mágica: enablejsapi=1 es OBLIGATORIO para controlar el volumen
        // Usamos el video de Lofi Girl
        String urlMusic = "https://www.youtube.com/embed/jfKfPfyJRdk?enablejsapi=1&controls=0&disablekb=1&fs=0";

        iframe = new IFrame(urlMusic);
        iframe.setId("lofi-video"); // IMPORTANTE: Este ID lo usaremos en el Javascript
        iframe.setWidth("280px");
        iframe.setHeight("160px");
        iframe.getStyle().set("border-radius", "4px").set("border", "1px solid #8d6e63");

        // 2. Slider
        sliderVolumen = new Input();
        sliderVolumen.setType("range");
        sliderVolumen.getElement().setProperty("min", "0");
        sliderVolumen.getElement().setProperty("max", "100");
        sliderVolumen.setValue("50");
        sliderVolumen.setWidth("100%");
        sliderVolumen.getStyle().set("cursor", "pointer").set("accent-color", "#ffca28");

        // Al mover la barra -> Llamamos a cambiar volumen
        sliderVolumen.addValueChangeListener(e ->
                setVolume(Integer.parseInt(e.getValue()))
        );

        // 3. Botones (Mute, Bajar, Subir)
        HorizontalLayout controls = new HorizontalLayout(
                createBtn("🔇", -100), createBtn("🔉", -10), createBtn("🔊", 10)
        );
        controls.setJustifyContentMode(VerticalLayout.JustifyContentMode.CENTER);
        controls.setWidthFull();

        // 4. Estructura visual
        VerticalLayout content = new VerticalLayout(iframe, controls, sliderVolumen);
        content.setPadding(false); content.setSpacing(false);

        Span title = new Span("🎧 Lofi Chill");
        title.getStyle().set("color", "white").set("font-weight", "bold");

        Details details = new Details(title, content);
        details.setOpened(true);
        details.getStyle().set("cursor", "pointer");

        this.add(details);
        this.addClassName("panel-madera");
        this.getStyle().set("padding", "15px").set("border-radius", "10px");
        this.getStyle().set("position", "fixed").set("bottom", "20px").set("left", "20px");
    }

    private Button createBtn(String text, int delta) {
        Button b = new Button(text, e -> changeVolume(delta));
        b.addClassName("btn-madera");
        b.getStyle().set("padding", "5px 10px");
        return b;
    }

    private void changeVolume(int delta) {
        setVolume(volumenActual + delta);
    }

    // --- AQUÍ ESTÁ LA MAGIA PARA QUE FUNCIONE EN LOCALHOST ---
    private void setVolume(int vol) {
        // Limites 0 y 100
        if (vol > 100) vol = 100;
        if (vol < 0) vol = 0;
        this.volumenActual = vol;
        sliderVolumen.setValue(String.valueOf(vol));

        // Enviamos un mensaje JSON al Iframe de YouTube.
        // YouTube escucha estos mensajes y obedece.
        // 'func': 'setVolume' es el comando oficial de Google.
        String command = "{\"event\":\"command\",\"func\":\"setVolume\",\"args\":[" + vol + "]}";

        iframe.getElement().executeJs(
                "var frame = document.getElementById('lofi-video');" +
                        "if(frame) { frame.contentWindow.postMessage('" + command + "', '*'); }"
        );
    }
}
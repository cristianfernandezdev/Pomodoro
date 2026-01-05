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

    private int volumenActual = 100;
    private final Input sliderVolumen;
    private final IFrame iframe;

    public MusicPlayer() {
        String urlMusic = "https://www.youtube.com/embed/jfKfPfyJRdk?enablejsapi=1";
        iframe = new IFrame(urlMusic);
        iframe.setId("lofi-video");
        iframe.setWidth("280px");
        iframe.setHeight("160px");
        iframe.getStyle().set("border-radius", "4px").set("border", "1px solid #8d6e63");

        sliderVolumen = new Input();
        sliderVolumen.setType("range");
        sliderVolumen.getElement().setProperty("min", "0");
        sliderVolumen.getElement().setProperty("max", "100");
        sliderVolumen.setValue("100");
        sliderVolumen.setWidth("100%");
        sliderVolumen.getStyle().set("cursor", "pointer").set("accent-color", "#ffca28");

        sliderVolumen.addValueChangeListener(e ->
            setVolume(Integer.parseInt(e.getValue()))
        );

        HorizontalLayout controls = new HorizontalLayout(
            createBtn("🔇", -100), createBtn("🔉", -10), createBtn("🔊", 10)
        );
        controls.setJustifyContentMode(VerticalLayout.JustifyContentMode.CENTER);
        controls.setWidthFull();

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

    private void setVolume(int vol) {
        if (vol > 100) vol = 100;
        if (vol < 0) vol = 0;
        this.volumenActual = vol;
        sliderVolumen.setValue(String.valueOf(vol));
        iframe.getElement().executeJs(
            "this.contentWindow.postMessage('{\"event\":\"command\",\"func\":\"setVolume\",\"args\":[' + vol + ']}', '*')"
        );
    }
}

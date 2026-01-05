package org.christian.Pomodoro.infrastructure.ui;

import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.IntegerField;
import com.vaadin.flow.router.Route;
import org.christian.Pomodoro.domain.PomodoroListener;
import org.christian.Pomodoro.domain.PomodoroService;
import org.christian.Pomodoro.domain.PomodoroState;
import org.christian.Pomodoro.infrastructure.ui.components.MusicPlayer;
import org.christian.Pomodoro.infrastructure.ui.components.StylesUtil;
import org.christian.Pomodoro.infrastructure.ui.components.TimerDisplay;
import org.springframework.beans.factory.annotation.Autowired;
import com.vaadin.flow.component.html.IFrame;
import com.vaadin.flow.router.PageTitle;

@Route("")
@PageTitle("Pomodoro-Lofi")
public class MainView extends VerticalLayout implements PomodoroListener {

    private final PomodoroService pomodoroService;
    private final TimerDisplay timerDisplay;

    @Autowired
    public MainView(PomodoroService pomodoroService) {
        this.pomodoroService = pomodoroService;
        this.pomodoroService.setListener(this);

        setSizeFull();
        setAlignItems(Alignment.CENTER);
        setJustifyContentMode(JustifyContentMode.CENTER);
        injectStyles();
        setupBackgroundVideo();

        timerDisplay = new TimerDisplay();
        HorizontalLayout controls = createControls();
        HorizontalLayout config = createConfig();
        MusicPlayer musicPlayer = new MusicPlayer();

        add(timerDisplay, controls, config, musicPlayer);
    }

    @Override
    public void onTick(PomodoroState state) {
        getUI().ifPresent(ui -> ui.access(() -> {
            timerDisplay.update(state);
        }));
    }

    @Override
    public void onFinish() {
        getUI().ifPresent(ui -> ui.access(() -> {
            System.out.println("Pomodoro terminado");
        }));
    }

    private HorizontalLayout createControls() {
        Button btnStart = new Button("Iniciar", e -> pomodoroService.start());
        Button btnStop = new Button("Parar", e -> pomodoroService.stop());
        Button btnReset = new Button("Reiniciar", e -> pomodoroService.reset());

        btnStart.addClassName("btn-madera");
        btnStop.addClassName("btn-madera");
        btnReset.addClassName("btn-madera");

        btnStart.getStyle().set("color", "#ffca28").set("border-color", "#ffca28");
        btnStop.getStyle().set("color", "#ef5350").set("border-color", "#ef5350");
        btnReset.getStyle().set("color", "#9e9e9e").set("border-color", "#9e9e9e");

        HorizontalLayout layout = new HorizontalLayout(btnStart, btnStop, btnReset);
        layout.getStyle().set("margin-top", "40px");
        return layout;
    }

    private HorizontalLayout createConfig() {
        IntegerField input = new IntegerField();
        input.setValue(25);
        input.addClassName("input-madera");
        input.getStyle().set("background", "rgba(0,0,0,0.5)")
             .set("border", "1px solid #8d6e63").set("border-radius", "4px");

        Button btnSet = new Button("Modificar Tiempo", e -> {
            if (input.getValue() != null) pomodoroService.setCustomTime(input.getValue());
        });
        btnSet.addClassName("btn-madera");
        btnSet.addClickShortcut(Key.ENTER);

        HorizontalLayout layout = new HorizontalLayout(input, btnSet);
        layout.setAlignItems(Alignment.CENTER);
        layout.getStyle().set("margin-top", "25px");
        return layout;
    }

    private void injectStyles() {
        getElement().executeJs(
            "const style = document.createElement('style');" +
            "style.innerHTML = `" + StylesUtil.getGlobalCss() + "`;" +
            "document.head.appendChild(style);"
        );
    }

    private void setupBackgroundVideo() {
        String videoID = "AjECybntXv0";
        String urlFondo = "https://www.youtube.com/embed/" + videoID +
                "?autoplay=1&mute=1&controls=0&disablekb=1&fs=0&loop=1&playlist=" + videoID + "&start=10";
        IFrame fondoVideo = new IFrame(urlFondo);
        fondoVideo.setId("video-fondo");

        fondoVideo.getStyle().set("position", "fixed");
        fondoVideo.getStyle().set("top", "50%");
        fondoVideo.getStyle().set("left", "50%");

        fondoVideo.getStyle().set("min-width", "100%");
        fondoVideo.getStyle().set("min-height", "100%");
        fondoVideo.getStyle().set("width", "auto");
        fondoVideo.getStyle().set("height", "auto");

        fondoVideo.getStyle().set("z-index", "-1");
        fondoVideo.getStyle().set("transform", "translate(-50%, -50%)");
        fondoVideo.getStyle().set("pointer-events", "none");
        add(fondoVideo);
    }
}

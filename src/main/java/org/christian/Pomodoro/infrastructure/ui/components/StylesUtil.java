package org.christian.Pomodoro.infrastructure.ui.components;

public class StylesUtil {

    public static String getGlobalCss() {
        return
            ".panel-madera {" +
            "    background: rgba(20, 10, 5, 0.85);" +
            "    border: 1px solid #8d6e63;" +
            "    box-shadow: 0 10px 30px rgba(0,0,0,0.9);" +
            "    backdrop-filter: blur(3px);" +
            "}" +
            ".btn-madera {" +
            "    background: transparent;" +
            "    color: #ffca28;" +
            "    border: 1px solid #ffca28;" +
            "    cursor: pointer;" +
            "    font-weight: bold;" +
            "    padding: 10px 20px;" +
            "    border-radius: 4px;" +
            "    transition: all 0.2s;" +
            "    text-transform: uppercase;" +
            "    letter-spacing: 1px;" +
            "}" +
            ".btn-madera:hover {" +
            "    background: rgba(255, 202, 40, 0.1);" +
            "}" +
            ".input-madera input {" +
            "    color: white !important;" +
            "    text-align: center;" +
            "}";
    }
}

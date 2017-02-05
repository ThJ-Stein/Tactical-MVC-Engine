package implementation;

import engine.controller.Controller;
import engine.view.View;

/**
 * Created by thomas on 4-2-17.
 */
public class MyApp {
    private final Controller controller;
    private final View view;

    public MyApp() {
        this.controller = new MyController();
        this.view = new MyView("MyApp");

        controller.setView(view);
        view.connectController(controller);
    }

    public static void main(String[] args) {
        MyApp app = new MyApp();
    }
}

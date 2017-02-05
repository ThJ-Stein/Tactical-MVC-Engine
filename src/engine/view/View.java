package engine.view;

import engine.GameLoop;
import engine.command.InputCommand;
import engine.controller.Controller;

import javax.swing.*;
import java.awt.event.*;
import java.util.HashMap;

/**
 * The View class contains all the functionality that a window should have, while specifying nothing about what
 * gets displayed by it.
 * Created by thomas on 4-2-17.
 */
public abstract class View extends JFrame {

    /**
     * The amount of times the canvas will be repainted each second
     */
    public static final int FPS = 60;

    /**
     * The controller to which InputCommands will be sent
     */
    private Controller controller = null;

    /**
     * The canvas object that will show the game
     */
    private Canvas canvas = null;

    private GameLoop viewLoop;

    private HashMap<KeyStroke, InputCommand> keyMap = new HashMap<>();

    /**
     * The constructor that gets called by a subclass. The canvas object is not yet available at this point, and the
     * content pane is not yet set by the subclass either.
     * @param windowTitle the title of the window
     */
    protected View(String windowTitle) {
        super(windowTitle);
    }

    /**
     * Gets called at the end of the subclass constructor. When this method is called, the canvas and content pane are
     * set properly, so much of the initialization that would usually be done in the constructor, is instead handled by
     * this method.
     */
    protected void init() {
        assert canvas != null;

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setVisible(true);

        setFocusable(true);
        setupListeners();

        viewLoop = new GameLoop(60, this::run);
        viewLoop.start();
    }

    /**
     * Sets up all the relevant listeners for the window. Gets called by this::init.
     */
    private void setupListeners() {
        addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                requestFocus();
            }

            @Override
            public void mousePressed(MouseEvent mouseEvent) {

            }

            @Override
            public void mouseReleased(MouseEvent mouseEvent) {

            }

            @Override
            public void mouseEntered(MouseEvent mouseEvent) {

            }

            @Override
            public void mouseExited(MouseEvent mouseEvent) {

            }
        });

        addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent keyEvent) {

            }

            @Override
            public void keyPressed(KeyEvent keyEvent) {
                KeyStroke key = KeyStroke.getKeyStroke(keyEvent.getKeyChar());
                enqueueIfNecessary(key);
            }

            @Override
            public void keyReleased(KeyEvent keyEvent) {

            }
        });
    }

    /**
     * This method is passed to the GameLoop instance in this::init. It tells the canvas to repaint itself. When
     * GameLoop::start is called (which happens right after it is instantiated in this::init), the canvas starts
     * repainting itself a separate thread that belongs to the GameLoop at a constant amount of times per second,
     * which is specified in the constant View.FPS.
     */
    private void run() {
        assert canvas != null;
        canvas.repaint();
    }

    /**
     * Checks if the keystroke is in the keymap, and if so, enqueues the command which the key is mapped to. The
     * command is enqueued to the concurrent hash map in the controller, but only if the controller has been set.
     *
     * This method gets called by the KeyListener on the window whenever a key is pressed. The keystroke gets derived
     * from the key event.
     * @param key the keystroke that is derived from the key event
     */
    private void enqueueIfNecessary(KeyStroke key) {
        if (controller != null && keyMap.containsKey(key)) {
            controller.enqueueCommand(keyMap.get(key));
        }
    }

    /**
     * Puts a KeyStroke - InputCommand pair in the view's key map. This should be called by a subclass, but might also
     * ba called by a Controller when a user wishes to change key mappings.
     * @param key the character from which the keystroke is put into the map
     * @param command the InputCommand that gets sent to the controller when the keystroke is registered
     */
    public void mapKey(char key, InputCommand command) {
        keyMap.put(KeyStroke.getKeyStroke(key), command);
    }

    /**
     * Specifies the controller to which InputCommands should be sent. Should be called after initializing both
     * the View and the Controller.
     * @param controller the controller that will receive the InputCommands
     */
    public void setController(Controller controller) {
        this.controller = controller;
    }

    /**
     * Sets the canvas that is set to continuously repaint itself when GameLoop::start is called, as well as the
     * canvas that will receive CanvasPainters from the Controller. Gets called by the subclass in its constructor
     * after calling super(), but before calling init().
     * @param canvas the canvas to be updated continuously
     */
    protected void setCanvas(Canvas canvas) {
        assert canvas == null;

        this.canvas = canvas;
    }

    /**
     * Adds a CanvasPainter to the canvas. Every tick, painter::paint will be called by the canvas, so that the painter
     * draws an image on the canvas.
     * @param painter the painter to add to the list of painters whose paint methods will be called
     */
    public void addPainter(CanvasPainter painter) {
        canvas.addPainter(painter);
    }

    /**
     * Abstract method that can print debug output. Final products should ideally either do nothing, or print the
     * output to a log file.
     * @param message the string to print
     */
    public abstract void println(String message);
}

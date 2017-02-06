package engine.view;

import engine.GameLoop;
import engine.command.InputCommand;
import engine.controller.Controller;
import engine.view.gui.ViewFrame;

import javax.swing.*;
import java.awt.event.*;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;

/**
 * The View class contains all the functionality that a window should have, while specifying nothing about what
 * gets displayed by it.
 * Created by thomas on 4-2-17.
 */
public abstract class View {

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

    /**
     * The GameLoop object that continuously executes this:run in a dedicated thread after init() is called
     */
    private GameLoop viewLoop;

    /**
     * The hash map that maps KeyStrokes (from the KeyEvents resulting from key presses) to InputCommands that
     * get sent to the controller
     */
    private HashMap<KeyStroke, InputCommand> keyMap = new HashMap<>();

    /**
     * The window that carries the canvas.
     */
    private ViewFrame window = null;

    /**
     * Constructs a new View with a new instance of the ViewFrame subclass that is gets passed, whose window
     * has the windowTitle as a title. The window requests a reference to the canvas of the ViewFrame subclass and
     * sets up the right event listeners, then starts the game loop.
     * TODO improve javadoc
     * TODO justify why the parameter is a class instead of an instance / reconsider this choice
     * @param windowTitle the title of the window
     */
    protected View(Class<? extends ViewFrame> viewFrameClass, String windowTitle) {
        try {
            window = viewFrameClass.getDeclaredConstructor(String.class).newInstance(windowTitle);
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }

        canvas = window.getCanvas();

        //pack is here instead of in viewframe because pack needs to happen after setcontentpane
        window.pack();

        setupListeners(window);

        viewLoop = new GameLoop(60, this::run);
        viewLoop.start();
    }

    /**
     * Sets up all the relevant listeners for the window. Gets called by this::init.
     * Note to self: this method cannot be in ViewFrame because the KeyListener references the method
     * enqueueIfNecessary
     */
    private void setupListeners(JFrame window) {
        window.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                window.requestFocus();
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

        window.addKeyListener(new KeyListener() {
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
     * Puts a KeyStroke - InputCommand pair in the window's key map. This should be called by a subclass, but might also
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
        assert this.canvas == null;

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
     * Tells the window frame to print a string into its debug log.
     * @param debugOutput the string to print
     */
    public void println(String debugOutput) {
        window.println(debugOutput);
    }
}

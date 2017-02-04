package engine.view;

import engine.command.InputCommand;
import engine.controller.Controller;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.HashMap;

/**
 * Created by thomas on 4-2-17.
 */
public class View extends JFrame {
    protected Controller controller;

    protected Canvas canvas;

    HashMap<KeyStroke, InputCommand> keyMap;

    {
        keyMap = new HashMap<>();
    }

    public View(String s) {
        super(s);
    }

    public void init() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setVisible(true);

        setFocusable(true);
        setupListeners();
    }

    public void mapKey(char key, InputCommand command) {
        keyMap.put(KeyStroke.getKeyStroke(key), command);
    }

    public void setupListeners() {
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
                if (controller != null && keyMap.containsKey(key)) {
                    controller.enqueueCommand(keyMap.get(key));
                }
            }

            @Override
            public void keyReleased(KeyEvent keyEvent) {

            }
        });
    }

    public void connectController(Controller controller) {
        this.controller = controller;
    }
}

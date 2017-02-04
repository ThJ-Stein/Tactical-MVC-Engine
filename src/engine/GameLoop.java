package engine;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by thomas on 4-2-17.
 */
public class GameLoop {
    private int fps;
    private Timer timer = new Timer();
    private TimerTask timerTask;
    private Runnable task;

    {
        timerTask = new TimerTask() {
            @Override
            public void run() {
                doTask();
            }
        };
    }

    public GameLoop(int fps, Runnable task) {
        this.fps = fps;
        this.task = task;
    }

    public void start() {
        timer.scheduleAtFixedRate(timerTask, 0, 1000/fps);
    }

    public void stop() {
        timer.cancel();
    }

    private void doTask() {
        task.run();
    }
}

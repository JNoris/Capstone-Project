package utility;

import javafx.application.Platform;
import manager.ControllerManager;

/**
 * Timer singleton class is used for security in case the application does not
 * receive any input for a certain period of time.
 *
 * @author Vinicius Smith
 */
public class Timer implements Runnable {

    private static Timer timer = null;
    private double timeout = 0.0;
    private double currTimeout = 0.0;
    private boolean stop = true;

    /**
     * Default private constructor.
     *
     * @param timeout timeout time.
     */
    private Timer(double timeout) {
        this.timeout = timeout;
    }

    /**
     * Gets a reference to the Timer singleton.
     *
     * @return the timer object.
     */
    public static Timer getInstance() {
        return timer;
    }

    /**
     * Creates a new timer object with timeout time limit.
     *
     * @param timeout timeout time.
     */
    public static void createTimer(double timeout) {
        if (timer == null) {
            timer = new Timer(timeout);
        } else {
            timer.cancelTimer();
            timer = new Timer(timeout);
        }
    }

    /**
     * Reset the timer so it starts counting from zero.
     */
    public void resetTimer() {
        this.currTimeout = 0;
    }

    /**
     * Set timeout value.
     *
     * @param timeout new timeout value.
     */
    public void setTimeout(double timeout) {
        this.timeout = timeout;
        this.resetTimer();
    }

    /**
     * Stops the timer from continuing and executing code when the timer
     * expires.
     */
    public void cancelTimer() {
        this.stop = true;
        System.out.println("Timer canceled.");
    }

    /**
     * Checks if there is an current active timer or not.
     *
     * @return Whether a timer exists.
     */
    public static boolean exist() {
        if (timer == null) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * @see Runnable#run()
     */
    @Override
    public void run() {

        double startTime = System.currentTimeMillis();

        //Loops until the currTimeout is larger than the set timeout value or the stop variable is set to true.
        while (currTimeout <= timeout && !stop) {
            currTimeout = System.currentTimeMillis() - startTime;
        }
        System.out.println("Timer ended");
        timer = null;

        if (!stop) {
            //Sends the application back to the login screen.
            Platform.runLater(() -> {
                ControllerManager.getInstance().changeScene(ControllerManager.getInstance().getLoginScreen());
                ControllerManager.getInstance().hidePopup();
            });
        }
    }
}

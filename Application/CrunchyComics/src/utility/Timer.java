/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utility;

import javafx.application.Platform;
import manager.ControllerManager;

/**
 *
 * @author Vinicius Smith
 */
public class Timer implements Runnable {

    private static Timer timer = null;
    private double timeout = 0.0;
    private double currTimeout = 0.0;
    private boolean stop = false;

    private Timer(double timeout) {
        this.timeout = timeout;
    }

    public static Timer getInstance() {
        return timer;
    }

    public static void createTimer(double timeout) {
        if (timer == null) {
            timer = new Timer(timeout);
        } else {
            timer.cancelTimer();
            timer = new Timer(timeout);
        }
    }

    public void resetTimer() {
        this.currTimeout = 0;
    }

    public void setTimeout(double timeout) {
        this.timeout = timeout;
        this.resetTimer();
    }

    public void cancelTimer() {
        this.stop = true;
        System.out.println("Timer canceled.");
    }

    public static boolean exist() {
        if (timer == null) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    public void run() {
        
        double startTime = System.currentTimeMillis();
        while (currTimeout <= timeout && !stop) {
            currTimeout = System.currentTimeMillis() - startTime;
        }
        System.out.println("Timer ended");
        timer = null;
        if (!stop) {
            Platform.runLater(() -> {
                ControllerManager.getInstance().changeScene(ControllerManager.getInstance().getLoginScreen());
                ControllerManager.getInstance().hidePopup();
            });
        }
    }
}

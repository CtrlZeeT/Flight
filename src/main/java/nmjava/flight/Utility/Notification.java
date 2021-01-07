/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nmjava.flight.Utility;

import java.awt.Color;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JLabel;

/**
 *
 * @author xuanluan
 */
public class Notification {

    public static void show(JLabel label, String content, boolean isSuccess) {
        label.setText(content);
        if (isSuccess) {
            label.setForeground(new Color(8, 186, 29));
        } else {
            label.setForeground(Color.RED);
        }
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                label.setText("");
            }
        };
        long delay = 3000L;
        Timer timer = new Timer("Timer");
        timer.schedule(timerTask, delay);
    }
}

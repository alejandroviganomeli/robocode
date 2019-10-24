import robocode.Robot;
import robocode.ScannedRobotEvent;

import java.awt.*;

public class VFex extends Robot {

    public VFex() {
        this.setColors(Color.BLACK, Color.BLACK, Color.BLACK);
    }

    public void run() {
        while (true) {
            ahead(100);
            turnGunRight(360);
            back(100);
            turnGunRight(360);
        }
    }

    public void onScannedRobot(ScannedRobotEvent e) {
        fire(1);
    }

}

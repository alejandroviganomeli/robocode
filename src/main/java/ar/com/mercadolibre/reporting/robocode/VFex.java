package ar.com.mercadolibre.reporting.robocode;

import robocode.Robot;
import robocode.ScannedRobotEvent;

public class VFex extends Robot {

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

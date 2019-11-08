import robocode.*;

import java.util.Random;

public class Vader extends Robot {

    int status = 0;

    public Vader() {

    }

    public void run() {
        while (true) {

            switch(status) {

                case 1:

                    break;

                default :
                    ahead(getRandomMove());
                    turnRight(getRandomAngle());
                    break;
            }
            turnGunRight(360);
        }
    }

    public double getRandomMove() {
        return Math.random() * 100 + 100;
    }

    public double getRandomAngle() {
        return Math.random() * 360;
    }

    public void onScannedRobot(ScannedRobotEvent e) {

        status = 1;

        if(getEnergy() > 30) {
            fire( 10);
            turnGunLeft(5);
            fire(10);
            turnGunRight(5);
            fire(10);
            System.out.println("Distance: " + e.getDistance());
        }


    }

    public void onBulletMissed(BulletMissedEvent event) {
        status = 0;
    }

    public void onHitWall(HitWallEvent event) {

        //turnRight(30);
        //ahead(100);

        if (event.getBearing() > -90 && event.getBearing() <= 90) {
            back(100);
        } else {
            ahead(100);
        }

    }

    public void onHitByBullet(HitByBulletEvent event) {
        turnRight(30);
        ahead(100);

    }

    public void onHitRobot(HitRobotEvent event) {
        if (event.getBearing() > -90 && event.getBearing() <= 90) {
            back(100);
        } else {
            ahead(100);
        }
    }

}

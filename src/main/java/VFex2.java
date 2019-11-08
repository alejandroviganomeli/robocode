import robocode.AdvancedRobot;
import robocode.HitByBulletEvent;
import robocode.HitWallEvent;
import robocode.ScannedRobotEvent;

public class VFex2 extends AdvancedRobot {

    private static final int STATE_SEARCH_SEARCH = 0;
    private static final int STATE_SEARCH_FOCUS = 1;

    private int state;
    private ScannedRobotEvent lastTargetEvent;
    private double targetAngle;

    public VFex2() {
        state = STATE_SEARCH_SEARCH;
    }

    public void run() {
        //this.turnGunLeft(-this.getGunHeading());
        //this.turnLeft(-this.getHeading());
        do {
            switch (this.state) {
                case STATE_SEARCH_SEARCH:
                    turnRadarRight(360);
                case STATE_SEARCH_FOCUS:
                    this.state = STATE_SEARCH_SEARCH;
                    //moveTank();
            }
        } while (true);
    }

    public void onScannedRobot(ScannedRobotEvent e) {
        state = STATE_SEARCH_FOCUS;
        lastTargetEvent = e;
        lockTarget();
        if (getEnergy() > 20) {
            fire(20);
        } else {
            fire(getEnergy());
        }
        System.out.println("bearing: " + lastTargetEvent.getBearing());
        System.out.println("heading: " + getHeading());
        System.out.println("target angle: " + targetAngle);
    }

    @Override
    public void onHitByBullet(HitByBulletEvent event) {
        moveTank();
        this.ahead(100);

    }

    @Override
    public void onHitWall(HitWallEvent event) {
        this.ahead(-50);
    }

    public void lockTarget() {
        targetAngle = (this.getHeading() + lastTargetEvent.getBearing());
        double gunHeading = this.getGunHeading();
        if (targetAngle < 0) {
            setTurnGunRight(360 + targetAngle - gunHeading);
        } else if (targetAngle > 360) {
            setTurnGunRight(targetAngle - 360 - gunHeading);
        } else {
            setTurnGunRight(targetAngle - gunHeading);
        }

    }

    public void moveTank() {
        targetAngle = (this.getHeading() + lastTargetEvent.getBearing());
        double gunHeading = 90;
        if (targetAngle < 0) {
            turnLeft(360 + targetAngle - gunHeading);
        } else if (targetAngle > 360) {
            turnLeft(targetAngle - 360 - gunHeading);
        } else {
            turnLeft(targetAngle - gunHeading);
        }


    }

    public void onBulletMissed() {
        //state = STATE_SEARCH_SEARCH;
    }
}

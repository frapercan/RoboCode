package fnl;

import java.awt.Color;
import java.awt.geom.Rectangle2D;

import robocode.*;
import robocode.util.Utils;

public class MocardoBot2 extends AdvancedRobot {

	static double bulletVelocity;
	static double direction = 1;
	static double lastEnemyEnergy;
	static int mov = 1;
	static int type = 1;
	static int muertes = 0;


	public void run() {

		setBodyColor(Color.ORANGE);
		setGunColor(Color.PINK);
		setRadarColor(Color.RED);
		setBulletColor(Color.CYAN);
		setScanColor(Color.BLUE);
		setAdjustGunForRobotTurn(true);
		setAdjustRadarForGunTurn(true); //

		do {
			turnRadarRightRadians(Double.POSITIVE_INFINITY);

		} while (true);
	}

	public void onScannedRobot(ScannedRobotEvent e) {
		// movement

		double absbearing = e.getBearingRadians() + getHeadingRadians();
		double eDistance = e.getDistance();
		double eOffset = (Math.PI / 2) - eDistance * 0.001;
		double moveAngle = absbearing + direction * (eOffset -= .02);
		while (!new Rectangle2D.Double(18, 18, getBattleFieldWidth() - 50, getBattleFieldHeight() - 50)
				.contains(getX() + 160 * Math.sin(moveAngle), getY() + 160 * Math.cos(moveAngle))) {
			moveAngle = absbearing + direction * (eOffset = eOffset - .02);

		}

		if (eOffset < Math.PI / 4) {
			direction = -direction;
		}
		setTurnRightRadians(Math.tan(moveAngle -= getHeadingRadians()));

		if (getTime() % 25 == 0) {
			mov *= -1;
			setAhead(999 * mov);
		}
		// radar
		setTurnRadarLeft(getRadarTurnRemaining());
		// targeting
		int bPower = (int) (getEnergy() > 5 ? 3 : Math.min(2, getEnergy() / 2));
		int bVelocity = 20 - (bPower * 3);

		if (type == -1) {

			double RandomOffset = Math.random() / 10;

			setTurnGunRightRadians(Utils.normalRelativeAngle(
					getHeadingRadians() + e.getBearingRadians() - getGunHeadingRadians() + RandomOffset));

			setFire(bPower);

		}
		if (type == 1) {
			setTurnGunRightRadians(Utils.normalRelativeAngle(absbearing - getGunHeadingRadians()
					+ (e.getVelocity() * Math.sin(e.getHeadingRadians() - absbearing) / bVelocity)));

			setFire(bPower);
		}
		System.out.println(type);

	}

	public void onHitByBullet(HitByBulletEvent e) {
		direction = -direction;
	}

	

	public void onDeath(DeathEvent e) {
		muertes = muertes +1;
		if(getRoundNum()/muertes > 0.501)
		type = type * -1;
		
		
		

	}

	public void onWin(WinEvent e) {
		setAhead(0);
		setTurnRightRadians(Double.NEGATIVE_INFINITY);

	}

}

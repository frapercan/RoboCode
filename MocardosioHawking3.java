package fnl;


import robocode.*;
import robocode.util.Utils;

import java.awt.*;
import java.awt.geom.Point2D;

public class MocardosioHawking3 extends AdvancedRobot {
	boolean Mov; //Variable que indica si vamos hacia adelante o hacia atras
	Double pi = Math.PI;
	public void run() {
		
		setBodyColor(Color.BLUE);
		setGunColor(Color.BLUE);
		setRadarColor(Color.BLUE);
		setBulletColor(Color.BLUE);
		setScanColor(Color.BLUE);
		setAdjustGunForRobotTurn(true);
		setAdjustRadarForGunTurn(true); // Cañon independiente del giro
		
		do {
	        turnRadarRightRadians(Double.POSITIVE_INFINITY);
	    } while (true);
	}


//Cuando un enemigo está dentro del radar:
	public void onScannedRobot(ScannedRobotEvent e) {
		double absBearing=e.getHeadingRadians();//enemies absolute bearing
		double gunTurnAmt;//amount to turn our gun
		double distance = e.getDistance();
		System.out.println(distance);
	    setTurnRadarLeftRadians(getRadarTurnRemainingRadians());
	    if (distance < 150){
	    	fire(16);
	    }
	    if (distance > 150 && distance < 300){
	    	fire(4);
	    }
	    
	    if (distance > 300 && distance < 500){
	    	fire(1);
	    }
	    if (distance > 500 && distance < 700){
	    	fire(0.5);
	    }
	    
		
	
	}	
	
	// Tras chocar con la pared, cambio de sentido!
	public void onHitWall(HitWallEvent e) {
		invertir();
	}

	//Cambiar sentido del movimiento
	public void invertir() {
		if (Mov) {
			setBack(40000);
			Mov = false;
		} else {
			setAhead(40000);
			Mov = true;
		}
	}


	
}

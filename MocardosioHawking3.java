package fnl;


import robocode.*;
import robocode.util.Utils;

import java.awt.*;
import java.awt.geom.Point2D;

public class MocardosioHawking3 extends AdvancedRobot {
	double Mov = 1; //Variable que indica si vamos hacia adelante o hacia atras
	double pi = Math.PI;
	double randomness = Math.random();
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
		double RandomEnemyHeading = (e.getHeadingRadians() * Math.random())/10;
		double distance = e.getDistance();
	
		System.out.println("velocidad :" + e.getVelocity());
		
		
		if(distance < 150 && Math.abs(e.getVelocity()) > 4){
			RandomEnemyHeading = RandomEnemyHeading/2;
			
		}
		if(distance < 150 && Math.abs(e.getVelocity()) < 4){
			RandomEnemyHeading = RandomEnemyHeading/10;
			
		}
			
		
		//movimiento del cañon  hacia la derecha siguiendo al tanque enemigo con un pequeño offset random
		setTurnGunRightRadians(Utils.normalRelativeAngle(getHeadingRadians() + e.getBearingRadians() - 
			    getGunHeadingRadians()+RandomEnemyHeading));
		
		System.out.println(RandomEnemyHeading);
	    setTurnRadarLeftRadians(getRadarTurnRemainingRadians());
	    if (distance < 150){
	    	setFire(16);
	    }
	    if (distance > 150 && distance < 300){
	    	setFire(8);
	    }
	    
	    if (distance > 300 && distance < 500){
	    	setFire(5);
	    }
	    if (distance > 500 ){
	    	setFire(3);
	    }
	    
	    //movimiento en sentido oscilatorio cada 25 turnos, girando siempre en torno al enemigo + un angulo aleatorio
	    setTurnRightRadians(e.getBearingRadians()+(pi/2) );
		if (getTime() % 25 == 0) {
			Mov *= -1;
			randomness = Math.random();
			setAhead(999 * Mov);
		}
		
	}
	    
	
	}    
	    
	
	
	

	


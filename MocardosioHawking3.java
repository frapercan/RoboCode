package fnl;


import robocode.*;
import robocode.util.Utils;

import java.awt.*;

public class MocardosioHawking3 extends AdvancedRobot {
	double Mov = 1; //Variable que indica si vamos hacia adelante o hacia atras
	double pi = Math.PI;
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


//Cuando un enemigo está dentro del radar:
	public void onScannedRobot(ScannedRobotEvent e) {
		double RandomOffset =  Math.random()/10;
		double distance = e.getDistance();
		setTurnRadarLeftRadians(getRadarTurnRemainingRadians());
	
		
		if(distance < 150 && Math.abs(e.getVelocity()) > 4){
			RandomOffset = RandomOffset/2;
			
		}
		if(distance < 150 && Math.abs(e.getVelocity()) < 4){
			RandomOffset = RandomOffset/5;
			
		}
		
		
			
		setTurnGunRightRadians(Utils.normalRelativeAngle(getHeadingRadians() + e.getBearingRadians() - 
			    getGunHeadingRadians()+RandomOffset));
		
		
		
		
	    
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
			setAhead(999 * Mov);
		}
		
	}
	    
	public void onWin(WinEvent e) {
		setAhead(0);
		setTurnRightRadians(Double.NEGATIVE_INFINITY);	
		
		
	}
	}    
	    
	
	


	


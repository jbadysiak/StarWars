package Gra;

import java.util.LinkedList;
import classes.EntityA;
import classes.EntityB;
/** Klasa Physisc odpowiada za sprawdzenie wyst�pienia kolizji */
public class Physics {
	
	/**Sprawdzenie kolizji pomi�dzy EntityA oraz EntityB */
	public static boolean Collision(EntityA enta, EntityB entb){
		if(enta.getBounds().intersects(entb.getBounds())){
			return true;
		}
		return false;
	}
	
	/**Sprawdzenie kolizji pomi�dzy EntityB oraz EntityA */
	public static boolean Collision(EntityB entb, EntityA enta){
		if(entb.getBounds().intersects(enta.getBounds())){
			return true;
		}
		return false;
	}
}

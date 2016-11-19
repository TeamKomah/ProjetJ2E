package model;

import java.awt.Color;

import static model.IConfig.COULEUR_EAU;
import static model.IConfig.COULEUR_FORET;
import static model.IConfig.COULEUR_ROCHER;

public class Obstacle extends Element {
	public enum TypeObstacle {
		ROCHER (COULEUR_ROCHER), FORET (COULEUR_FORET), EAU (COULEUR_EAU);

		private final Color COULEUR;
		TypeObstacle(Color couleur) { COULEUR = couleur; }
		public static TypeObstacle getObstacleAlea() {
			return values()[(int)(Math.random()*values().length)];
		}
	}
	private TypeObstacle TYPE;
	Obstacle(TypeObstacle type, Position pos) { TYPE = type; this.pos = pos; }
	public String toString() { return ""+TYPE; }
}
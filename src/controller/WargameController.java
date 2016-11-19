package controller;

import model.Partie;

public class WargameController {
	private Partie modelPartie;
	public WargameController(Partie p){
		this.modelPartie = p;
	}
	public void control(){
		if(this.modelPartie.getJoueurTourCourant()==this.modelPartie.getJoueurReel()){
			this.modelPartie.notificationFinDeTour();
		}
	}
}

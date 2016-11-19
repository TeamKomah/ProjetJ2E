package Model;

import java.util.ArrayList;
import java.util.Collection;

public class Partie {
	
	private AbstractJoueur joueurTourCourant;
	private JoueurReel joueurReel;
	private JoueurIA joueurIA;
	private boolean partieEnCours = false;

	public Partie(){
		initPartie();
		while(this.partieEnCours){
			
		}
	}

	private void initPartie() {
		this.joueurReel = new JoueurReel(this);
		this.joueurIA = new JoueurIA(this);
		this.partieEnCours = true;
		
		//TODO A definir aleatoirement
		this.joueurTourCourant = this.joueurReel;
		
	}
	/**
	 * notification appelée par un des joueurs pour signaler la fin de son tour
	 */
	public void notificationFinDeTour(){
		if(this.joueurTourCourant == joueurReel)
			this.joueurTourCourant = joueurIA;
		else
			this.joueurTourCourant = joueurReel;
	}
	
	/**
	 * notification appelée par un des joueurs pour signaler qu'il est mort
	 */
	public void notificationFinDeJeu(){
		this.partieEnCours = false;
	}
}


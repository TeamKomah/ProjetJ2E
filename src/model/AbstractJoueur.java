package model;

public abstract class AbstractJoueur {
	
	protected Partie partie;
	public AbstractJoueur(Partie p)
	{
		this.partie = p;
	}
	
	
	/**
	 * Appelée à chaque tour d'un joueur
	 * TODO le joueur doit appeler notifierFinDeTour quand il finit son tour
	 */
	public abstract void jouerTour();
	public abstract void ajouterPartie(Partie p);
	
	
	public void notifierFinDeTour(){
		this.partie.notificationFinDeTour();
	}
}

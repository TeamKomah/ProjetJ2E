package model;

public class JoueurReel extends AbstractJoueur{

	public JoueurReel(Partie p) {
		super(p);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void jouerTour() {
		System.out.println("A vous de jouer");
		while(this.partie.getJoueurTourCourant()==this){
			try {
			       Thread.sleep(1);
			    } catch(InterruptedException e) {
			    }
		}		
	}

	@Override
	public void ajouterPartie(Partie p) {
		// TODO Auto-generated method stub
		
	}
	
	

}

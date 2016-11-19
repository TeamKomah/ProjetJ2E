package view;

import controller.WargameController;
import model.Partie;


public class Launcher {
    public static void main(String args[]){
        System.out.println("started");
        
        //Partie utilisée comme Model
        Partie partie = new Partie();
        //WargameController utilisée comme controller
        WargameController controller = new WargameController(partie);
        //FenetreJeu utilisée comme View
        FenetreJeu fen = new FenetreJeu(controller);
        partie.addObserver(fen);
        partie.lancerPartie();
        
    }
}

package controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import model.Document;
import model.Utilisateur;

/**
 * Servlet implementation class DocumentS
 */
//@WebServlet("/DocumentS")
public class DocumentS extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static final int TAILLE_TAMPON = 10240;
	private static final int DEFAULT_BUFFER_SIZE = 10240;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DocumentS() {
        super();
        // TODO Auto-generated constructor stub
    }

    private static String getNomFichier( Part part ) {

        /* Boucle sur chacun des paramètres de l'en-tête "content-disposition". */
        for ( String contentDisposition : part.getHeader( "content-disposition" ).split( ";" ) ) {
            /* Recherche de l'éventuelle présence du paramètre "filename". */
            if ( contentDisposition.trim().startsWith("filename") ) {
                /* Si "filename" est présent, alors renvoi de sa valeur, c'est-à-dire du nom de fichier. */
                return contentDisposition.substring( contentDisposition.indexOf( '=' ) + 1 ).trim().replace( "\"", "" );
            }
        }
        /* Et pour terminer, si rien n'a été trouvé... */
        return null;
    }
    
    
    private void ecrireFichier( Part part, String nomFichier, String chemin ) throws IOException {

        BufferedInputStream entree = null;
        BufferedOutputStream sortie = null;
        try {
            
            entree = new BufferedInputStream( part.getInputStream(), TAILLE_TAMPON );
            sortie = new BufferedOutputStream( new FileOutputStream( new File( chemin + nomFichier ) ),  TAILLE_TAMPON );
            byte[] tampon = new byte[TAILLE_TAMPON];
            int longueur;
            while ( ( longueur = entree.read( tampon ) ) > 0 ) {
                sortie.write( tampon, 0, longueur );
            }
        } finally {
            try {
                sortie.close();
            } catch ( IOException ignore ) {

            }
            try {
                entree.close();
            } catch ( IOException ignore ) {
            }
        }
    }
    
    public void chargerFichierDir(String chemin, String nomfichier){
    	File fichier = new File(chemin,nomfichier);
    	
    }
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			Document doc1 = new Document();
			Utilisateur user = new Utilisateur();
				/**
				 * modification de document
				 */
				if(request.getParameter("iddoc")!=null && request.getParameter("modif")!=null){
					
						
					
					HttpSession session= request.getSession();
					user.setId((int)session.getAttribute("id"));
					request.setAttribute("amis", user.listAmis());
					session.setAttribute("docModif", request.getParameter("iddoc"));
					
				}
				/**
				 * suppression du document
				 */
				if(request.getParameter("iddoc")!=null && request.getParameter("suppri")!=null){
				
					doc1.supprimer(Integer.parseInt(request.getParameter("iddoc")));
					
					HttpSession session= request.getSession();
					user.setId((int)session.getAttribute("id"));
					request.setAttribute("amis", user.listAmis());
					String S = "Le fichier a été supprimé";
					request.setAttribute("suppression", S);
					System.out.println("okkkkkkkkkkk");
					this.getServletContext().getRequestDispatcher("/WEB-INF/modification.jsp").forward(request, response);
					System.out.println("okkkkkkkkkkk");
				}
				
				
				

				BufferedReader fluxEntree;
				if(request.getParameter("iddoc") != null){
					String chemin = this.getServletConfig().getInitParameter("chemin");
					 int idd = Integer.parseInt(request.getParameter("iddoc"));
					 doc1.setId(idd);
					 String nomdoc;
					
					 nomdoc = doc1.recupererDoc();
					
					 fluxEntree = new BufferedReader(new FileReader(chemin+nomdoc));
					 /* Lecture d'une ligne */
					 String ligneLue = fluxEntree.readLine();
					 ArrayList<String> listContent= new ArrayList<>();
					while(ligneLue!=null){
						System.out.println(ligneLue);
						listContent.add(ligneLue);
						ligneLue = fluxEntree.readLine();
					}
					request.setAttribute("ContentFichier", listContent);
				}
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		this.getServletContext().getRequestDispatcher("/WEB-INF/utilisateur.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
			HttpSession session = request.getSession();
			int id = (int) session.getAttribute("id");
			String nom = (String) session.getAttribute("nom");
			String prenom = (String) session.getAttribute("prenom");
			String chemin = this.getServletConfig().getInitParameter("chemin");
			
			Document doc1;
			Utilisateur user;
			try {
				doc1 = new Document();
				user = new Utilisateur();
			
			//	if(request.getParameter( "fichier" )!=null){
					System.out.println("fichier detecte");
					Part part = request.getPart( "fichier" );
					String nomFichier = getNomFichier( part );
					
					if ( nomFichier != null && !nomFichier.isEmpty() ) {
					        String nomChamp = part.getName();
					        nomFichier = nomFichier.substring( nomFichier.lastIndexOf( '/' ) + 1 ).substring( nomFichier.lastIndexOf( '\\' ) + 1 );
					        ecrireFichier( part, nomFichier, chemin );
					     
								user.setId(id);
								user.setNom(nom);
								user.setPrenom(prenom);
								 Document doc = new Document(doc1.nbDoc, nomFichier,user,0,new Date(0000));
								 doc.ajouterDoc();
								 
						        request.setAttribute( nomChamp, nomFichier );
						        //System.out.println(nomFichier);
					    }
				//}
				// enregistrement d'une version du document
				
				 
			 
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			System.out.println("passage");
			 response.sendRedirect("Connection");
		
	}

}

package controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;

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
		
			
		HttpSession session = request.getSession();
		int id = (int) session.getAttribute("id");
		String nom = (String) session.getAttribute("nom");
		String prenom = (String) session.getAttribute("prenom");
		System.out.println("passage if"+request.getParameter("iddoc"));
		Document doc1;
		
		
		
		BufferedInputStream entree =null;
		BufferedOutputStream sortie = null;
		try {
			doc1 = new Document();
			
			String chemin = this.getServletConfig().getInitParameter("chemin");
			
			if(request.getParameter("iddoc") != null){
				 int idd = Integer.parseInt(request.getParameter("iddoc"));
				 doc1.setId(idd);
				 String nomdoc = doc1.recupererDoc();
				 System.out.println(nomdoc);
				 File fichierdoc = new File(chemin,nomdoc);
				 
				 response.reset();
					response.setBufferSize( DEFAULT_BUFFER_SIZE );
					//response.setContentType( type );
					response.setHeader( "Content-Length", String.valueOf( fichierdoc.length() ) );
					response.setHeader( "Content-Disposition", "attachment; filename=\"" + fichierdoc.getName() + "\"" );
				 
				 entree = new BufferedInputStream(new FileInputStream(fichierdoc),TAILLE_TAMPON);
				 sortie = new BufferedOutputStream(response.getOutputStream(),TAILLE_TAMPON);
				 byte[] tampon1 = new byte[TAILLE_TAMPON];
				 int longueur;
				 while((longueur= entree.read(tampon1))> 0){
					 sortie.write(tampon1, 0, longueur);
				 }
			 }
		 
		} catch (ClassNotFoundException e1) {
			sortie.close();
			e1.printStackTrace();
		} catch (SQLException e1) {
			entree.close();
			e1.printStackTrace();
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
			
			Document doc1;
			try {
				doc1 = new Document();
			
			
				String chemin = this.getServletConfig().getInitParameter("chemin");
				Part part = request.getPart( "fichier" );
				String nomFichier = getNomFichier( part );
				
				if ( nomFichier != null && !nomFichier.isEmpty() ) {
				        String nomChamp = part.getName();
				        nomFichier = nomFichier.substring( nomFichier.lastIndexOf( '/' ) + 1 ).substring( nomFichier.lastIndexOf( '\\' ) + 1 );
				        ecrireFichier( part, nomFichier, chemin );
				     
						
							Utilisateur user = new Utilisateur(id,nom,prenom,null,null,null,null);
							 
							 Document doc = new Document(doc1.nbDoc, nomFichier,user,0);
							 doc.ajouterDoc();
					        request.setAttribute( nomChamp, nomFichier );
					        System.out.println(nomFichier);
				    }
				 
			 
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

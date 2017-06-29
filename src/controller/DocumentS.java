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
		
		response.setContentType("text/plain");
		String chemin = this.getServletConfig().getInitParameter("chemin");
		try {
			HttpSession session= request.getSession();
			Document doc1 = new Document();
			Utilisateur user = new Utilisateur();
			if(request.getParameter("iddoc") != null ){
				 int idd = Integer.parseInt(request.getParameter("iddoc"));
				 doc1.setId(idd);
				
				if((String)request.getParameter("iddocVersio")!= "0" && request.getParameter("iddocVersio")!=null){
					System.out.println("idddd "+ request.getParameter("iddocVersio"));
					doc1.recupererDocVersio();
					request.setAttribute("iddocVersion", "1");
				}
				else{
					doc1.recupererDoc();
				}
			}
				
			
			/**
			 * suppression du document
			 */
			if(request.getParameter("iddoc")!=null && request.getParameter("suppri")!=null){
				if(request.getParameter("iddocVersio") != null){
					doc1.supprimerVersion(Integer.parseInt(request.getParameter("iddoc")));
				}
					
				else 
					doc1.supprimer(Integer.parseInt(request.getParameter("iddoc")));
				
				
				File fic = new File("C:"+chemin+doc1.getNom());
				if(fic.exists()){
					System.out.println("le fichier existe");
					System.out.println(fic.getName());
				}
				
				if(fic.delete()){
					System.out.println("suppression ok");
				}
				user.setId((int)session.getAttribute("id"));
				request.setAttribute("amis", user.listAmis());
				String S = "Le fichier a été supprimé";
				request.setAttribute("suppression", S);
				
				
			}
			
				
				/**
				 * recuperation du nom du fichier dans bd et son contenu.
				 */
				
				BufferedReader fluxEntree;
				if(request.getParameter("iddoc") != null ){
					user.setId((int)session.getAttribute("id"));
					request.setAttribute("amis", user.listAmis());
					request.setAttribute("nonLu", user.getMessageNonLu());
					 
					 fluxEntree = new BufferedReader(new FileReader(chemin+doc1.getNom()));
					 /* Lecture d'une ligne */
					 String ligneLue = fluxEntree.readLine();
					 ArrayList<String> listContent= new ArrayList<>();
					while(ligneLue!=null){
						
						listContent.add(ligneLue);
						ligneLue = fluxEntree.readLine();
					}
					request.setAttribute("commentaires", doc1.getCommentaireDoc());
					request.setAttribute("idd", doc1.getId());
					request.setAttribute("nomdoc", doc1.getNom());
					request.setAttribute("description",doc1.getDescript());
					request.setAttribute("ContentFichier", listContent);
					// verification si utilisateur est proprieteur du document.
					request.setAttribute("userid", request.getParameter("userid"));
					if(request.getParameter("getContent")!=null){
						for(int i = 0; i<listContent.size(); i++){
							response.getOutputStream().print(listContent.get(i)+"\n");
						}
						response.getOutputStream().flush();
						response.getOutputStream().close();
					}
					fluxEntree.close();
					
				}
				
				/**
				 * modification de document
				 */
				if(request.getParameter("iddoc")!=null && request.getParameter("modif")!=null){
					
						user.setId((int)session.getAttribute("id"));
						request.setAttribute("amis", user.listAmis());
						if(user.autorisationModif(Integer.parseInt(request.getParameter("iddoc")))){
							
							request.setAttribute("docmodif", request.getParameter("iddoc"));
							this.getServletContext().getRequestDispatcher("/WEB-INF/modif.jsp").forward(request, response);
							System.out.println("passage  ok");
						}
						else{
							request.setAttribute("docmodifMessage", " <h3>Vous n'avez pas droit de modifier ce fichier.</br> Veuillez de contacter le proprietaire.</h3>");
							this.getServletContext().getRequestDispatcher("/WEB-INF/modif.jsp").forward(request, response);
						}
				}
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		if(request.getParameter("getContent")==null)
			this.getServletContext().getRequestDispatcher("/WEB-INF/document.jsp").forward(request, response);
		
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
					
					Part part = request.getPart( "fichier" );
					
					String nomFichier = getNomFichier( part );
					
					if ( nomFichier != null && !nomFichier.isEmpty() ) {
						System.out.println("fichier detecte");
					        String nomChamp = part.getName();
					        System.out.println("document");
					       
					        System.out.println((nomFichier.substring(nomFichier.lastIndexOf('.'), nomFichier.length())));
					        nomFichier = nomFichier.substring( nomFichier.lastIndexOf( '/' ) + 1 ).substring( nomFichier.lastIndexOf( '\\' ) + 1 );
					        String type = nomFichier.substring(nomFichier.lastIndexOf('.'), nomFichier.length());
					        String newNom = nomFichier.substring(0, nomFichier.lastIndexOf('.'));
					        	int Nb = doc1.docExiste()+1;
					        	newNom = newNom+""+Nb;
								user.setId(id);
								user.setNom(nom);
								user.setPrenom(prenom);
								 Document doc = new Document(doc1.nbDoc, newNom+type,user,0,new Date(0000));
								 //ecriture du fichier dans la bd
								 String descript = request.getParameter("descript");
								 System.out.println("descrip "+descript);
								 doc.setDescript(descript);
								 doc.ajouterDoc();
								 doc.accesDoc(id,doc.nbDoc());
								 //ecriture du fichier le reperetoire des fichier
								 ecrireFichier( part, newNom+type, chemin );
						        request.setAttribute( nomChamp, nomFichier );
						        
					    }
					System.out.println("Client lourd doc");
			 
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			System.out.println("passage");
			 response.sendRedirect("Connection?doc=1");
		
	}

}

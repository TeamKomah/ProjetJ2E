package controller;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Document;
import model.Utilisateur;

/**
 * Servlet implementation class VersionDocS
 */
//@WebServlet("/VersionDocS")
public class VersionDocS extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public VersionDocS() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			Document doc1 = new Document();
			Utilisateur user = new Utilisateur();
			
			if(request.getParameter("iddoc")!=null && request.getParameter("version")!=null){
				
				HttpSession session= request.getSession();
				user.setId((int)session.getAttribute("id"));
				request.setAttribute("amis", user.listAmis());
				request.setAttribute("nonLu", user.getMessageNonLu());
				request.setAttribute("lesVersions", doc1.versions(Integer.parseInt(request.getParameter("iddoc")), user));
			}
			
			
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		this.getServletContext().getRequestDispatcher("/WEB-INF/modification.jsp").forward(request, response);
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
			//if(request.getParameter("newversion")!=null){
				user.setId(id);
				user.setNom(nom);
				user.setPrenom(prenom);
				
				int docid = Integer.parseInt(request.getParameter("iddoc"));
				System.out.println("nouveau doc");
				
				doc1.setId(docid);
				doc1.trouverdoc();
				String nomFichier = doc1.getNom();
				nomFichier = nomFichier.substring( nomFichier.lastIndexOf( '/' ) + 1 ).substring( nomFichier.lastIndexOf( '\\' ) + 1 );
		        String type = nomFichier.substring(nomFichier.lastIndexOf('.'), nomFichier.length());
		        String newNom = nomFichier.substring(0, nomFichier.lastIndexOf('.'));
		        	int Nb = doc1.docExiste()+1;
		        	newNom = newNom+""+Nb+doc1.nbDocV()+"V";
				
				Document doc = new Document(0,newNom+type,user,0,new Date(0000));
				File file = new File(chemin+newNom+type);
				file.createNewFile();
				FileWriter ff = new FileWriter(file);
				ff.write((String)request.getParameter("newversion"));
				ff.write("\n");
				ff.close();
				
				doc.nouveauVersion(doc1.getId(),user.getId());
				doc.accesDoc(id,doc.nbDocV());
				System.out.println("nouvelle version "+request.getParameter("newversion"));
				
			//}
			
			
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		 response.sendRedirect("Connection");
	}

}

package controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Document;
import model.Utilisateur;

/**
 * Servlet implementation class Version
 */
//@WebServlet("/Version")
public class Version extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Version() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String chemin = this.getServletConfig().getInitParameter("chemin");
		try {
			HttpSession session= request.getSession();
			Document doc1 = new Document();
			Utilisateur user = new Utilisateur();
			if(request.getParameter("iddoc") != null ){
				 int idd = Integer.parseInt(request.getParameter("iddoc"));
				 doc1.setId(idd);
			
					doc1.recupererDocVersio();
					request.setAttribute("iddocVersion", "1");
			}
				
			
			/**
			 * suppression du document
			 */
			if(request.getParameter("iddoc")!=null && request.getParameter("suppri")!=null){
					doc1.supprimerVersion(Integer.parseInt(request.getParameter("iddoc")));
				
				
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
			this.getServletContext().getRequestDispatcher("/WEB-INF/documentVersion.jsp").forward(request, response);
		
	}

	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

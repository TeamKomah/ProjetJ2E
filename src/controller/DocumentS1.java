package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Document;
import model.Utilisateur;

/**
 * Servlet implementation class DocumentS1
 */
@WebServlet("/DocumentS1")
public class DocumentS1 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DocumentS1() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
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
			
		System.out.println("ajout commence");
		System.out.println("doc id " +Integer.parseInt(request.getParameter("iddoc")));
				doc1 = new Document();
				user = new Utilisateur();
				Utilisateur us = new Utilisateur();
				user.setId(id);
				int i, count= user.nbAmis();
				System.out.println("user nbamis " +count);
				System.out.println("user " +id);
				for(i=1; i <= count+1; i++){
					String s= ""+i;
					System.out.println("okkkk"+s);
					System.out.println("user " +request.getParameter(s)); 
					if(request.getParameter(s)!=null){
						System.out.println("user ok " +Integer.parseInt(request.getParameter(s)));
						us.setId(Integer.parseInt(request.getParameter(s)));
						doc1.setId(Integer.parseInt(request.getParameter("iddoc")));
						doc1.ajoutContributeur(us);
						
					}
				}
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		response.sendRedirect("Connection");
	}

}

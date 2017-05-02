package controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Document;
import model.Utilisateur;

/**
 * Servlet implementation class Profile
 */
@WebServlet("/Profile")
public class Profile extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Profile() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	try {
			if(request.getParameter("prof")!=null){
				
				Utilisateur us = new Utilisateur();
				Document doc = new Document();
				us.getUtilisateur(Integer.parseInt(request.getParameter("prof")));
				request.setAttribute("profnom", us.getNom());
				request.setAttribute("profprenom", us.getPrenom());
				request.setAttribute("profid", us.getId());
				request.setAttribute("amis", us.listAmis());
				request.setAttribute("mesdocs",us.mesDocuments(us));	
				request.setAttribute("docspartager",us.listDocPartager(us));
			}
			
			if(request.getParameter("profdoc")!=null){
				
				Utilisateur us = new Utilisateur();
				Document doc = new Document();
				us.getUtilisateur(Integer.parseInt(request.getParameter("profdoc")));
				request.setAttribute("profnom", us.getNom());
				request.setAttribute("profprenom", us.getPrenom());
				request.setAttribute("profid", us.getId());
				request.setAttribute("amis", us.listAmis());
				request.setAttribute("mesdocs",us.mesDocuments(us));	
				request.setAttribute("docspartager",us.listDocPartager(us));
			}
			
			
				
			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.getServletContext().getRequestDispatcher("/WEB-INF/profile.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String recherche = request.getParameter("recherche");
		
		try {
			Utilisateur us = new Utilisateur();
			Document doc = new Document();
			
			request.setAttribute("amisPropose", us.rechercheAmis(recherche));
			//request.setAttribute("lesAmisDamis", us.lesAmisAmis());
			
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		this.getServletContext().getRequestDispatcher("/WEB-INF/utilisateur.jsp").forward(request, response);
	}

}

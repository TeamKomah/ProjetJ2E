package controller;

import java.awt.SecondaryLoop;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Objects;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import model.ConnectionBD;
import model.Utilisateur;

/**
 * Servlet implementation class Connection
 */
//@WebServlet("/Connection")
public class Connection extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public Connection() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		try {
			Utilisateur user = new Utilisateur();
			user.setId((int)session.getAttribute("id"));
			request.setAttribute("amis", user.listAmis());
			int doc = Integer.parseInt(request.getParameter("doc"));
			if(doc==1){
				request.setAttribute("mesdocs",user.mesDocuments(user));
			}
		} catch (ClassNotFoundException | SQLException e) {
			
			e.printStackTrace();
		}
		
		this.getServletContext().getRequestDispatcher("/WEB-INF/utilisateur.jsp").forward(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String pseudo = request.getParameter("pseudo");
		String mdp = request.getParameter("mdp");
		try {
			
			//ConnectionBD connect= ConnectionBD.getConnectionBD();
			Utilisateur user = new Utilisateur();
			HttpSession session = request.getSession();
			
			if(user.identifier(pseudo, mdp)){
				session.setAttribute("nom", user.getNom());
				session.setAttribute("prenom", user.getPrenom());
				session.setAttribute("id", user.getId());
				request.setAttribute("amis", user.listAmis());
				
				this.getServletContext().getRequestDispatcher("/WEB-INF/utilisateur.jsp").forward(request, response);
			}
			else{
				System.out.println("Erreur de connection � la Base de Donnes");
			}
		} catch (SQLException e) {
			
			this.getServletContext().getRequestDispatcher("/WEB-INF/index.jsp").forward(request, response);
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		}

}

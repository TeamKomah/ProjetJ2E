package controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Utilisateur;

/**
 * Servlet implementation class Index
 */
//@WebServlet("/Index")
public class Index extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public Index() {
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			if(request.getParameter("decon")!=null){
				Utilisateur user;
				
				user = new Utilisateur();
				HttpSession session = request.getSession();
					session.setAttribute("nom", null);
					session.setAttribute("prenom", null);
					session.setAttribute("id", null);
					//user.getConnect().close();
				this.getServletContext().getRequestDispatcher("/WEB-INF/index.jsp").forward(request, response);
			}
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		this.getServletContext().getRequestDispatcher("/WEB-INF/index.jsp").forward(request, response);
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

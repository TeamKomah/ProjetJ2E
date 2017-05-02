package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Utilisateur;

/**
 * Servlet implementation class ChargerNouveauMessage
 */
@WebServlet("/ChargerNouveauMessage")
public class ChargerNouveauMessage extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ChargerNouveauMessage() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/xml");
		response.setHeader("Cache-Control", "no-cache");
		int id = Integer.parseInt(request.getParameter("id"));
		try {
			Utilisateur exp = new Utilisateur(id);
			ArrayList<int[]> nwM = exp.getMessageNonLu();
			response.getWriter().write("<new>");
			for (int i = 0; i < nwM.size(); i++) {
				int tab[] = nwM.get(i);
				response.getWriter().write("<message>"
						+ "<ami>"+tab[0]+"</ami>"
						+ "<nbr>"+tab[1]+"</nbr>"
						+ "</message>");
			}
			response.getWriter().write("</new>");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

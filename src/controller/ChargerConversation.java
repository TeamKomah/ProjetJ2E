package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Message;
import model.Utilisateur;

/**
 * Servlet implementation class ChargerConversation
 */
@WebServlet("/ChargerConversation")
public class ChargerConversation extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ChargerConversation() {
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
		int idR = Integer.parseInt(request.getParameter("recep"));
		int idE = Integer.parseInt(request.getParameter("exp"));
		try {
			Utilisateur exp = new Utilisateur(idE);
			int a = exp.tousLu(idR);
			ArrayList<Message> com = exp.getCommunication(idR);
			response.getWriter().write("<convers>");
			for (int i = 0; i < com.size(); i++){
				Message msg = com.get(i);
				response.getWriter().write("<message>"
						+ "<expediteur>"+msg.getExpediteur().getId()+"</expediteur>"
						+ "<heure>"+msg.getDateMes()+"</heure>"
						+ "<cont>"+msg.getContenu()+"</cont>"
						+ "</message>");
			}
			response.getWriter().write("</convers>");
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

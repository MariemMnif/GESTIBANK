package controllers;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.AssuranceDAO;
import model.Assurance;
import model.Compte;

/**
 * Servlet implementation class ListeAssuranceServlet
 */
@WebServlet("/ListeAssuranceServlet")
public class ListeAssuranceServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	AssuranceDAO assuranceDAO=new AssuranceDAO();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ListeAssuranceServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Assurance> assurances = assuranceDAO.getAllAssurance();
        request.setAttribute("listAssurances", assurances);
        System.out.println(assurances);
        RequestDispatcher dispatcher = request.getRequestDispatcher("listeAssurance.jsp");
        dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

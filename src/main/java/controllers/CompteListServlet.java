package controllers;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.CompteDAO;
import model.Compte;

/**
 * Servlet implementation class CompteListServlet
 */
@WebServlet("/CompteListServlet")
public class CompteListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private CompteDAO compteDAO;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CompteListServlet() {
        super();
        this.compteDAO = new CompteDAO();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		 List<Compte> comptes = compteDAO.getAllComptes();
	        request.setAttribute("listComptes", comptes);
	        System.out.println(comptes);
	        RequestDispatcher dispatcher = request.getRequestDispatcher("ListCompte.jsp");
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

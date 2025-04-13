package controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.AssuranceDAO;
import dao.CompteDAO;
import model.Assurance;
import model.Compte;

/**
 * Servlet implementation class ConsulterAssuranceServlet
 */
@WebServlet("/ConsulterAssuranceServlet")
public class ConsulterAssuranceServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ConsulterAssuranceServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String numeroCompte = request.getParameter("numeroCompte");
        CompteDAO compteDAO = new CompteDAO();
        Compte compte = compteDAO.findByNumeroCompte(numeroCompte);
        AssuranceDAO assuranceDAO = new AssuranceDAO();
        Assurance assurance = assuranceDAO.findAssuranceByNumeroCompte(numeroCompte);
        request.setAttribute("assuranceK", assurance);
        request.getRequestDispatcher("consulterAssurance.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

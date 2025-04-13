package controllers;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.CompteDAO;
import dao.DAOOperation;
import model.Compte;
import model.Operation;

/**
 * Servlet implementation class OperationServlet
 */
@WebServlet("/OperationServlet")
public class OperationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public OperationServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String numeroCompte = request.getParameter("numeroCompte");
		System.out.println("numeroCompte"+numeroCompte);
		request.getSession().setAttribute("numeroCompte", numeroCompte);
		List<Operation> operations = DAOOperation.getOperationsByAccountNumber(numeroCompte);
        request.setAttribute("listeOperations", operations);
        request.setAttribute("numeroCompte", numeroCompte);
        System.out.println(operations);System.out.println(numeroCompte);
        
        CompteDAO compte = new CompteDAO();
        List<Compte>comptes =compte.getAllComptes();
        comptes.remove(compte.findByNumeroCompte(numeroCompte));
        request.setAttribute("comptes", comptes);
        
		request.getRequestDispatcher("operation.jsp").forward(request, response);
    
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

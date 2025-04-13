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
 * Servlet implementation class GestionOperationServlet
 */
@WebServlet("/GestionOperationServlet")
public class GestionOperationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GestionOperationServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("operation.jsp").forward(request, response);
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		 	String	numeroCompte=(String) request.getSession().getAttribute("numeroCompte");
		 	System.out.println("Numéro de compte récupéré : " + numeroCompte);
		  
	        String typeOperation = request.getParameter("typeOperation"); System.out.println("typeOperation : " + typeOperation);
	        double montant = Double.parseDouble(request.getParameter("montant")); System.out.println("montant: " + montant);
	        
	        CompteDAO compteDAO = new CompteDAO();
	        Compte compte = compteDAO.findByNumeroCompte(numeroCompte);
	        
	        int numOperation = 0; 

	        List<Operation> operations = DAOOperation.getOperationsByAccountNumber(compte.getNumeroCompte());
	        if (operations != null && !operations.isEmpty()) {
	            numOperation = operations.size() + 1;
	        } else {
	            numOperation = 1; 
	        }
	        System.out.println("numOperation"+numOperation);
	    
	       if (typeOperation.equals("retrait")) {
	        	   double solde = compte.getSoldeCompte();
	               solde -= montant;
	        	   compte.setSoldeCompte(solde);
	        	   compteDAO.update(compte);
	          } else if (typeOperation.equals("versement")) {
	        	  double solde = compte.getSoldeCompte();
	        	  solde += montant;
	        	  compte.setSoldeCompte(solde);
	        	  compteDAO.update(compte);
	       } else if (typeOperation.equals("virement")) {
	        	  String numeroCompteDestination = request.getParameter("numCompteDest");
	        	  Compte compteDestination = compteDAO.findByNumeroCompte(numeroCompteDestination);
	            
	        	  double soldeSource = compte.getSoldeCompte();
	              double soldeDestination = compteDestination.getSoldeCompte();
	            
	        	 soldeSource -= montant;
	        	 soldeDestination += montant;
	            
	        	 compte.setSoldeCompte(soldeSource);
	             compteDestination.setSoldeCompte(soldeDestination);
	            
	        	 compteDAO.update(compte);
	        	 compteDAO.update(compteDestination);
	        }
	       Operation operation = new Operation();
           operation.setNumCompte(compte.getNumeroCompte());
           operation.setType(typeOperation);
           operation.setMontant(montant);
           operation.setNumOperation(numOperation);
         
           DAOOperation daoOperation = new DAOOperation();
           boolean operationReussie=daoOperation.addOperation(operation);
           if (operationReussie) {
               request.setAttribute("successMessage", "Opération réussie !");
           } else {
               request.setAttribute("errorMessage", "Échec de l'opération. Veuillez réessayer.");
           }
           
           request.getRequestDispatcher("message.jsp").forward(request, response);

	}
	

}
package controllers;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

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
 * Servlet implementation class AssuranceServlet
 */
@WebServlet("/AssuranceServlet")
public class AssuranceServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AssuranceServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		CompteDAO compte = new CompteDAO();
        List<Compte>comptes =compte.getAllComptes();
        request.setAttribute("comptes", comptes);	
        request.getRequestDispatcher("assurance.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String numeroCompte = request.getParameter("numeroCompte");
	    String typeAssurance = request.getParameter("typeAssurance");
	    double montantInitial = Double.parseDouble(request.getParameter("montantInitial"));
	   
	    LocalDate dateDebut = LocalDate.parse(request.getParameter("dateDebut"));
	    LocalDate dateFin = LocalDate.parse(request.getParameter("dateFin"));
	    
	    CompteDAO compteDAO = new CompteDAO();
	    AssuranceDAO assuranceDAO = new AssuranceDAO();
	    
	    Compte compte = compteDAO.findByNumeroCompte(numeroCompte);
	    
	    boolean ajoutReussi = false; 
	    
	    if (typeAssurance.equals("retraite")) {
	    	 double montantMensuel = Double.parseDouble(request.getParameter("montantMensuel"));
	        double montant=montantInitial+montantMensuel;
	        double solde = compte.getSoldeCompte();
	        solde -= montant;
	        compte.setSoldeCompte(solde);
	        compteDAO.update(compte);
	        Assurance assurance = new Assurance(numeroCompte, typeAssurance, montantInitial, montantMensuel, montant, dateDebut, dateFin);
	        ajoutReussi = assuranceDAO.save(assurance);
	    } else if (typeAssurance.equals("voyage")) {
	   
	        if (dateDebut.isEqual(LocalDate.now())) {
	            double solde = compte.getSoldeCompte();
	            solde -= montantInitial;
	            compte.setSoldeCompte(solde);
	            compteDAO.update(compte);
	        }
	        Assurance assurance = new Assurance(numeroCompte, typeAssurance, montantInitial, 0, 0, dateDebut, dateFin);
	        ajoutReussi = assuranceDAO.save(assurance);
	    }
	    
	    if (ajoutReussi) {
	        request.setAttribute("successMessage", "Assurance ajoutée avec succès");
	    } else {
	        request.setAttribute("errorMessage", "Erreur lors de l'ajout de l'assurance");
	    }

	    request.getRequestDispatcher("message.jsp").forward(request, response);
	}

}

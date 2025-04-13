package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Compte;

public class CompteDAO {
	//getAll
	public List<Compte> getAllComptes() {
        List<Compte> comptes = new ArrayList<>();
        Connection cnx = SConnection.getInstance();
        if (cnx == null) {
            System.out.println("La connexion à la base de données n'est pas disponible.");
            return comptes;
        }

        try {
            PreparedStatement st = cnx.prepareStatement("SELECT * FROM Compte");
            ResultSet res = st.executeQuery();
            while (res.next()) {
                Compte compte = new Compte();
                compte.setNumeroCompte(res.getString("numeroCompte"));
                compte.setNom(res.getString("nom"));
                compte.setPrenom(res.getString("prenom"));
                compte.setCin(res.getString("cin"));
                compte.setEmail(res.getString("email"));
                compte.setNumeroTelephone(res.getString("numeroTelephone"));
                compte.setTypeCompte(res.getString("typeCompte"));
                compte.setDecouvert(res.getDouble("decouvert"));
                compte.setTaux(res.getDouble("taux"));
                compte.setSoldeCompte(res.getDouble("soldeCompte"));
                compte.setDateCreationCompte(res.getDate("dateCreationCompte").toLocalDate());
                comptes.add(compte);
            }
            st.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return comptes;
    }
	//findByNumeroCompte
	 public Compte findByNumeroCompte(String numeroCompte) {
	        Compte compte = null;
	        if (numeroCompte == null) return compte;

	        Connection cnx = SConnection.getInstance();
	        if (cnx == null) {
	            System.out.println("La connexion à la base de données n'est pas disponible.");
	            return compte;
	        }

	        try {
	            PreparedStatement st = cnx.prepareStatement("SELECT * FROM Compte WHERE numeroCompte = ?");
	            st.setString(1, numeroCompte);
	            ResultSet res = st.executeQuery();
	            if (res.next()) {
	                compte = new Compte();
	                compte.setNumeroCompte(res.getString("numeroCompte"));
	                compte.setNom(res.getString("nom"));
	                compte.setPrenom(res.getString("prenom"));
	                compte.setCin(res.getString("cin"));
	                compte.setEmail(res.getString("email"));
	                compte.setNumeroTelephone(res.getString("numeroTelephone"));
	                compte.setTypeCompte(res.getString("typeCompte"));
	                compte.setDecouvert(res.getDouble("decouvert"));
	                compte.setTaux(res.getDouble("taux"));
	                compte.setSoldeCompte(res.getDouble("soldeCompte"));
	                compte.setDateCreationCompte(res.getDate("dateCreationCompte").toLocalDate());
	                compte.setDateCreationCompte(res.getDate("dateCreationCompte").toLocalDate());
	            }
	            st.close();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }

	        return compte;
	    }
	// save 
    public boolean save(Compte compte) {
        if (compte == null)
            return false;
        Connection cnx = SConnection.getInstance();
        if (cnx == null) {
            System.out.println("La connexion à la base de données n'est pas disponible.");
            return false;
        }

        int n = 0;
        try {
            PreparedStatement checkSt = cnx.prepareStatement("SELECT * FROM Compte WHERE numeroCompte = ?");
            checkSt.setString(1, compte.getNumeroCompte());
            ResultSet res = checkSt.executeQuery();
            if (!res.next()) { // Si le compte n'existe pas, l'ajouter
            	PreparedStatement insertSt = cnx.prepareStatement(
            		    "INSERT INTO Compte (numeroCompte, nom, prenom, cin, email, numeroTelephone, typeCompte, soldeCompte, dateCreationCompte, decouvert, taux) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
            	insertSt.setString(1, compte.getNumeroCompte());
                insertSt.setString(2, compte.getNom());
                insertSt.setString(3, compte.getPrenom());
                insertSt.setString(4, compte.getCin());
                insertSt.setString(5, compte.getEmail());
                insertSt.setString(6, compte.getNumeroTelephone());
                insertSt.setString(7, compte.getTypeCompte());
                insertSt.setDouble(8, compte.getSoldeCompte());
                insertSt.setString(9, compte.getDateCreationCompte().toString());
                insertSt.setLong(10, (long) compte.getDecouvert());
                insertSt.setLong(11, (long) compte.getTaux());
              
                n = insertSt.executeUpdate();
                if (n != 0)
                    System.out.println("Compte " + compte.getNumeroCompte() + " ajouté.");
            } else {
                System.out.println("Le compte " + compte.getNumeroCompte() + " existe déjà.");
            }
            checkSt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return n > 0;
    }
    
    
    public boolean delete(String numeroCompte) {
        if (numeroCompte == null)
            return false;

        Connection cnx = SConnection.getInstance();
        if (cnx == null) {
            System.out.println("La connexion à la base de données n'est pas disponible.");
            return false;
        }

        int n = 0;
        try {
            PreparedStatement st = cnx.prepareStatement("DELETE FROM Compte WHERE numeroCompte = ?");
            st.setString(1, numeroCompte);
            n = st.executeUpdate();
            if (n != 0) {
                System.out.println("Compte " + numeroCompte + " supprimé.");
            } else {
                System.out.println("Le compte " + numeroCompte + " n'existe pas.");
            }
            st.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return n > 0;
    }

    // update
public boolean update(Compte compte) {
    if (compte == null)
        return false;

    Connection cnx = SConnection.getInstance();
    if (cnx == null) {
        System.out.println("La connexion à la base de données n'est pas disponible.");
        return false;
    }

    int n = 0;
    try {
        PreparedStatement st = cnx.prepareStatement(
                "UPDATE Compte SET nom=?, prenom=?, cin=?, email=?, numeroTelephone=?, typeCompte=?, decouvert=?, taux=?, soldeCompte=? WHERE numeroCompte=?");
        st.setString(1, compte.getNom());
        st.setString(2, compte.getPrenom());
        st.setString(3, compte.getCin());
        st.setString(4, compte.getEmail());
        st.setString(5, compte.getNumeroTelephone());
        st.setString(6, compte.getTypeCompte());
        st.setDouble(7, compte.getDecouvert());
        st.setDouble(8, compte.getTaux());
        st.setDouble(9, compte.getSoldeCompte());
        st.setString(10, compte.getNumeroCompte()); // numéro de compte est le 10ème paramètre
        n = st.executeUpdate();
        if (n != 0) {
            System.out.println("Compte " + compte.getNumeroCompte() + " mis à jour.");
        } else {
            System.out.println("Le compte " + compte.getNumeroCompte() + " n'existe pas.");
        }
        st.close();
    } catch (SQLException e) {
        e.printStackTrace();
    }

    return n > 0;
}
public boolean updateSolde(String numeroCompte, double nouveauSolde) {
    Connection cnx = SConnection.getInstance();
    if (cnx == null) {
        System.out.println("La connexion à la base de données n'est pas disponible.");
        return false;
    }

    int n = 0;
    try {
        PreparedStatement st = cnx.prepareStatement("UPDATE Compte SET soldeCompte = ? WHERE numeroCompte = ?");
        st.setDouble(1, nouveauSolde);
        st.setString(2, numeroCompte);
        n = st.executeUpdate();
        if (n != 0) {
            System.out.println("Solde du compte " + numeroCompte + " mis à jour.");
        } else {
            System.out.println("Le compte " + numeroCompte + " n'existe pas.");
        }
        st.close();
    } catch (SQLException e) {
        e.printStackTrace();
    }

    return n > 0;
}
}


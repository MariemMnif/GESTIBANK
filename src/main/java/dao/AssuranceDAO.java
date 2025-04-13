package dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import model.Assurance;
import model.Compte;

public class AssuranceDAO {

	   //getAll
		public List<Assurance> getAllAssurance() {
	        List<Assurance> assurances = new ArrayList<>();
	        Connection cnx = SConnection.getInstance();
	        if (cnx == null) {
	            System.out.println("La connexion à la base de données n'est pas disponible.");
	            return assurances;
	        }

	        try {
	            PreparedStatement st = cnx.prepareStatement("SELECT * FROM Assurance");
	            ResultSet res = st.executeQuery();
	            while (res.next()) {
	            	Assurance assurance =new Assurance();
	            	assurance.setId(res.getInt("id"));
	            	assurance.setNumeroCompte(res.getString("numeroCompte"));
	            	assurance.setTypeAssurance(res.getString("typeAssurance"));
	            	assurance.setMontantInitial(res.getDouble("montantInitial"));
	            	assurance.setMontantMensuel(res.getDouble("montantMensuel"));
	            	assurance.setMontant(res.getDouble("montant"));
	            	assurance.setDateDebut(res.getDate("dateDebut").toLocalDate());
	            	assurance.setDateFin(res.getDate("dateFin").toLocalDate());
	            	assurance.setEtat(res.getString("etat"));
	                assurances.add(assurance);
	            }
	            st.close();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }

	        return assurances;
	    }    
		// Rechercher une assurance par  numéro de compte
		public Assurance findAssuranceByNumeroCompte(String numeroCompte) {
		    Assurance assurance = null;
		    Connection cnx = SConnection.getInstance();
		    if (cnx == null) {
		        System.out.println("La connexion à la base de données n'est pas disponible.");
		        return assurance;
		    }

		    try {
		        PreparedStatement st = cnx.prepareStatement("SELECT * FROM Assurance WHERE numeroCompte = ?");
		        st.setString(1, numeroCompte);
		        ResultSet res = st.executeQuery();
		        if (res.next()) {
		            assurance = new Assurance();
		            assurance.setNumeroCompte(res.getString("numeroCompte"));
		            assurance.setTypeAssurance(res.getString("typeAssurance"));
		            assurance.setMontantInitial(res.getDouble("montantInitial"));
		            assurance.setMontantMensuel(res.getDouble("montantMensuel"));
		            assurance.setMontant(res.getDouble("montant"));
		            assurance.setDateDebut(res.getDate("dateDebut").toLocalDate());
		            assurance.setDateFin(res.getDate("dateFin").toLocalDate());
		            assurance.setEtat(res.getString("etat"));
		        }
		        st.close();
		    } catch (SQLException e) {
		        e.printStackTrace();
		    }

		    return assurance;
		}

		// Rechercher une assurance par ID
				public Assurance 	findAssuranceByID(int id) {
				    Assurance assurance = null;
				    Connection cnx = SConnection.getInstance();
				    if (cnx == null) {
				        System.out.println("La connexion à la base de données n'est pas disponible.");
				        return assurance;
				    }

				    try {
				        PreparedStatement st = cnx.prepareStatement("SELECT * FROM Assurance WHERE id = ?");
				        st.setInt(1,id);
				        ResultSet res = st.executeQuery();
				        if (res.next()) {
				            assurance = new Assurance();
				            assurance.setNumeroCompte(res.getString("numeroCompte"));
				            assurance.setTypeAssurance(res.getString("typeAssurance"));
				            assurance.setMontantInitial(res.getDouble("montantInitial"));
				            assurance.setMontantMensuel(res.getDouble("montantMensuel"));
				            assurance.setMontant(res.getDouble("montant"));
				            assurance.setDateDebut(res.getDate("dateDebut").toLocalDate());
				            assurance.setDateFin(res.getDate("dateFin").toLocalDate());
				            assurance.setEtat(res.getString("etat"));
				        }
				        st.close();
				    } catch (SQLException e) {
				        e.printStackTrace();
				    }

				    return assurance;
				}

				
		//save
	    public boolean save(Assurance assurance) {
	        if (assurance == null)
	            return false;

	        Connection cnx = SConnection.getInstance();
	        if (cnx == null) {
	            System.out.println("La connexion à la base de données n'est pas disponible.");
	            return false;
	        }

	        int n = 0;
	        try {
	            PreparedStatement st = cnx.prepareStatement(
	                    "INSERT INTO Assurance (numeroCompte, typeAssurance, montantInitial, montantMensuel, montant, dateDebut, dateFin, etat) VALUES (?, ?, ?, ?, ?, ?, ?, ?)");
	            st.setString(1, assurance.getNumeroCompte());
	            st.setString(2, assurance.getTypeAssurance());
	            st.setDouble(3, assurance.getMontantInitial());
	            st.setDouble(4, assurance.getMontantMensuel());
	            st.setDouble(5, assurance.getMontant());
	            st.setString(6, assurance.getDateDebut().toString());
	            st.setString(7, assurance.getDateFin().toString());
	            st.setString(8, assurance.getEtat());

	            n = st.executeUpdate();
	            if (n != 0) {
	                System.out.println("Assurance ajoutée avec succès.");
	            } else {
	                System.out.println("Erreur lors de l'ajout de l'assurance.");
	            }
	            st.close();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }

	        return n > 0;
	    }

	    public boolean update(Assurance assurance) {
	        if (assurance == null)
	            return false;

	        Connection cnx = SConnection.getInstance();
	        if (cnx == null) {
	            System.out.println("La connexion à la base de données n'est pas disponible.");
	            return false;
	        }

	        int n = 0;
	        try {
	            PreparedStatement st = cnx.prepareStatement(
	                    "UPDATE Assurance SET typeAssurance = ?, montantInitial = ?, montantMensuel = ?, montant = ?, dateDebut = ?, dateFin = ? WHERE numeroCompte = ?");
	            st.setString(1, assurance.getTypeAssurance());
	            st.setDouble(2, assurance.getMontantInitial());
	            st.setDouble(3, assurance.getMontantMensuel());
	            st.setDouble(4, assurance.getMontant());
	            st.setString(5, assurance.getDateDebut().toString());
	            st.setString(6, assurance.getDateFin().toString());
	           
	            st.setString(7, assurance.getNumeroCompte());

	            n = st.executeUpdate();
	            if (n != 0) {
	                System.out.println("Assurance mise à jour avec succès.");
	            } else {
	                System.out.println("Erreur lors de la mise à jour de l'assurance.");
	            }
	            st.close();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }

	        return n > 0;
	    }
	    
	    public boolean updateEtatAssurance(int id, String nouvelEtat) {
	        Connection cnx = SConnection.getInstance();
	        if (cnx == null) {
	            System.out.println("La connexion à la base de données n'est pas disponible.");
	            return false;
	        }

	        int n = 0;
	        try {
	            PreparedStatement st = cnx.prepareStatement(
	                "UPDATE Assurance SET etat = ? WHERE id = ?");
	            st.setString(1, nouvelEtat);
	            st.setInt(2, id);
	            
	            // Exécution de la requête de mise à jour
	            n = st.executeUpdate();
	            
	            // Vérification du nombre de lignes affectées
	            if (n != 0) {
	                System.out.println("L'état de l'assurance avec l'ID " + id + " a été mis à jour.");
	            } else {
	                System.out.println("L'assurance avec l'ID " + id + " n'existe pas.");
	            }
	            
	            st.close();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }

	        return n > 0;
	    }


}




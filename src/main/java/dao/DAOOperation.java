package dao;

import model.Operation;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
public class DAOOperation {
	// Méthode pour ajouter une opération
    public boolean addOperation(Operation operation) {
        Connection connection = SConnection.getInstance();
        if (connection == null) {
            System.out.println("La connexion à la base de données n'est pas disponible.");
            return false;
        }

        try {
            String query = "INSERT INTO Operation (numCompte, type, montant, date,numOperation) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, operation.getNumCompte());
            statement.setString(2, operation.getType());
            statement.setDouble(3, operation.getMontant());
            statement.setString(4, LocalDate.now().toString());
            statement.setInt(5, operation.getNumOperation());
            
            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Opération insérée avec succès.");
                return true;
            } else {
                System.out.println("Échec de l'insertion de l'opération.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    // Méthode pour récupérer toutes les opérations
    public List<Operation> getAllOperations() {
        List<Operation> operations = new ArrayList<>();
        Connection connection = SConnection.getInstance();
        if (connection == null) {
            System.out.println("La connexion à la base de données n'est pas disponible.");
            return operations;
        }

        try {
            String query = "SELECT * FROM Operation";
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Operation operation = new Operation();
                operation.setId(resultSet.getInt("id"));
                operation.setNumOperation(resultSet.getInt("numOperation"));
                operation.setNumCompte(resultSet.getString("numCompte"));
                operation.setType(resultSet.getString("type"));
                operation.setMontant(resultSet.getDouble("montant"));
                operation.setDate(resultSet.getDate("date").toLocalDate());
                operations.add(operation);
            }
            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return operations;
    }

    // Méthode pour récupérer les opérations par numéro de compte
    public static List<Operation> getOperationsByAccountNumber(String string) {
        List<Operation> operations = new ArrayList<>();
        Connection connection = SConnection.getInstance();
        if (connection == null) {
            System.out.println("La connexion à la base de données n'est pas disponible.");
            return operations;
        }

        try {
            String query = "SELECT * FROM Operation WHERE numCompte = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, string);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Operation operation = new Operation();
                operation.setId(resultSet.getInt("id"));
                operation.setNumOperation(resultSet.getInt("numOperation"));
                operation.setNumCompte(resultSet.getString("numCompte"));
                operation.setType(resultSet.getString("type"));
                operation.setMontant(resultSet.getDouble("montant"));
                operation.setDate(resultSet.getDate("date").toLocalDate());
                operations.add(operation);
            }
            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return operations;
    }
}

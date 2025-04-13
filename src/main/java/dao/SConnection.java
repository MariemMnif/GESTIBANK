package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SConnection {
	private static String url="jdbc:mysql://localhost:3306/ProjetJEE?useSSL=false";
	private static String utilisateur="root";
	private static String motPasse="";
	private static Connection cnx;
	public static Connection getInstance() {
		if (cnx == null) {
		try {
			//Chargement du pilote
			Class.forName("com.mysql.jdbc.Driver");
		} 
		catch (ClassNotFoundException e) {
			System.out.println("Impossible de charger le pilote");}
		try {
			//Etablissement de connexion
			cnx = DriverManager.getConnection(url, utilisateur, motPasse);
		} 
		catch (SQLException e) {
			System.out.println("Connexion a echouee");
			System.out.println(e);}
		 }
		return cnx;
		}
	public static void close() {
		try {
			if (cnx != null)
				cnx.close();
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
		}
}

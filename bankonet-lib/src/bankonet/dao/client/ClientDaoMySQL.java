package bankonet.dao.client;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import com.bankonet.Client;

public class ClientDaoMySQL implements ClientDao {

	@Override
	public Client getClient(String identifiant) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Client> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void remove(Client client) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void save(Client client) {
		// TODO Auto-generated method stub
		Connection connection;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			String url="� jdbc:mysql://localhost:3306/bankonet";
			connection = DriverManager.getConnection(url);
		
		
			StringBuilder sb= new StringBuilder();
			
			sb.append("INSERT INTO `client`(`identifiant`, `nom`, `prenom`, `password`) VALUES (");
				sb.append(client.getIdentifiant()+",");
				sb.append(client.getNom()+",");
				sb.append(client.getPrenom()+",");
				sb.append(client.getPassword()+",)");
			
			Statement stmt= connection.createStatement();
			stmt.executeQuery(sb.toString());
			
		} catch (SQLException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}

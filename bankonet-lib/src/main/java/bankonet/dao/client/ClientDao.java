package bankonet.dao.client;

import java.util.List;

import com.bankonet.Client;

public interface ClientDao {
	Client getClient(String identifiant);
	List<Client> getAll();
	void remove(Client client);
	void save(Client client);
	List<Client> findByName(String nom);
	List<Client> findByFirstname(String prenom);
	void updateClient(Client client);
}

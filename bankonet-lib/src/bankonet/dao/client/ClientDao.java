package bankonet.dao.client;

import java.util.List;

import com.bankonet.Client;

public interface ClientDao {
	void setClient(Client client);
	Client getClient(String identifiant);
	List<Client> getAll();
	void remove(Client client);
	void save(Client client);
}

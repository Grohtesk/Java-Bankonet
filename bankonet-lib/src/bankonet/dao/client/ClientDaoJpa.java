package bankonet.dao.client;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import com.bankonet.Client;

public class ClientDaoJpa implements ClientDao {
	
	EntityManagerFactory emf;
	EntityManager em;
	
	public ClientDaoJpa(String unitName) {
		emf=Persistence.createEntityManagerFactory(unitName);
	}

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
		EntityManager em=emf.createEntityManager();
		EntityTransaction et = em.getTransaction();
		et.begin();
		em.persist(client);
		et.commit();
		em.close();
	}

}
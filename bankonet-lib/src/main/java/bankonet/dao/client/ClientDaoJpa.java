package bankonet.dao.client;

import java.util.Iterator;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;

import com.bankonet.Client;
import com.bankonet.Compte;

public class ClientDaoJpa implements ClientDao {
	
	EntityManagerFactory emf;
	
	public ClientDaoJpa(EntityManagerFactory emf) {
		this.emf=emf;
	}

	@Override
	public Client getClient(String identifiant) throws NoResultException {
		Client client;
		EntityManager em=emf.createEntityManager();
		client =em.createNamedQuery("Client.getClientByLogin",Client.class)
			.setParameter("identifiant", identifiant)
			.getSingleResult();
		
		Iterator<Compte> iterator = client.getListeComptes().iterator();
		if(iterator.hasNext()) iterator.next();
		em.close();
		return client;
	}

	@Override
	public List<Client> getAll() {
		List<Client> clients;
		EntityManager em=emf.createEntityManager();
		clients =em.createQuery("SELECT c FROM Client c",Client.class)
			.getResultList();
		return clients;
	}

	@Override
	public void save(Client client) {
		EntityManager em=emf.createEntityManager();
		EntityTransaction et = em.getTransaction();
		et.begin();
		em.merge(client);
		et.commit();
		em.close();
	}

	@Override
	public List<Client> findByName(String nom) {
		List<Client> clients;
		EntityManager em=emf.createEntityManager();
		clients =em.createNamedQuery("Client.getClientByName",Client.class)
			.setParameter("nom", nom)
			.getResultList();
		return clients;
	}

	@Override
	public List<Client> findByFirstname(String prenom) {
		List<Client> clients;
		EntityManager em=emf.createEntityManager();
		clients =em.createNamedQuery("Client.getClientByFirstname",Client.class)
			.setParameter("prenom", prenom)
			.getResultList();
		em.close();
		return clients;
	}

	@Override
	public void updateClient(Client client) {
		EntityManager em=emf.createEntityManager();
		EntityTransaction et = em.getTransaction();
		et.begin();
		em.merge(client);
		et.commit();
		em.close();
	}

	@Override
	public void remove(Client client) {
		EntityManager em=emf.createEntityManager();
		EntityTransaction et = em.getTransaction();
		et.begin();
		em.remove(client);
		et.commit();
		em.close();
	}

}

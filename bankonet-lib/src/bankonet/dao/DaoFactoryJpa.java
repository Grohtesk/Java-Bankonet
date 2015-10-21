package bankonet.dao;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import bankonet.dao.client.ClientDao;
import bankonet.dao.client.ClientDaoJpa;
import bankonet.dao.compte.CompteDao;
import bankonet.dao.compte.CompteDaoJpa;

public class DaoFactoryJpa implements DaoFactory{


	EntityManagerFactory emf;
	
	public DaoFactoryJpa() {
		this.emf=Persistence.createEntityManagerFactory("bankonet-lib");
	}
	
	@Override
	public CompteDao getDaoCompte() {
		// TODO Auto-generated method stub
		
		return new CompteDaoJpa(emf);
	}

	@Override
	public ClientDao getDaoClient() {
		// TODO Auto-generated method stub
		return new ClientDaoJpa(emf);
	}

}

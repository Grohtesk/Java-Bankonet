package bankonet.dao;

import bankonet.dao.client.ClientDao;
import bankonet.dao.client.ClientDaoJpa;
import bankonet.dao.compte.CompteDao;

public class DaoFactoryJpa implements DaoFactory{

	private static final String UNIT_NAME = "bankonet-lib";

	@Override
	public CompteDao getDaoCompte() {
		// TODO Auto-generated method stub
		
		return null;
	}

	@Override
	public ClientDao getDaoClient() {
		// TODO Auto-generated method stub
		return new ClientDaoJpa(UNIT_NAME);
	}

}
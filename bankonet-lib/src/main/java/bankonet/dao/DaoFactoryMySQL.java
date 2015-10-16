package bankonet.dao;

import bankonet.dao.client.ClientDao;
import bankonet.dao.client.ClientDaoMySQL;
import bankonet.dao.compte.CompteDao;
import bankonet.dao.compte.CompteDaoMySQL;

public class DaoFactoryMySQL implements DaoFactory{

	@Override
	public CompteDao getDaoCompte() {
		// TODO Auto-generated method stub
		return new CompteDaoMySQL();
	}

	@Override
	public ClientDao getDaoClient() {
		// TODO Auto-generated method stub
		return new ClientDaoMySQL();
	}

}

package bankonet.dao;

import bankonet.dao.client.ClientDao;
import bankonet.dao.compte.CompteDao;

public interface DaoFactory {
	CompteDao getDaoCompte();
	ClientDao getDaoClient();
}

package bankonet.dao.compte;

import java.util.List;

import com.bankonet.Client;
import com.bankonet.Compte;

public interface CompteDao {
	Compte getComtpe(String numero);
	List<Compte> getCompte(Client client);
	void removeCompte(Compte compte);
	void save(Compte compte);
}
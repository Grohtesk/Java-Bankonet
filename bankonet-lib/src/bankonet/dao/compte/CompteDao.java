package bankonet.dao.compte;

import java.util.List;

import com.bankonet.Client;
import com.bankonet.Compte;

public interface CompteDao {
	Compte getCompte(String numero);
	List<Compte> getAll();
	void remove(Compte compte);
	void save(Compte compte);
}

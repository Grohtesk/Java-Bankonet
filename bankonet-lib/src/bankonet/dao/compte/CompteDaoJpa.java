package bankonet.dao.compte;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;

import com.bankonet.Compte;

public class CompteDaoJpa implements CompteDao {

	EntityManagerFactory emf;
	
	public CompteDaoJpa(EntityManagerFactory emf) {
		this.emf=emf;
	}

	@Override
	public Compte getCompte(String numero) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Compte> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void remove(Compte compte) {
		// TODO Auto-generated method stub

	}

	@Override
	public void save(Compte compte) {
		EntityManager em=emf.createEntityManager();
		EntityTransaction et = em.getTransaction();
		et.begin();
			em.persist(compte);
		et.commit();
		em.close();
	}

}

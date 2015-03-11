package aminem.adviceapp.lookUpServices;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import aminem.adviceapp.models.Expert;

@Stateless
public class ExpertEjb {

	@PersistenceContext
	EntityManager em;
	
	
	public List<Expert> allExperts() {
		TypedQuery<Expert> q = em.createQuery("SELECT c FROM Expert c",
				Expert.class);
		return (List<Expert>) q.getResultList();

	}

	public Expert saveExpert(Expert expert) {
		em.persist(expert);
		return expert;
	}
	
	
}

package aminem.adviceapp.lookUpServices;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import aminem.adviceapp.models.Category;

@Stateless
public class CategoryEJB {

	@PersistenceContext
	EntityManager em;

	public List<Category> allCategories() {
		TypedQuery<Category> q = em.createQuery("SELECT c FROM Category c",
				Category.class);
		return (List<Category>) q.getResultList();

	}

	public Category saveCategory(Category cat) {
		em.persist(cat);
		return cat;
	}

}

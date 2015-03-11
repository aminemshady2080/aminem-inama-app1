package aminem.adviceapp.lookUpServices;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import aminem.adviceapp.models.Subcategory;

@Stateless
public class SubCategoryEJB {

	@PersistenceContext
	EntityManager em;

	public List<Subcategory> allSubCategories() {
		TypedQuery<Subcategory> q = em.createQuery(
				"SELECT s FROM SubCategory s", Subcategory.class);
		return (List<Subcategory>) q.getResultList();

	}

	public Subcategory saveSubCategory(Subcategory subcat) {
		em.persist(subcat);
		return subcat;
	}

	public List<Subcategory> allAssociatedSubCategories(int catId) {
		TypedQuery<Subcategory> q = em.createQuery(
				"SELECT s FROM SubCategory s WHERE s.categoryId = :catId",
				Subcategory.class);
		q.setParameter("catId", catId);
		return q.getResultList();
	}
}

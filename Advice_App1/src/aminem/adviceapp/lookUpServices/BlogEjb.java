package aminem.adviceapp.lookUpServices;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import aminem.adviceapp.models.Blogpost;

@Stateless
public class BlogEjb {

	@PersistenceContext
	EntityManager em;

	public List<Blogpost> findAll() {
		TypedQuery<Blogpost> q = em.createQuery("SELECT b FROM Blogpost b",
				Blogpost.class);
		return q.getResultList();
	}

	public Blogpost savePost(Blogpost post) {
		em.persist(post);
		return post;
	}
}

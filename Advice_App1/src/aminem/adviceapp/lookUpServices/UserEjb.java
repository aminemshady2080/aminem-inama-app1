package aminem.adviceapp.lookUpServices;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import aminem.adviceapp.models.Answer;
import aminem.adviceapp.models.Question;
import aminem.adviceapp.models.User;

@Stateless
public class UserEjb {

	@PersistenceContext
	EntityManager em;

	public List<User> allUsers() {
		TypedQuery<User> q = em.createQuery("SELECT a FROM User a", User.class);
		return q.getResultList();
	}

	public User saveUser(User user) {
		em.persist(user);
		return user;

	}

	public User updateUsername(int id, String username) {
		User u = em.find(User.class, id);
		if (u != null) {
			u.setUsername(username);
			return u;
		}
		return null;

	}

	public User updateEmail(int id, String email) {
		User u = em.find(User.class, id);
		if (u != null) {
			u.setEmail(email);
			return u;
		}
		return null;

	}

	public User updateUser(User user) {

		return em.merge(user);

	}

	public User updatePassword(int id, String password) {
		User u = em.find(User.class, id);
		if (u != null) {
			u.setPassword(password);
			return u;
		}
		return null;

	}

	public User loginUser(User user) {

		TypedQuery<User> q = em.createQuery(
				"SELECT a FROM User a WHERE a.email = ?1 ", User.class);
		q.setParameter(1, user.getEmail());
		if (!q.getResultList().isEmpty()) {
			if (q.getSingleResult().getPassword().equals(user.getPassword())) {
				return q.getSingleResult();
			} else {
				return null;
			}
		}
		em.persist(user);
		return user;

	}


	public User findUserById(int userId) {
		User user = em.find(User.class, userId);
		if (user != null) {
			System.out.print("found user: " + user.getUsername());
			return user;
		} else {
			return null;
		}

	}

	public User findUserByUsername(String username) {
		TypedQuery<User> q = em.createQuery(
				"SELECT a FROM User a WHERE a.username = ?1", User.class);
		q.setParameter(1, username);

		if (!q.getResultList().isEmpty()) {
			return q.getSingleResult();
		}
		return null;
	}

	public User findUserByEmail(String email) {
		TypedQuery<User> q = em.createQuery(
				"SELECT a FROM User a WHERE a.email = ?1", User.class);
		q.setParameter(1, email);
		if (!q.getResultList().isEmpty()) {
			return q.getSingleResult();
		}
		return null;

	}

	public User updateUserQuestions(int userId, List<Question> questions) {
		User user = em.find(User.class, userId);
		if (!user.equals(null)) {
			// user.setQuestions(questions);
			return user;
		} else {
			return null;
		}
	}

	public User updateUserAnswers(int userId, List<Answer> answers) {
		User user = em.find(User.class, userId);
		if (!user.equals(null)) {
			// user.setAnswers(answers);
			return user;
		} else {
			return null;
		}
	}

}

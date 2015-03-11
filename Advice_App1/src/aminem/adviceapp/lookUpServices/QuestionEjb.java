package aminem.adviceapp.lookUpServices;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import aminem.adviceapp.models.Question;

@Stateless
public class QuestionEjb {

	@PersistenceContext
	EntityManager em;

	public Question saveQuestion(Question q) {
		em.persist(q);
		return q;
	}

	public List<Question> allQuestions() {
		TypedQuery<Question> q = em.createQuery(
				"SELECT  q FROM Question q ORDER BY q.questionId DESC",
				Question.class);
		return q.getResultList();
	}

	public Question findQuestionById(int id) {
		Question q = em.find(Question.class, id);
		if (q != null) {
			return q;
		} else {
			return null;
		}
	}

}

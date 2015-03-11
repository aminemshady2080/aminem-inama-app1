package aminem.adviceapp.lookUpServices;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import aminem.adviceapp.models.Answer;

@Stateless
public class AnswerEjb {

	@PersistenceContext
	EntityManager em;

	public List<Answer> allAnswers() {
		TypedQuery<Answer> q = em.createQuery("SELECT a FROM Answer a ORDER BY a.answerId DESC",
				Answer.class);
		return q.getResultList();
	}

	public Answer saveAnswer(Answer answer) {
		em.persist(answer);
		return answer;
	}

}

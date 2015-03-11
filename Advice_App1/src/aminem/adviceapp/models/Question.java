package aminem.adviceapp.models;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQuery;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * The persistent class for the question database table.
 * 
 */
@Entity
@NamedQuery(name = "Question.findAll", query = "SELECT q FROM Question q")
public class Question implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "question_id")
	private int questionId;
	@Column(name = "ANSWERED")
	private boolean answered;

	@Temporal(TemporalType.DATE)
	@Column(name = "asked_time")
	private Date askedTime;

	@Column(name = "category_id")
	private int categoryId;
	@Column(name = "DESCRIPTION")
	private String description;
	@Column(name = "TITLE")
	private String title;
	@Column(name = "VALID")
	private boolean valid;

	@Column(name = "user_id")
	private int userId;

	// bi-directional many-to-many association to Expert
	@ManyToMany(mappedBy = "questions", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private Set<Expert> experts;

	public Question() {
	}

	public int getQuestionId() {
		return this.questionId;
	}

	public void setQuestionId(int questionId) {
		this.questionId = questionId;
	}

	public boolean getAnswered() {
		return this.answered;
	}

	public void setAnswered(boolean answered) {
		this.answered = answered;
	}

	public Date getAskedTime() {
		return this.askedTime;
	}

	public void setAskedTime(Date askedTime) {
		this.askedTime = askedTime;
	}

	public int getCategoryId() {
		return this.categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public boolean getValid() {
		return this.valid;
	}

	public void setValid(boolean valid) {
		this.valid = valid;
	}

	public Set<Expert> getExperts() {
		return this.experts;
	}

	public void setExperts(Set<Expert> experts) {
		this.experts = experts;
	}


	public int getUserId() {
		return this.userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

}
package aminem.adviceapp.models;

import java.io.Serializable;

import javax.persistence.*;

import java.util.List;

/**
 * The persistent class for the answer database table.
 * 
 */
@Entity
@NamedQuery(name = "Answer.findAll", query = "SELECT a FROM Answer a")
public class Answer implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "answer_id")
	private int answerId;

	@Column(name = "DESCRIPTION")
	private String description;
	
	@Column(name = "VALID")
	private boolean valid;

	@Lob
	@Column(name = "video_url")
	private String videoUrl;
	
	@Column(name="user_id")
	private int userId;
	@Column(name="question_id")
	private int questionId;

	// bi-directional many-to-many association to Expert
	@ManyToMany(mappedBy = "answers", fetch = FetchType.EAGER,cascade=CascadeType.ALL)
	private List<Expert> experts;

	public Answer() {
	}

	public int getAnswerId() {
		return this.answerId;
	}

	public void setAnswerId(int answerId) {
		this.answerId = answerId;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public boolean getValid() {
		return this.valid;
	}

	public void setValid(boolean valid) {
		this.valid = valid;
	}

	public String getVideoUrl() {
		return this.videoUrl;
	}

	public void setVideoUrl(String videoUrl) {
		this.videoUrl = videoUrl;
	}

	public List<Expert> getExperts() {
		return this.experts;
	}

	public void setExperts(List<Expert> experts) {
		this.experts = experts;
	}



	public int getUserId() {
		return this.userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getQuestionId() {
		return questionId;
	}

	public void setQuestionId(int questionId) {
		this.questionId = questionId;
	}


}
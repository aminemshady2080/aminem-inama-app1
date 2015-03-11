package aminem.adviceapp.models;

import java.io.Serializable;

import javax.persistence.*;

import java.util.Set;


/**
 * The persistent class for the expert database table.
 * 
 */
@Entity
@NamedQuery(name="Expert.findAll", query="SELECT e FROM Expert e")
public class Expert implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="expert_id")
	private int expertId;
	@Column(name="ACTIVE")
	private boolean active;
	@Column(name="EMAIL")
	private String email;

	@Lob
	@Column(name="image_url")
	private String imageUrl;
	@Column(name="PASSWORD")
	private String password;
	@Column(name="PHONE")
	private String phone;

	@Column(name="public_ok")
	private boolean publicOk;

	@Column(name="QUALIFICATIONS")
	@Lob
	private String qualifications;
	@Column(name="USERNAME")
	private String username;

	//bi-directional many-to-many association to Category
	@ManyToMany(mappedBy="experts", fetch=FetchType.EAGER)
	private Set<Category> categories;

	//bi-directional many-to-many association to Answer
	@ManyToMany(fetch=FetchType.EAGER)
	@JoinTable(
		name="expert_answer"
		, joinColumns={
			@JoinColumn(name="expert_id")
			}
		, inverseJoinColumns={
			@JoinColumn(name="answer_id")
			}
		)
	private Set<Answer> answers;

	
	//bi-directional many-to-many association to Question
	@ManyToMany(fetch=FetchType.EAGER)
	@JoinTable(
		name="expert_question"
		, joinColumns={
			@JoinColumn(name="expert_id")
			}
		, inverseJoinColumns={
			@JoinColumn(name="question_id")
			}
		)
	private Set<Question> questions;

	public Expert() {
	}

	public int getExpertId() {
		return this.expertId;
	}

	public void setExpertId(int expertId) {
		this.expertId = expertId;
	}

	public boolean getActive() {
		return this.active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getImageUrl() {
		return this.imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public boolean getPublicOk() {
		return this.publicOk;
	}

	public void setPublicOk(boolean publicOk) {
		this.publicOk = publicOk;
	}

	public String getQualifications() {
		return this.qualifications;
	}

	public void setQualifications(String qualifications) {
		this.qualifications = qualifications;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Set<Category> getcategories() {
		return this.categories;
	}

	public void setcategories(Set<Category> categories) {
		this.categories = categories;
	}

	public Set<Answer> getAnswers() {
		return this.answers;
	}

	public void setAnswers(Set<Answer> answers) {
		this.answers = answers;
	}


	public Set<Question> getQuestions() {
		return this.questions;
	}

	public void setQuestions(Set<Question> questions) {
		this.questions = questions;
	}

}
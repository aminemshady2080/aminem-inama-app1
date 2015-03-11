package aminem.adviceapp.beans;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import aminem.adviceapp.lookUpServices.AnswerEjb;
import aminem.adviceapp.lookUpServices.QuestionEjb;
import aminem.adviceapp.lookUpServices.UserEjb;
import aminem.adviceapp.models.Answer;
import aminem.adviceapp.models.Expert;
import aminem.adviceapp.models.Question;
import aminem.adviceapp.models.User;
import aminem.adviceapp.tools.Utilities;

@ManagedBean
@RequestScoped
public class QuestionBean {

	@Inject
	QuestionEjb ejb;
	@Inject
	UserEjb userEjb;
	@Inject
	AnswerEjb aEjb;

	private List<Question> allQuestions;
	private int numberOfQuestions;
	@Named
	Question question;

	String userEmail;
	String userPassword;

	private int questionId;

	private boolean answered;

	private Date askedTime;

	private String description;

	private int categoryId;

	private String title;

	private boolean valid;
	private boolean loggedIn = false;

	private List<Expert> experts = new ArrayList<Expert>();

	private List<Answer> answers = new ArrayList<Answer>();

	private List<User> users = new ArrayList<User>();
	private String username;

	public QuestionBean() {
		initialize();
	}

	public int getNumberOfQuestions() {
		numberOfQuestions = ejb.allQuestions().size();
		return numberOfQuestions;
	}

	public void setNumberOfQuestions(int numberOfQuestions) {
		this.numberOfQuestions = numberOfQuestions;
	}

	public List<Question> getAllQuestions() {
		allQuestions = ejb.allQuestions();
		return allQuestions;
	}

	public void setAllQuestions(List<Question> allQuestions) {
		this.allQuestions = allQuestions;
	}

	public Question getQuestion() {
		return question;
	}

	public void setQuestion(Question question) {
		this.question = question;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public boolean isLoggedIn() {
		return loggedIn;
	}

	public void setLoggedIn(boolean loggedIn) {
		this.loggedIn = loggedIn;
	}

	public List<Question> findQuestionsByCategory(int catId) {
		List<Question> qq = new ArrayList<Question>();
		for (Question qu : ejb.allQuestions()) {
			if (qu.getCategoryId() == catId) {
				qq.add(qu);
				System.out.println(qu.getTitle());
			}
		}

		return qq;
	}

	public List<Answer> findQuestionsAnswers(int questionId) {
			List<Answer> ans = new ArrayList<Answer>();
			for (Question quest : ejb.allQuestions()) {
				if (quest.getQuestionId() == questionId) {
					for(Answer an:aEjb.allAnswers()){
						if(an.getQuestionId()==questionId){
							ans.add(an);
						}
					}
				}
			}
			return ans;
		
	}

	public String saveQuestion() {

		User user = new User();
		user.setPassword(userPassword);
		user.setEmail(userEmail);
		user.setUsername(userEmail.substring(0, userEmail.indexOf("@")));
		User userByMail = userEjb.loginUser(user);
		if (userByMail != null) {
			Question question = new Question();
			question.setAskedTime(new Date());
			question.setAnswered(false);
			question.setCategoryId(categoryId);
			question.setDescription(description);
			question.setTitle(title);
			question.setUserId(userByMail.getUserId());
			System.err.println("question sent:" + question.toString());
			ejb.saveQuestion(question);
			Utilities.setUserCookies(userByMail);
			return "/resources/snippets/questions.xhtml?faces-redirect=true";
		}
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage("Please Login To use this service"));
		return null;
	}

	public User usersAsked(int questionId) {
		User user = new User();
		for (Question ques : ejb.allQuestions()) {
			if (ques.getQuestionId() == questionId) {
				for (User us : userEjb.allUsers()) {
					if (us.getUserId() == ques.getUserId()) {
						user = us;
					}
				}
			}
		}
		return user;
	}

	public void initialize() {
		FacesContext con = FacesContext.getCurrentInstance();
		HttpServletRequest req = (HttpServletRequest) con.getExternalContext()
				.getRequest();
		if (!req.getSession().isNew()) {

			Cookie[] kk = req.getCookies();
			for (Cookie k : kk) {
				if (k.getName().equals("AppUsername")) {
					username = k.getValue();
				}
				if (k.getName().equals("AppEmail")) {
					userEmail = k.getValue();
				}
				if (k.getName().equals("AppPassword")) {
					userPassword = k.getValue();
				}
				System.out.println("COOOKIIIIEE " + k.getName() + " : "
						+ k.getValue());
			}
		}

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

	public List<Expert> getExperts() {
		return this.experts;
	}

	public void setExperts(List<Expert> experts) {
		this.experts = experts;
	}

	public List<Answer> getAnswers() {
		return this.answers;
	}

	public void setAnswers(List<Answer> answers) {
		this.answers = answers;
	}

	public List<User> getUsers() {
		return this.users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
}

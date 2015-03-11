package aminem.adviceapp.beans;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import aminem.adviceapp.lookUpServices.AnswerEjb;
import aminem.adviceapp.lookUpServices.ExpertEjb;
import aminem.adviceapp.lookUpServices.QuestionEjb;
import aminem.adviceapp.lookUpServices.UserEjb;
import aminem.adviceapp.models.Answer;
import aminem.adviceapp.models.Expert;
import aminem.adviceapp.models.Question;
import aminem.adviceapp.models.User;

@ManagedBean
@RequestScoped
public class AnswerBean {

	@Inject
	AnswerEjb ejb;
	@Inject
	QuestionEjb qEjb;
	@Inject
	ExpertEjb eEjb;
	@Inject
	UserEjb uEjb;

	private int answerId;
	private String description;

	private boolean valid;

	private String videoUrl;
	private List<Expert> allExperts;
	private List<Question> allQuestions;
	private List<Answer> allAnswers;
	private List<User> users;
	int userId;
	String userName, userMail, userPass;
	int questionId;

	// //////////business logic ////////////////////

	public List<Answer> answerOfQuestion(int questionId) {
		List<Answer> ans = new ArrayList<Answer>();
		for (Question quest : qEjb.allQuestions()) {
			if (quest.getQuestionId() == questionId) {
				for (Answer an : ejb.allAnswers()) {
					if (an.getQuestionId() == questionId) {
						ans.add(an);
					}
				}
			}
		}
		return ans;
	}

	public Question questionOfAnswer(int answerId) {
		Question quest = new Question();
		for (Answer ans : ejb.allAnswers()) {
			if (ans.getAnswerId() == answerId) {
				for (Question an : qEjb.allQuestions()) {
					if (an.getQuestionId() == ans.getQuestionId()) {
						quest = an;
					}
				}
			}
		}
		return quest;
	}

	public List<User> usersAnswered(int answerId) {
		List<User> users = new ArrayList<User>();
		for (Answer ans : ejb.allAnswers()) {
			if (ans.getAnswerId() == answerId) {
				for (User u : uEjb.allUsers()) {
					if (u.getUserId() == ans.getUserId()) {
						users.add(u);
					}
				}
			}
		}
		return users;
	}

	public String saveAnswer() {
		HttpServletRequest request = (HttpServletRequest) FacesContext
				.getCurrentInstance().getExternalContext().getRequest();
		questionId = Integer.parseInt(request.getParameter("quid"));

		try {
			userId = Integer.parseInt(request.getParameter("useid"));
		} catch (NumberFormatException e) {
			FacesMessage msg = new FacesMessage("You Must Login To Answer");
			msg.setSeverity(FacesMessage.SEVERITY_WARN);
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}

		System.out.println("USER ID :" + userId + " question ID " + questionId);

		User user = uEjb.findUserById(userId);
		Question quest = qEjb.findQuestionById(questionId);

		if (user != null && quest != null) {
			System.out.println("USER :" + user.getEmail() + " "
					+ user.getUsername() + " question "
					+ quest.getDescription());
			List<User> uss = new ArrayList<>();
			uss.add(user);
			users = uss;
			List<Question> qqq = new ArrayList<Question>();
			qqq.add(quest);
			allQuestions = qqq;
			Answer ans = new Answer();
			ans.setDescription(description);
			ans.setQuestionId(questionId);
			ans.setUserId(userId);
			ejb.saveAnswer(ans);
			System.out.println("ANSWERS IS : " + ans.getDescription() + " WAS "
					+ description);

			return "/resources/snippets/user.xhtml?faces-redirect=true";
		}

		return null;
	}

	// /////getters and setters///////

	public int getAnswerId() {
		return answerId;
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

	public boolean isValid() {
		return valid;
	}

	public void setValid(boolean valid) {
		this.valid = valid;
	}

	public String getVideoUrl() {
		return videoUrl;
	}

	public void setVideoUrl(String videoUrl) {
		this.videoUrl = videoUrl;
	}

	public List<Expert> getAllExperts() {
		allExperts = eEjb.allExperts();
		return allExperts;
	}

	public void setAllExperts(List<Expert> allExperts) {
		this.allExperts = allExperts;
	}

	public List<Question> getAllQuestions() {
		return allQuestions;
	}

	public void setAllQuestions(List<Question> allQuestions) {
		this.allQuestions = allQuestions;
	}

	public List<Answer> getAllAnswers() {
		return allAnswers;
	}

	public void setAllAnswers(List<Answer> allAnswers) {
		this.allAnswers = allAnswers;

	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	public List<User> getUsers() {
		return users;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserMail() {
		return userMail;
	}

	public void setUserMail(String userMail) {
		this.userMail = userMail;
	}

	public String getUserPass() {
		return userPass;
	}

	public void setUserPass(String userPass) {
		this.userPass = userPass;
	}

	public int getQuestionId() {
		return questionId;
	}

	public void setQuestionId(int questionId) {
		this.questionId = questionId;
	}

}

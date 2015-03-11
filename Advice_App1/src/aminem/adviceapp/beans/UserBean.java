package aminem.adviceapp.beans;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import aminem.adviceapp.lookUpServices.AnswerEjb;
import aminem.adviceapp.lookUpServices.QuestionEjb;
import aminem.adviceapp.lookUpServices.UserEjb;
import aminem.adviceapp.models.Answer;
import aminem.adviceapp.models.Question;
import aminem.adviceapp.models.User;
import aminem.adviceapp.tools.Utilities;

@ManagedBean
@RequestScoped
public class UserBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	UserEjb ejb;
	@Inject
	QuestionEjb qEjb;

	@Inject
	AnswerEjb aEJb;

	User user;

	List<User> allUsers;
	private int userId;
	private String email;
	private List<Question> questions;
	private List<Answer> answers;
	private String username;
	private String picUrl;
	private String password;
	private boolean loggedIn = false;
	private boolean userEditable;

	public UserBean() {
		initialize();
	}

	public List<Question> usersQuestions(int userId) {
		List<Question> ques = new ArrayList<Question>();
		for (User user : ejb.allUsers()) {
			if (user.getUserId() == userId) {
				for (Question q : qEjb.allQuestions()) {
					if (q.getUserId() == userId) {
						ques.add(q);
					}
				}
			}
		}
		return ques;
	}

	public User singleUser(int userId) {
		return ejb.findUserById(userId);
	}

	public List<Answer> usersAnswers(int userId) {
		List<Answer> ans = new ArrayList<Answer>();
		for (User user : ejb.allUsers()) {
			if (user.getUserId() == userId) {
				for (Answer an : aEJb.allAnswers()) {
					if (an.getUserId() == userId) {
						ans.add(an);
					}
				}
			}
		}

		return ans;
	}

	public String signinUser() {
		user = new User();

		username = email.substring(0, email.indexOf("@"));
		user.setUsername(username);
		user.setPassword(password);
		user.setEmail(email);
		User returnedUser = ejb.loginUser(user);
		if (returnedUser == null) {
			Utilities.sendErrorMessage("Wrong Password",
					"U Gave a wrong Password try again");
			return null;
		}

		Utilities.setUserCookies(returnedUser);
		loggedIn = true;

		return "/resources/snippets/user.xhtml?faces-redirect=true";

	}

	public void signOut(ActionEvent e) {

		loggedIn = false;
		HttpServletRequest req = (HttpServletRequest) FacesContext
				.getCurrentInstance().getExternalContext().getRequest();
		if (!req.getSession().isNew()) {
			Cookie[] cc = req.getCookies();
			for (Cookie c : cc) {
				c.setMaxAge(0);
			}

			req.getSession().invalidate();

			try {
				FacesContext.getCurrentInstance().getExternalContext()
						.redirect("https://www.google.com");
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}

	}

	public String upadateUser() {
		getuserIdFromRequest();
		User u = ejb.updateUsername(userId, username);
		if (u != null) {
			Utilities.setUserCookies(u);
			initialize();
			return "/resources/snippets/user.xhtml?faces-redirect=true";
		}
		Utilities.sendErrorMessage("Soory Cant Change Username",
				"You are Not logged in, login to user this");
		return null;
	}

	private void getuserIdFromRequest() {
		try {
			HttpServletRequest request = (HttpServletRequest) FacesContext
					.getCurrentInstance().getExternalContext().getRequest();
			userId = Integer.parseInt(request.getParameter("userSentId"));
		} catch (NumberFormatException e) {
			Utilities.sendErrorMessage("Not Logged In",
					"You Must Login To Update your Info");

		}
	}

	public void initialize() {
		FacesContext con = FacesContext.getCurrentInstance();
		HttpServletRequest req = (HttpServletRequest) con.getExternalContext()
				.getRequest();
		if (!req.getSession().isNew()) {
			Cookie[] kk = req.getCookies();
			for (int i = 0; i < kk.length; i++) {
				if (kk[i].getName().equals("AppUsername")) {
					username = kk[i].getValue();
					System.out.println("COOOKIIIIEE USERNAME "
							+ kk[i].getName() + " : " + kk[i].getValue());
				}
				if (kk[i].getName().equals("AppEmail")) {
					email = kk[i].getValue();
					System.out.println("COOOKIIIIEE EMAIL" + kk[i].getName()
							+ " : " + kk[i].getValue());
				}
				if (kk[i].getName().equals("AppPassword")) {
					password = kk[i].getValue();
					System.out.println("COOOKIIIIEE PASSWORD "
							+ kk[i].getName() + " : " + kk[i].getValue());
				}
			}
		}
	}

	// /////////getters and setters////////

	public int getUserId() {
		return this.userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPicUrl() {
		return this.picUrl;
	}

	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public List<Answer> getAnswers() {

		return this.answers;
	}

	public void setAnswers(List<Answer> answers) {
		this.answers = answers;
	}

	public List<Question> getQuestions() {
		return this.questions;
	}

	public void setQuestions(List<Question> questions) {
		this.questions = questions;
	}

	String authMessage = "";

	public String getAuthMessage() {
		return authMessage;
	}

	public void setAuthMessage(String authMessage) {
		this.authMessage = authMessage;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public User getUser() {
		user = new User();
		return user;
	}

	public List<User> getAllUsers() {
		allUsers = ejb.allUsers();
		return allUsers;
	}

	public void setAllUsers(List<User> allUsers) {
		this.allUsers = allUsers;
	}

	public boolean isLoggedIn() {

		return loggedIn;
	}

	public void setLoggedIn(boolean loggedIn) {
		this.loggedIn = loggedIn;
	}

	public boolean isUserEditable() {
		return userEditable;
	}

	public void setUserEditable(boolean userEditable) {
		this.userEditable = userEditable;
	}

}

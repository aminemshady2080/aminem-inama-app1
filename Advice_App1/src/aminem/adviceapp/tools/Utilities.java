package aminem.adviceapp.tools;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import aminem.adviceapp.models.User;

public class Utilities {

	
	public static void setUserCookies(User returnedUser) {
		FacesContext
				.getCurrentInstance()
				.getExternalContext()
				.addResponseCookie("AppUsername", returnedUser.getUsername(), null);
		FacesContext.getCurrentInstance().getExternalContext()
				.addResponseCookie("AppEmail", returnedUser.getEmail(), null);
		FacesContext
				.getCurrentInstance()
				.getExternalContext()
				.addResponseCookie("AppPassword", returnedUser.getPassword(), null);
		FacesContext.getCurrentInstance().getExternalContext()
				.addResponseCookie("AppUserId", "" + returnedUser.getUserId(), null);
	}
	public static void sendErrorMessage(String summary,String details) {
		FacesContext con=FacesContext.getCurrentInstance();
		FacesMessage msg=new FacesMessage();
		msg.setDetail(details);
		msg.setSummary(summary);
		msg.setSeverity(FacesMessage.SEVERITY_ERROR);
		con.addMessage(null,msg);
	}
	public static void sendSuccessMessage(String summary,String details) {
		FacesContext con=FacesContext.getCurrentInstance();
		FacesMessage msg=new FacesMessage();
		msg.setDetail(details);
		msg.setSummary(summary);
		msg.setSeverity(FacesMessage.SEVERITY_INFO);
		con.addMessage(null,msg);
	}

}

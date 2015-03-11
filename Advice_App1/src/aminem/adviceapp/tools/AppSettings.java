package aminem.adviceapp.tools;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;

@ManagedBean
@SessionScoped
public class AppSettings implements Serializable {

	private static final long serialVersionUID = 1L;

	private static final Locale KINYARWANDA = new Locale("rw");
	private static final Locale ENGLISH = new Locale("en");
	private Locale locale = new Locale("en");
	private boolean englOk = true;
	private boolean rwOk;
	private boolean englishOk = true;
	private String localeName = "ENGLISH";
	Map<String, String> fonts = new LinkedHashMap<String, String>();
	String font;
	List<String> webImages=new ArrayList<String>();

	public AppSettings() {
		fonts.put("Normal Text", "normal");
		fonts.put("Big/Bold Text", "bold");
		fonts.put("Bigger Text", "bolder");

	}

	// /////////languages settings
	public Locale getLocale() {
		return locale;
	}

	public void setLocale(Locale locale) {
		this.locale = locale;
	}

	public String getLocaleName() {
		return (localeName);
	}

	public void setLocaleName(String localeName) {
		this.localeName = localeName;
	}

	public void changeLang(ActionEvent e) {
		englOk = !englOk;
		if (englOk) {
			localeName = "ENGLISH";
			locale = ENGLISH;
			englishOk = true;
			rwOk = false;
		} else {
			localeName = "KINYARWANDA";
			locale = KINYARWANDA;
			englishOk = false;
			rwOk = true;
		}
	}

	public boolean isEnglishOk() {
		return englishOk;
	}

	public void setEnglishOk(boolean englishOk) {
		this.englishOk = englishOk;
	}

	public boolean isRwOk() {
		return rwOk;
	}

	public void setRwOk(boolean rwOk) {
		this.rwOk = rwOk;
	}

	// //////////////fonts settings
	public String getFont() {
		return font;
	}

	public void setFont(String font) {
		this.font = font;
	}

	public Map<String, String> getFonts() {
		return fonts;
	}

	public void setFonts(Map<String, String> fonts) {
		this.fonts = fonts;
	}

	public List<String> getWebImages() {
		
		webImages.add("icon.png");
		webImages.add("inama.png");
		webImages.add("admin-pic.png");
		return webImages;
	}

	public void setWebImages(List<String> webImages) {
		this.webImages = webImages;
	}
}

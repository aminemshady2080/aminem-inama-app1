package aminem.adviceapp.models;

import java.io.Serializable;

import javax.persistence.*;


/**
 * The persistent class for the modulator database table.
 * 
 */
@Entity
@NamedQuery(name="Modulator.findAll", query="SELECT m FROM Modulator m")
public class Modulator implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="modulator_id")
	private int modulatorId;
	@Column(name="ACTIVE")
	private boolean active;
	@Column(name="EMAIL")
	private String email;

	@Lob
	@Column(name="image_url")
	private String imageUrl;

	@Column(name="PASSWORD")
	private String password;
	@Column(name="USERNAME")
	private String username;

	public Modulator() {
	}

	public int getModulatorId() {
		return this.modulatorId;
	}

	public void setModulatorId(int modulatorId) {
		this.modulatorId = modulatorId;
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

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

}
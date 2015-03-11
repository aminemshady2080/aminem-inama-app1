package aminem.adviceapp.models;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the blogpost database table.
 * 
 */
@Entity
@NamedQuery(name="Blogpost.findAll", query="SELECT b FROM Blogpost b")
public class Blogpost implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="post_id")
	private int postId;

	@Lob
	@Column(name="blogpost_description")
	private String description;

	@Column(name="blogpost_title")
	private String title;

	@Column(name="category_id")
	private int categoryId;

	@Column(name="posted_by")
	private int postedBy;

	@Temporal(TemporalType.DATE)
	@Column(name="posted_time")
	private Date postedTime;

	@Lob
	@Column(name="video_url")
	private String videoUrl;

	public Blogpost() {
	}

	public int getPostId() {
		return this.postId;
	}

	public void setPostId(int postId) {
		this.postId = postId;
	}

	public String getBlogpostDescription() {
		return this.description;
	}

	public void setBlogpostDescription(String blogpostDescription) {
		this.description = blogpostDescription;
	}

	public String getBlogpostTitle() {
		return this.title;
	}

	public void setBlogpostTitle(String blogpostTitle) {
		this.title = blogpostTitle;
	}




	public Date getPostedTime() {
		return this.postedTime;
	}

	public void setPostedTime(Date postedTime) {
		this.postedTime = postedTime;
	}

	public String getVideoUrl() {
		return this.videoUrl;
	}

	public void setVideoUrl(String videoUrl) {
		this.videoUrl = videoUrl;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	public int getPostedBy() {
		return postedBy;
	}

	public void setPostedBy(int postedBy) {
		this.postedBy = postedBy;
	}

	
	
	
}
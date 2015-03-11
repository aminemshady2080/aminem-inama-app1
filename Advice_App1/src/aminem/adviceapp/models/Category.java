package aminem.adviceapp.models;

import java.io.Serializable;

import javax.persistence.*;

import java.util.List;
import java.util.Set;


/**
 * The persistent class for the category database table.
 * 
 */
@Entity
@NamedQuery(name="Category.findAll", query="SELECT c FROM Category c")
public class Category implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="category_id")
	private int categoryId;

	@Column(name="DESCRIPTION_ENG")
	private String descriptionEng;

	@Column(name="DESCRIPTION_RW")
	private String descriptionRw;

	@Lob
	@Column(name="image_url")
	private String imageUrl;

	@Column(name="TITLE_ENG")
	private String titleEng;

	@Column(name="TITLE_RW")
	private String titleRw;

	//bi-directional many-to-many association to Expert
	@ManyToMany(fetch=FetchType.EAGER)
	@JoinTable(
		name="category_expert"
		, joinColumns={
			@JoinColumn(name="category_id")
			}
		, inverseJoinColumns={
			@JoinColumn(name="expert_id")
			}
		)
	private Set<Expert> experts;

	
	//bi-directional many-to-one association to Subcategory
	@OneToMany(mappedBy="category", fetch=FetchType.EAGER)
	private List<Subcategory> subcategories;

	public Category() {
	}

	public int getCategoryId() {
		return this.categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	public String getDescriptionEng() {
		return this.descriptionEng;
	}

	public void setDescriptionEng(String descriptionEng) {
		this.descriptionEng = descriptionEng;
	}

	public String getDescriptionRw() {
		return this.descriptionRw;
	}

	public void setDescriptionRw(String descriptionRw) {
		this.descriptionRw = descriptionRw;
	}

	public String getImageUrl() {
		return this.imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public String getTitleEng() {
		return this.titleEng;
	}

	public void setTitleEng(String titleEng) {
		this.titleEng = titleEng;
	}

	public String getTitleRw() {
		return this.titleRw;
	}

	public void setTitleRw(String titleRw) {
		this.titleRw = titleRw;
	}

	public Set<Expert> getexperts() {
		return this.experts;
	}

	public void setexperts(Set<Expert> experts) {
		this.experts = experts;
	}


	public List<Subcategory> getSubcategories() {
		return this.subcategories;
	}

	public void setSubcategories(List<Subcategory> subcategories) {
		this.subcategories = subcategories;
	}

	public Subcategory addSubcategory(Subcategory subcategory) {
		getSubcategories().add(subcategory);
		subcategory.setCategory(this);

		return subcategory;
	}

	public Subcategory removeSubcategory(Subcategory subcategory) {
		getSubcategories().remove(subcategory);
		subcategory.setCategory(null);

		return subcategory;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Category [categoryId=");
		builder.append(categoryId);
		builder.append(",\n descriptionEng=");
		builder.append(descriptionEng);
		builder.append(",\n descriptionRw=");
		builder.append(descriptionRw);
		builder.append(",\n imageUrl=");
		builder.append(imageUrl);
		builder.append(",\n titleEng=");
		builder.append(titleEng);
		builder.append(",\n titleRw=");
		builder.append(titleRw);
		builder.append(",\n experts=");
		builder.append(experts);
		builder.append(",\n subcategories=");
		builder.append(subcategories);
		builder.append("]\n\n");
		return builder.toString();
	}
	
	

}
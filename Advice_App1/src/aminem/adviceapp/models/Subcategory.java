package aminem.adviceapp.models;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the subcategory database table.
 * 
 */
@Entity
@NamedQuery(name="Subcategory.findAll", query="SELECT s FROM Subcategory s")
public class Subcategory implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="sub_category_id")
	private int subCategoryId;

	@Column(name="DESCRIPTION_ENG")
	private String descriptionEng;

	@Column(name="DESCRIPTION_RW")
	private String descriptionRw;

	@Column(name="TITLE_ENG")
	private String titleEng;

	@Column(name="TITLE_RW")
	private String titleRw;

	//bi-directional many-to-one association to Category
	@ManyToOne
	@JoinColumn(name="category_id")
	private Category category;

	public Subcategory() {
	}

	public int getSubCategoryId() {
		return this.subCategoryId;
	}

	public void setSubCategoryId(int subCategoryId) {
		this.subCategoryId = subCategoryId;
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

	public Category getCategory() {
		return this.category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

}
package aminem.adviceapp.beans;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import aminem.adviceapp.lookUpServices.SubCategoryEJB;
import aminem.adviceapp.models.Category;
import aminem.adviceapp.models.Subcategory;

@Named
@ManagedBean(name = "subcat")
@RequestScoped
public class SubCategoryBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	SubCategoryEJB ejbSubCat;

	private int SubcategoryId;
	private String description;
	private String title;
	private List<Subcategory> subCategories;

	Category catgery;

	private int categoryId;

	public int getSubcategoryId() {
		return SubcategoryId;
	}

	public void setSubcategoryId(int SubcategoryId) {
		this.SubcategoryId = SubcategoryId;
	}

	public int getCategoryId() {
		return categoryId;
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

	public void setCatgery(Category catgery) {
		this.catgery = catgery;
	}

	public Category getCatgery() {
		return catgery;
	}

	// ////////// business //////////////

	public void setSubCategories(List<Subcategory> subcategories) {
		this.subCategories = subcategories;
	}

	public List<Subcategory> getSubCategories(int catId) {
		subCategories=ejbSubCat.allAssociatedSubCategories(catId);
		return subCategories;
	}

	public List<Subcategory> findAllSubCategories(int catId) {
		return ejbSubCat.allAssociatedSubCategories(catId);
	}

}

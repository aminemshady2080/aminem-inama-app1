package aminem.adviceapp.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.inject.Inject;

import aminem.adviceapp.lookUpServices.AnswerEjb;
import aminem.adviceapp.lookUpServices.CategoryEJB;
import aminem.adviceapp.lookUpServices.QuestionEjb;
import aminem.adviceapp.models.Answer;
import aminem.adviceapp.models.Category;
import aminem.adviceapp.models.Expert;
import aminem.adviceapp.models.Question;
import aminem.adviceapp.models.Subcategory;

@ManagedBean(name = "cat")
@RequestScoped
public class CategoryBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	CategoryEJB ejb;
	@Inject
	QuestionEjb qEjb;
	@Inject AnswerEjb aejb; 

	private int categoryId;
	private String description;
	private String imageUrl;
	private String title;
	private List<Expert> experts;
	private List<Subcategory> subcategories;
	Subcategory subCat;
	Category category;

	private List<Category> allCategories;

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

	public String getImageUrl() {
		return this.imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public List<Expert> getExperts() {
		return this.experts;
	}

	public void setExperts(List<Expert> experts) {
		this.experts = experts;
	}

	public Subcategory getSubCat() {
		return subCat;
	}

	public void setSubCat(Subcategory subCat) {
		this.subCat = subCat;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public List<Category> getAllCategories() {
		allCategories = ejb.allCategories();
		return allCategories;
	}

	public void setAllCategories(List<Category> allCategories) {
		this.allCategories = allCategories;
	}

	public void setSubcategories(List<Subcategory> subcategories) {
		this.subcategories = subcategories;
	}

	public List<Subcategory> getSubcategories() {

		return subcategories;
	}

	// ////////// business //////////////
	// ------final method to retrieve all related sub categories------//

	public Map<Category, List<Subcategory>> thesubs(int catID) {
		Map<Category, List<Subcategory>> map = new HashMap<>();
		for (Category cat : allCategories) {
			map.put(cat, cat.getSubcategories());
		}
		return map;
	}

	public List<Subcategory> thesubss(int catID) {
		List<Subcategory> map = new ArrayList<>();
		for (Category cat : allCategories) {
			if (cat.getCategoryId() == catID) {
				for (Subcategory su : cat.getSubcategories()) {
					map.add(su);
				}
			}
		}
		return map;
	}

	public List<Question> theQuests(int catID) {
		List<Question> map = new ArrayList<>();
		for (Category cat : allCategories) {
			if (cat.getCategoryId() == catID) {
				for (Question que : qEjb.allQuestions()) {
					List<Question> qq = new ArrayList<>();
					if (que.getCategoryId() == cat.getCategoryId()) {
						qq.add(que);
						map.add(que);
						// System.out.println("qeustion "
						// + map.iterator().next().getTitle());
					}
				}
			}
		}
		return map;
	}

	public List<Answer> findAnswersForQuestions(int questionId) {
			List<Answer> ans = new ArrayList<Answer>();
			for (Question quest : qEjb.allQuestions()) {
				if (quest.getQuestionId() == questionId) {
					for(Answer an:aejb.allAnswers()){
						if(an.getQuestionId()==questionId){
							ans.add(an);
						}
					}
				}
			}
			return ans;
		
	}

	public Map<Integer, String> categoryMapRw() {
		Map<Integer, String> map = new HashMap<>();
		for (Category cat : ejb.allCategories()) {
			map.put(cat.getCategoryId(), cat.getTitleRw());
		}
		return map;
	}

	public Map<Integer, String> categoryMapEng() {
		Map<Integer, String> map = new HashMap<>();
		for (Category cat : ejb.allCategories()) {
			map.put(cat.getCategoryId(), cat.getTitleEng());
		}
		return map;
	}

}

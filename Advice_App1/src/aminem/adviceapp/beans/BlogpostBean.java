package aminem.adviceapp.beans;

import java.util.List;

import javax.faces.bean.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import aminem.adviceapp.lookUpServices.BlogEjb;
import aminem.adviceapp.lookUpServices.ExpertEjb;
import aminem.adviceapp.models.Blogpost;
import aminem.adviceapp.models.Expert;

@Named
@RequestScoped
public class BlogpostBean {

	@Inject
	BlogEjb ejb;
	
	@Inject ExpertEjb exEjb;

	Blogpost post;
	List<Blogpost> allPosts;

	Expert expert;

	public List<Blogpost> getAllPosts() {
		allPosts = ejb.findAll();
		return allPosts;
	}

	public void setAllPosts(List<Blogpost> allPosts) {
		this.allPosts = allPosts;
	}

	public Expert getExpert() {
		
		return expert;
	}

	public void setExpert(Expert expert) {
		this.expert = expert;
	}

	public Blogpost getPost() {
		return post;
	}

	public void setPost(Blogpost post) {
		this.post = post;
	}
	
	public Expert expertOfBlog(int postId){
		Expert expat=new Expert();
		for(Blogpost post:ejb.findAll()){
			if(post.getPostId()==postId){
				for(Expert exp: exEjb.allExperts()){
					if(exp.getExpertId()==post.getPostedBy()){
						expat= exp;
					}
				}
			}
		}
		return expat;
	}


}

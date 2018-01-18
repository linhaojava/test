package cn.tedu.cloudnote.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Post implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3052811227415870604L;
	private Integer id;
	private String title;
	/**
	 * 发帖人
	 */
	private Person person;
	/**
	 * 当前帖子收到的回复
	 */
	private List<Comment> comments = new ArrayList<Comment>();

	public Post(Integer id, String title, Person person, List<Comment> comments) {
		super();
		this.id = id;
		this.title = title;
		this.person = person;
		this.comments = comments;
	}

	public Post() {
	}

	@Override
	public String toString() {
		return "Post [id=" + id + ", title=" + title + ", person=" + person + ", comments=" + comments + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Post other = (Post) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

}

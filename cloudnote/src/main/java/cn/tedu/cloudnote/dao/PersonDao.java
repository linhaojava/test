package cn.tedu.cloudnote.dao;

import org.springframework.stereotype.Repository;

import cn.tedu.cloudnote.entity.Person;
import cn.tedu.cloudnote.entity.Post;

@Repository("personDao")
public interface PersonDao {
	int addPerson(Person person);

	Post findPostById(int id);
}

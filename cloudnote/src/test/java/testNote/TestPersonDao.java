package testNote;

import org.junit.Test;

import cn.tedu.cloudnote.entity.Person;
import cn.tedu.cloudnote.entity.Post;

public class TestPersonDao extends BaseTestNote {
	@Test
	public void test1() {
		Person p = new Person();
		p.setName("Rose12");
		int n = personDao.addPerson(p);
		System.out.println(n);
		System.out.println(p);
	}

	@Test
	public void test2() {
		Post post = personDao.findPostById(1);
		System.out.println(post);
	}
}
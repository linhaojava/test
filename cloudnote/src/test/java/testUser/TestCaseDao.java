package testUser;

import org.junit.Test;

import cn.tedu.cloudnote.entity.User;

public class TestCaseDao extends TestBase {
	@Test
	public void test1() {
		System.out.println(dao.findByName("wsf"));
	}

	@Test
	public void test2() {
		User user = new User();
		user.setId("123");
		user.setName("Rose");
		user.setPassword("123456");
		user.setToken(null);
		user.setNick(null);
		dao.addUser(user);
	}

	@Test
	public void test3() {
		System.out.println(dao.findUserById("03590914-a934-4da9-ba4d-b41799f917d1"));
	}

	@Test
	public void test4() {
		User user = new User();
		user.setId("12");
		user.setPassword("654321");
		user.setToken(null);
		user.setNick(null);
		dao.changeUserPass(user);
	}
}

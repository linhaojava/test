package testUser;

import org.junit.Before;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.tedu.cloudnote.dao.UserDao;
import cn.tedu.cloudnote.service.UserService;

public class TestBase {

	protected UserDao dao;
	protected UserService userService;

	public TestBase() {
		super();
	}

	@Before
	public void init() {
		ApplicationContext ac = new ClassPathXmlApplicationContext("conf/spring-*.xml");
		dao = ac.getBean("userDao", UserDao.class);
		userService = ac.getBean("userService", UserService.class);
	}

}
package testNoteBook;

import org.junit.Before;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.tedu.cloudnote.dao.NoteBookDao;
import cn.tedu.cloudnote.service.NoteBookService;

public class TestBase {

	protected NoteBookDao noteBookDao;
	protected NoteBookService noteBookService;

	public TestBase() {
		super();
	}

	@Before
	public void init() {
		ApplicationContext ac = new ClassPathXmlApplicationContext("conf/spring-*.xml");
		noteBookDao = ac.getBean("noteBookDao", NoteBookDao.class);
		noteBookService = ac.getBean("noteBookService", NoteBookService.class);
	}

}
package testNote;

import org.junit.Before;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.tedu.cloudnote.dao.NoteDao;
import cn.tedu.cloudnote.dao.PersonDao;
import cn.tedu.cloudnote.service.NoteService;

public class BaseTestNote {
	protected NoteDao noteDao;
	protected NoteService noteService;
	protected PersonDao personDao;

	@Before
	public void init() {
		ApplicationContext ac = new ClassPathXmlApplicationContext("conf/spring-*.xml");
		noteDao = ac.getBean("noteDao", NoteDao.class);
		noteService = ac.getBean("noteService", NoteService.class);
		personDao = ac.getBean("personDao", PersonDao.class);
	}

}
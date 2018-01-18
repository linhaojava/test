package testNoteBook;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.junit.Test;

import com.sun.jmx.snmp.Timestamp;

import cn.tedu.cloudnote.entity.NoteBook;

public class TestNoteBookDao extends TestBase {
	@Test
	public void test1() {
		List<Map<String, Object>> noteBook = noteBookDao.findNoteBookByUserId("03590914-a934-4da9-ba4d-b41799f917d1");
		for (Map<String, Object> map : noteBook) {
			System.out.println(map.keySet());
			System.out.println(map);
			Set<Entry<String, Object>> ent = map.entrySet();
			for (Entry<String, Object> e : ent) {
				System.out.println(e.getKey() + ";" + e.getValue());
			}

		}
		System.out.println(noteBook);
	}

	@Test
	public void test2() {
		System.out.println(noteBookDao.findNoteBookByNoteBookId("049d4cd3-943b-4fc0-97cf-520cd788fe85"));
	}

	@Test
	public void test3() {
		NoteBook noteBook = new NoteBook();
		noteBook.setCn_notebook_id("123456");
		noteBook.setCn_user_id("39295a3d-cc9b-42b4-b206-a2e7fab7e77c");
		noteBook.setCn_notebook_name("hello");
		noteBook.setCn_notebook_createtime(new java.sql.Timestamp(System.currentTimeMillis()));
		noteBookDao.createNoteBook(noteBook);
		System.out.println(noteBook);
	}

	@Test
	public void test4() {
		List<Map<String, Object>> noteBook = noteBookDao.findNoteBookByPage("48595f52-b22c-4485-9244-f4004255b972", 0,
				5);
		for (Map<String, Object> map : noteBook) {
			System.out.println(map);
		}
	}
}

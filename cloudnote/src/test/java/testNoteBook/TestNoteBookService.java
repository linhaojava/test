package testNoteBook;

import java.util.List;
import java.util.Map;

import org.junit.Test;

import cn.tedu.cloudnote.entity.NoteBook;

public class TestNoteBookService extends TestBase {
	@Test
	public void test1() {
		List<Map<String, Object>> noteBooks = noteBookService.listNoteBooks("03590914-a934-4da9-ba4d-b41799f917d1");
		for (Map<String, Object> map : noteBooks) {
			System.out.println(map);
		}
	}

	@Test
	public void test2() {
		NoteBook noteBook = noteBookService.createNoteBook("39295a3d-cc9b-42b4-b206-a2e7fab7e77c", "Rosess");
		System.out.println(noteBook);
	}

	@Test
	public void test3() {
		List<Map<String, Object>> noteBooks = noteBookService.listNoteBooks("48595f52-b22c-4485-9244-f4004255b972", 0);
		for (Map<String, Object> map : noteBooks) {
			System.out.println(map);
		}
	}
}

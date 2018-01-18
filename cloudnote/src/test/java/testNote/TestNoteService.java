package testNote;

import java.util.List;
import java.util.Map;

import org.junit.Test;

import cn.tedu.cloudnote.entity.Note;

public class TestNoteService extends BaseTestNote {
	@Test
	public void test1() {
		List<Map<String, Object>> listNotes = noteService.listNotes("0037215c-09fe-4eaa-aeb5-25a340c6b39b");
		for (Map<String, Object> map : listNotes) {
			System.out.println(map);
		}
	}

	@Test
	public void test2() {
		Note note = noteService.getNote("b4f82f9f-bc0f-480a-b8f2-335164d69945");
		System.out.println(note);
	}

	@Test
	public void test3() {
		Note note = noteService.updateNote("fed920a0-573c-46c8-ae4e-368397846efd", "你好", "不好");
		System.out.println(note);
	}

	@Test
	public void test4() {
		Note note = noteService.createNote("524f7440-7283-4b2d-8af5-4a67570e892e",
				"fa8d3d9d-2de5-4cfe-845f-951041bcc461", "hello");
		System.out.println(note);
	}

	@Test
	public void test5() {
		Note note = noteService.delNoteToCab("416e5f07-cebc-4f58-9231-53b116759f1d", "0");
		System.out.println(note);
	}

	@Test
	public void test6() {
		List<Map<String, Object>> listNotes = noteService.listDelNote("94d42a62-fdbb-4e8d-94a7-bba33f3860bb");
		for (Map<String, Object> map : listNotes) {
			System.out.println(map);
		}
	}

	@Test
	public void test7() {
		noteService.delNote("b9104819-a8d7-4cfe-aeac-e41b04844367 ");
	}

	@Test
	public void test8() {
		List<Map<String, Object>> searchlistNotes = noteService.searchNote("94d42a62-fdbb-4e8d-94a7-bba33f3860bb", "s");
		for (Map<String, Object> map : searchlistNotes) {
			System.out.println(map);
		}
	}

	/**
	 * 利用事物测试批量删除
	 */
	@Test
	public void test9() {
		String id1 = "fsaf-as-df-asdf-as-df-dsa  ";
		String id2 = "22222222222222222222222222222222222";
		String id3 = "8d3763b2-8e01-48d4-a338-730b02ded9c9";
		String id4 = "a200ec50-4111-4785-97b3-539115b61ed5";
		boolean b = noteService.deleteNotes(id1, id2, id3, id4);
		System.out.println(b);
	}
}

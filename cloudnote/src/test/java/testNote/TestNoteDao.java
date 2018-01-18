package testNote;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import cn.tedu.cloudnote.entity.Note;

public class TestNoteDao extends BaseTestNote {
	@Test
	public void test1() {
		List<Map<String, Object>> notes = noteDao.findNoteByNoteBookId("453603a7-e311-4069-9ea2-2cbd062b1c2c");
		for (Map<String, Object> map : notes) {
			System.out.println(map);
			// Set<Entry<String, Object>> set = map.entrySet();
			// for (Entry<String, Object> ent : set) {
			// System.out.println(ent.getKey() + ":" + ent.getValue());
			// }
		}
		System.out.println("OK");
	}

	@Test
	public void test2() {
		Note note = noteDao.findNoteByNoteId("b4f82f9f-bc0f-480a-b8f2-335164d69945");
		System.out.println(note);
	}

	@Test
	public void test3() {
		Note note = new Note("fed920a0-573c-46c8-ae4e-368397846efd", "fa8d3d9d-2de5-4cfe-845f-951041bcc461",
				"524f7440-7283-4b2d-8af5-4a67570e892e", "1", "", "测试笔记——31", "121212", 12, 12);
		System.out.println(note);
		noteDao.updateNote(note);
	}

	@Test
	public void test4() {
		Note note = new Note("fed920a0-573c-46c8-ae4e-368397846e23", "fa8d3d9d-2de5-4cfe-845f-951041bcc461",
				"524f7440-7283-4b2d-8af5-4a67570e892e", "1", "", "测试笔记——31", "121212", System.currentTimeMillis(), 12);
		noteDao.createNote(note);
	}

	@Test
	public void test5() {
		List<Map<String, Object>> notes = noteDao.findDelNote("94d42a62-fdbb-4e8d-94a7-bba33f3860bb");
		for (Map<String, Object> map : notes) {
			System.out.println(map);
		}
	}

	@Test
	public void test6() {
		noteDao.delNote("e8b8b633-9c3b-4a80-a650-e75f47b1ce2d ");
	}

	@Test
	public void test7() {
		Note note = new Note();
		note.setCn_user_id("94d42a62-fdbb-4e8d-94a7-bba33f3860bb");
		note.setCn_note_title("%s%");
		System.out.println(note.getCn_note_title());
		System.out.println(note);
		List<Map<String, Object>> notes = noteDao.searchNote(note);
		for (Map<String, Object> map : notes) {
			System.out.println(map);
		}
	}

	@Test
	public void test8() {
		String id1 = "19fbb55b-0541-433b-a7cd-dba52220a447";
		String id2 = "7c44e29b-0622-43dd-9f50-7103b1461bfe ";
		String id3 = "8530622b-f739-4048-a23f-d226228756b3";
		String id4 = "111";
		int n = noteDao.deleteNotesByid(id1, id2, id3, id4);
		System.out.println(n);
	}

	@Test
	public void test9() {
		Map<String, Object> param = new HashMap<String, Object>();
		// param.put("title", "%ABC%");
		// param.put("body", "%ABC%");
		param.put("key", "%s%");
		List<Map<String, Object>> notes = noteDao.findNoteByParam(param);
		for (Map<String, Object> map : notes) {
			System.out.println(map);
		}
	}

	@Test
	public void test10() {
		List<Map<String, Object>> notes = noteDao.findNoteByParams("%s%", "%s%", "%s%");
		for (Map<String, Object> map : notes) {
			System.out.println(map);
		}
	}
}

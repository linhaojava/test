package cn.tedu.cloudnote.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.tedu.cloudnote.dao.NoteBookDao;
import cn.tedu.cloudnote.dao.NoteDao;
import cn.tedu.cloudnote.dao.UserDao;
import cn.tedu.cloudnote.entity.Note;
import cn.tedu.cloudnote.entity.NoteBook;
import cn.tedu.cloudnote.entity.User;
import cn.tedu.cloudnote.util.NoteUtil;

/**
 * 业务层笔记实现类
 * 
 * @author soft01
 *
 */
@Service("noteService")
@Transactional
public class NoteServiceImpl implements NoteService {
	@Resource(name = "noteDao")
	private NoteDao noteDao;
	@Resource(name = "noteBookDao")
	private NoteBookDao noteBookDao;
	@Resource(name = "userDao")
	private UserDao userDao;

	public List<Map<String, Object>> listNotes(String noteBookId) throws NoteBookNotFoundException {
		// 参数校验
		if (noteBookId == null || noteBookId.trim().isEmpty()) {
			throw new NoteBookNotFoundException("笔记本ID不能为空");
		}
		NoteBook noteBook = noteBookDao.findNoteBookByNoteBookId(noteBookId);
		if (noteBook == null) {
			throw new NoteBookNotFoundException("笔记本不存在");
		}
		return noteDao.findNoteByNoteBookId(noteBookId);
	}

	public Note getNote(String noteId) throws NoteNotFoundException {
		// 参数校验
		if (noteId == null || noteId.trim().isEmpty()) {
			throw new NoteNotFoundException("笔记ID不能为空");
		}
		Note note = noteDao.findNoteByNoteId(noteId);
		return note;
	}

	public Note updateNote(String noteId, String title, String body) throws NoteBookNotFoundException {
		// 参数校验
		if (noteId == null || noteId.trim().isEmpty()) {
			throw new NoteBookNotFoundException("笔记ID不能为空");
		}
		Note note = noteDao.findNoteByNoteId(noteId);
		if (note == null) {
			throw new NoteBookNotFoundException("笔记不存在");
		}
		if (title == null || title.trim().isEmpty()) {
			throw new NoteBookNotFoundException("标题不能为空");
		}
		if (body == null || body.trim().isEmpty()) {
			throw new NoteBookNotFoundException("笔记内容不能为空");
		}
		note = new Note();
		if (title != null && !title.equals(note.getCn_note_title())) {
			note.setCn_note_title(title);
		}
		if (body != null && !body.equals(note.getCn_note_body())) {
			note.setCn_note_body(body);
		}
		note.setCn_note_id(noteId);
		note.setCn_note_last__modify_time(System.currentTimeMillis());
		noteDao.updateNote(note);
		return note;
	}

	/**
	 * 测试事物的传播
	 */
	public void addScore() {
		String a = null;
		a.length();
	}

	public Note createNote(String userId, String noteBookId, String title)
			throws UserNotFoundException, NoteBookNotFoundException {
		// 参数校验
		if (userId == null || userId.trim().isEmpty()) {
			throw new UserNotFoundException("用户ID为空");
		}
		User user = userDao.findUserById(userId);
		if (user == null) {
			throw new UserNotFoundException("用户不存在");
		}
		if (noteBookId == null || noteBookId.trim().isEmpty()) {
			throw new NoteBookNotFoundException("笔记本ID为空");
		}
		NoteBook noteBook = noteBookDao.findNoteBookByNoteBookId(noteBookId);
		if (noteBook == null) {
			throw new NoteBookNotFoundException("笔记本不存在");
		}
		if (title == null || title.trim().isEmpty()) {
			throw new NoteNotFoundException("笔记标题不能为空");
		}
		// 利用用户的操作构建笔记对象
		String noteId = NoteUtil.createId();// 笔记ID
		String statusId = "1";// 1表示不是回收的笔记
		String typeId = null;
		String body = null;
		long lmodTime = 0;
		long createTime = System.currentTimeMillis();// 笔记创建时间
		Note note = new Note(noteId, noteBookId, userId, statusId, typeId, title, body, createTime, lmodTime);
		noteDao.createNote(note);
		//addScore();
		return note;
	}

	public Note delNoteToCab(String noteId, String statusId) throws NoteNotFoundException {
		// 参数校验
		if (noteId == null || noteId.trim().isEmpty()) {
			throw new NoteBookNotFoundException("笔记ID不能为空");
		}
		Note note = noteDao.findNoteByNoteId(noteId);
		if (note == null) {
			throw new NoteBookNotFoundException("笔记不存在");
		}
		note.setCn_note_id(noteId);
		note.setCn_note_status_id(statusId);
		note.setCn_note_last__modify_time(System.currentTimeMillis());
		noteDao.updateNote(note);
		return note;
	}

	public List<Map<String, Object>> listDelNote(String userId) throws UserNotFoundException {
		// 参数校验
		if (userId == null || userId.trim().isEmpty()) {
			throw new UserNotFoundException("用户ID为空");
		}
		User user = userDao.findUserById(userId);
		if (user == null) {
			throw new UserNotFoundException("用户不存在");
		}
		return noteDao.findDelNote(userId);
	}

	public void delNote(String noteId) throws NoteNotFoundException {
		// 参数校验
		if (noteId == null || noteId.trim().isEmpty()) {
			throw new NoteBookNotFoundException("笔记ID不能为空");
		}
		Note note = noteDao.findNoteByNoteId(noteId);
		if (note == null) {
			throw new NoteBookNotFoundException("笔记不存在");
		}
		noteDao.delNote(noteId);
	}

	public Note rollBackNote(String noteId, String statusId) throws NoteNotFoundException {
		// 参数校验
		if (noteId == null || noteId.trim().isEmpty()) {
			throw new NoteBookNotFoundException("笔记ID不能为空");
		}
		Note note = noteDao.findNoteByNoteId(noteId);
		if (note == null) {
			throw new NoteBookNotFoundException("笔记不存在");
		}
		note.setCn_note_id(noteId);
		note.setCn_note_status_id(statusId);
		note.setCn_note_last__modify_time(System.currentTimeMillis());
		noteDao.updateNote(note);
		return note;
	}

	@Transactional // 必须配置事物管理器
	public Note moveNote(String noteId, String notebookId) throws NoteBookNotFoundException, NoteNotFoundException {
		// 参数校验
		if (noteId == null || noteId.trim().isEmpty()) {
			throw new NoteBookNotFoundException("笔记ID不能为空");
		}
		Note note = noteDao.findNoteByNoteId(noteId);
		if (note == null) {
			throw new NoteBookNotFoundException("笔记不存在");
		}
		if (notebookId == null || notebookId.trim().isEmpty()) {
			throw new NoteBookNotFoundException("笔记本ID不能为空");
		}
		NoteBook noteBook = noteBookDao.findNoteBookByNoteBookId(notebookId);
		if (noteBook == null) {
			throw new NoteBookNotFoundException("笔记本不存在");
		}
		note.setCn_note_id(noteId);
		note.setCn_notebook_id(notebookId);
		note.setCn_note_last__modify_time(System.currentTimeMillis());
		noteDao.updateNote(note);
		// 测试springAop动态代理事物处理
		// String a = "";
		// a.charAt(4);
		return note;
	}

	public List<Map<String, Object>> searchNote(String userId, String title)
			throws UserNotFoundException, NoteNotFoundException {
		// 参数校验
		if (userId == null || userId.trim().isEmpty()) {
			throw new UserNotFoundException("用户ID为空");
		}
		User user = userDao.findUserById(userId);
		if (user == null) {
			throw new UserNotFoundException("用户不存在");
		}
		if (title == null || title.trim().isEmpty()) {
			throw new NoteNotFoundException("请输入查询笔记标题");
		}
		// 给like传值用于模糊查询
		title = "%" + title + "%";
		Note note = new Note();
		note.setCn_user_id(userId);
		note.setCn_note_title(title);
		return noteDao.searchNote(note);
	}

	public boolean deleteNotes(String... ids) {
		// for (String id : ids) {
		// int n = noteDao.deleteNoteById(id);
		// if (n != 1) {
		// throw new NoteNotFoundException("ID错误");
		// }
		// }
		// return true;
		int n = noteDao.deleteNotesByid(ids);
		if (n == ids.length) {
			return true;
		} else {
			throw new NoteNotFoundException("ID错误");
		}
	}

}

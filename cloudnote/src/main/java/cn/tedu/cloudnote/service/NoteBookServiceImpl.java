package cn.tedu.cloudnote.service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.tedu.cloudnote.dao.NoteBookDao;
import cn.tedu.cloudnote.dao.UserDao;
import cn.tedu.cloudnote.entity.NoteBook;
import cn.tedu.cloudnote.entity.User;
import cn.tedu.cloudnote.util.NoteUtil;

/**
 * 笔记本业务层接口实现类
 * 
 * @author soft01
 *
 */
@Service("noteBookService")
@Transactional
public class NoteBookServiceImpl implements NoteBookService {
	@Resource(name = "noteBookDao")
	private NoteBookDao noteBookDao;
	@Resource(name = "userDao")
	private UserDao userDao;

	public List<Map<String, Object>> listNoteBooks(String userId) throws UserNotFoundException {
		// 参数校验
		if (userId == null || userId.trim().isEmpty()) {
			throw new UserNotFoundException("Id不能为空");
		}
		User user = userDao.findUserById(userId);
		if (user == null) {
			throw new UserNotFoundException("Id不存在");
		}
		return noteBookDao.findNoteBookByUserId(userId);
	}

	public NoteBook createNoteBook(String userId, String noteBookName)
			throws UserNotFoundException, NoteBookNotFoundException {
		if (userId == null || userId.trim().isEmpty()) {
			throw new UserNotFoundException("用户ID不能为空");
		}
		User user = userDao.findUserById(userId);
		if (user == null) {
			throw new UserNotFoundException("用户不存在");
		}
		if (noteBookName == null || noteBookName.trim().isEmpty()) {
			throw new NoteBookNotFoundException("请输入笔记本名称");
		}
		NoteBook noteBook = new NoteBook();
		String id = NoteUtil.createId();
		noteBook.setCn_notebook_id(id);
		noteBook.setCn_notebook_name(noteBookName);
		noteBook.setCn_user_id(userId);
		noteBook.setCn_notebook_createtime(new Timestamp(System.currentTimeMillis()));
		noteBookDao.createNoteBook(noteBook);
		return noteBook;
	}

	public List<Map<String, Object>> listNoteBooks(String userId, int page) throws UserNotFoundException {
		// 参数校验
		if (userId == null || userId.trim().isEmpty()) {
			throw new UserNotFoundException("Id不能为空");
		}
		User user = userDao.findUserById(userId);
		if (user == null) {
			throw new UserNotFoundException("Id不存在");
		}
		// 分页计算
		int size = 3;
		int start = size * page;

		return noteBookDao.findNoteBookByPage(userId, start, size);
	}

}

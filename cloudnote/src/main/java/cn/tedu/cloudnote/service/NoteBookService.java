package cn.tedu.cloudnote.service;

import java.util.List;
import java.util.Map;

import cn.tedu.cloudnote.entity.NoteBook;

/**
 * 笔记本业务层接口
 * 
 * @author soft01
 *
 */
public interface NoteBookService {
	/**
	 * 根据用户ID查询笔记本列表
	 * 
	 * @param userId
	 *            用户ID
	 * @return 笔记本列表，每个笔记本信息包含ID和name
	 * @throws UserNotFoundException
	 *             用户ID不存在时抛出异常
	 */
	List<Map<String, Object>> listNoteBooks(String userId) throws UserNotFoundException;

	/**
	 * 根据用户操作传入的用户ID 和笔记本名称创建笔记本
	 * 
	 * @return
	 * @throws UserNotFoundException
	 * @throws NoteBookNotFoundException
	 */
	NoteBook createNoteBook(String userId, String noteBookName) throws UserNotFoundException, NoteBookNotFoundException;
	/**
	 * 分页查询
	 * @param userId
	 * @param page 页号时0，1，2，3，4，5，6，7，8，9
	 * @return
	 * @throws UserNotFoundException
	 */

	List<Map<String, Object>> listNoteBooks(String userId, int page) throws UserNotFoundException;
}

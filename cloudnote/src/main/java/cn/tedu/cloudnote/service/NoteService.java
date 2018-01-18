package cn.tedu.cloudnote.service;

import java.util.List;
import java.util.Map;

import cn.tedu.cloudnote.entity.Note;

/**
 * 笔记业务层接口
 * 
 * @author soft01
 *
 */
public interface NoteService {
	/**
	 * 笔记列表
	 * 
	 * @param noteBookId笔记本ID
	 * @return 笔记本中的笔记列表，每个笔记对象包含ID和title两个属性
	 * @throws NoteBookNotFoundException
	 *             笔记本不存在时候抛出异常
	 */
	List<Map<String, Object>> listNotes(String noteBookId) throws NoteBookNotFoundException;

	/**
	 * 根据笔记ID查找对应的笔记信息
	 * 
	 * @param noteId
	 * @return
	 * @throws NoteNotFoundException
	 */
	Note getNote(String noteId) throws NoteNotFoundException;

	/**
	 * 根据用户的操作，将笔记的标题，内容保存
	 * 
	 * @param noteId
	 * @param title
	 * @param body
	 * @throws NoteBookNotFoundException
	 */
	Note updateNote(String noteId, String title, String body) throws NoteBookNotFoundException;

	/**
	 * 根据用户的操作创建一个新笔记
	 * 
	 * @param userId
	 * @param noteBookId
	 * @param title
	 * @return
	 * @throws UserNotFoundException
	 * @throws NoteBookNotFoundException
	 */
	Note createNote(String userId, String noteBookId, String title)
			throws UserNotFoundException, NoteBookNotFoundException, NoteNotFoundException;

	/**
	 * 改变笔记的status_id属性，将笔记移动到回收站
	 * 
	 * @param noteId
	 * @param statusId
	 * @return
	 * @throws NoteNotFoundException
	 */
	Note delNoteToCab(String noteId, String statusId) throws NoteNotFoundException;

	/**
	 * 查找当前用户的回收站笔记信息
	 * 
	 * @param userId
	 * @return
	 * @throws UserNotFoundException
	 */
	List<Map<String, Object>> listDelNote(String userId) throws UserNotFoundException;

	/**
	 * 删除笔记true
	 * 
	 * @param noteId
	 * @throws NoteNotFoundException
	 */
	void delNote(String noteId) throws NoteNotFoundException;

	/**
	 * 改变笔记的statue_id把杀出的笔记回复
	 * 
	 * @throws NoteNotFoundException
	 */
	Note rollBackNote(String noteId, String statusId) throws NoteNotFoundException;

	/**
	 * 改变笔记的笔记本的id用于移动笔记
	 * 
	 * @param noteId
	 * @param notebookId
	 */
	Note moveNote(String noteId, String notebookId) throws NoteBookNotFoundException, NoteNotFoundException;

	/**
	 * 利用模糊查询查找相应的笔记
	 * 
	 * @param noteId
	 * @param title
	 * @return
	 */
	List<Map<String, Object>> searchNote(String userId, String title)
			throws UserNotFoundException, NoteNotFoundException;

	/**
	 * 批量删除笔记 String...变长参数 String[]
	 * ==String...:调用时：如果是String[]参数必须传递数组参数--deleteNotes(new
	 * String[]{"","",""})
	 * String...传递参数时参数可以直接传元素--deleteNotes("","","");或deleteNotes(new
	 * String[]{"","",""}) 注意：String...只能使用最后一个参数位置
	 * 
	 * @param id
	 * @return
	 */
	boolean deleteNotes(String... ids);
}

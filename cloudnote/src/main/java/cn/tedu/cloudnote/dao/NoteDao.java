package cn.tedu.cloudnote.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import cn.tedu.cloudnote.entity.Note;

/**
 * 持久层笔记接口
 * 
 * @author soft01
 *
 */
@Repository("noteDao")
public interface NoteDao {
	/**
	 * 根据笔记本Id查找对应的笔记
	 * 
	 * @param noteBookId
	 * @return
	 */
	List<Map<String, Object>> findNoteByNoteBookId(String noteBookId);

	/**
	 * 根据笔记的ID查找笔记信息
	 * 
	 * @param noteID
	 * @return
	 */
	Note findNoteByNoteId(String noteId);

	/**
	 * 将用户操作后的笔记保存倒数据中
	 * 
	 * @param note
	 * @return
	 */
	void updateNote(Note note);

	/**
	 * 根据用户操作创建笔记
	 * 
	 * @param note
	 */
	void createNote(Note note);

	/**
	 * 找到当前用户的回收站笔记
	 * 
	 * @param userId
	 * @return
	 */
	List<Map<String, Object>> findDelNote(String userId);

	/**
	 * 真的删除笔记
	 * 
	 * @param noteId
	 */
	void delNote(String noteId);

	/**
	 * 利用模糊查询查找相应的笔记
	 * 
	 * @param noteId
	 * @param title
	 * @return
	 */
	List<Map<String, Object>> searchNote(Note note);

	/**
	 * 批量删除笔记
	 * 
	 * @param id
	 * @return
	 */
	int deleteNoteById(String id);

	int deleteNotesByid(String... ids);

	/**
	 * 多参数查询
	 * 
	 * @param param
	 *            可以接受參數title body key
	 * @return
	 */
	List<Map<String, Object>> findNoteByParam(Map<String, Object> param);

	List<Map<String, Object>> findNoteByParams(@Param("title") String title, @Param("body") String body,
			@Param("key") String key);
}

package cn.tedu.cloudnote.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import cn.tedu.cloudnote.entity.NoteBook;

/**
 * 笔记本出持久层接口 登录成功后显示笔记本功能
 * 
 * @author soft01
 *
 */
@Repository("noteBookDao")
public interface NoteBookDao {
	/**
	 * 查询制定用户ID的全部笔记本信息
	 * 
	 * @param String
	 *            userId 用户ID
	 * @return 笔记本信息列表，咩个笔记本信息包含ID和name属性
	 */
	List<Map<String, Object>> findNoteBookByUserId(String userId);

	/**
	 * 根据笔记本ID查找笔记本
	 * 
	 * @param noteBookId
	 * @return
	 */
	NoteBook findNoteBookByNoteBookId(String noteBookId);

	/**
	 * 根据用户操作创建笔记本
	 * 
	 * @param noteBook
	 * @return
	 */
	void createNoteBook(NoteBook noteBook);
	/**
	 * 分页查询方法
	 * @param userId 
	 * @param start 开始位置
	 * @param size 每页显示记录数
	 * @return
	 */
	List<Map<String, Object>> findNoteBookByPage(@Param("userId") String userId, @Param("start") int start,
			@Param("size") int size);
}

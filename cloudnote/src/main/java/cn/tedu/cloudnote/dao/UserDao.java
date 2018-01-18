package cn.tedu.cloudnote.dao;

import org.springframework.stereotype.Repository;

import cn.tedu.cloudnote.entity.User;

/**
 * 持久层用户接口
 * 
 * @author soft01
 *
 */
@Repository("userDao")
public interface UserDao {
	/**
	 * 根据用户名查找用户信息
	 * 
	 * @param name
	 * @return
	 */
	User findByName(String name);

	/**
	 * 添加方法，用于用户注册
	 * 
	 * @param user
	 */
	void addUser(User user);

	/**
	 * 根据用户Id查找用户
	 * 
	 * @param userId
	 * @return
	 */
	User findUserById(String userId);

	/**
	 * 将用户从页面传入的新密码更新到数据库中
	 * 
	 * @param userId
	 * @param newPass
	 */
	void changeUserPass(User user);

}

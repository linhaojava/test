package cn.tedu.cloudnote.service;

import cn.tedu.cloudnote.entity.User;

/**
 * 业务层用户接口
 * 
 * @author soft01
 *
 */
public interface UserService {
	/**
	 * 根据用户名登录
	 * 
	 * @param name
	 *            用户名
	 * @param password
	 *            密码
	 * @return
	 */
	User login(String name, String password) throws NameException, PasswordException;

	/**
	 * 用户注册
	 * 
	 * @param name
	 * @param password
	 * @param nick
	 * @throws NameException
	 */
	User regist(String name, String password, String nick) throws NameException, PasswordException;

	/**
	 * 修改用户密码
	 * @param userId
	 * @param newPass
	 * @return
	 * @throws UserNotFoundException
	 * @throws PasswordException
	 */
	User changePass(String userId, String lPass, String newPass) throws UserNotFoundException, PasswordException;
}

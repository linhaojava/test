package cn.tedu.cloudnote.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.tedu.cloudnote.dao.UserDao;
import cn.tedu.cloudnote.entity.User;
import cn.tedu.cloudnote.util.NoteUtil;

/**
 * 业务层用户接口实现类
 * 
 * @author soft01
 *
 */
@Service("userService")
@Transactional
public class UserServiceImpl implements UserService {
	@Resource(name = "userDao")
	private UserDao userDao;

	public User login(String name, String password) throws NameException, PasswordException {
		System.out.println("调用业务层login");
		// 校验参数
		if (name == null || name.trim().isEmpty()) {
			throw new NameException("用户名不能为空");
		}
		if (password == null || password.trim().isEmpty()) {
			throw new PasswordException("密码不能为空");
		}
		// 调用DAO,根据用户名查找用户
		User user = userDao.findByName(name);
		// 判断结果，比对密码
		if (user == null) {
			throw new NameException("用户名错误");
		}
		// 对请求传如的参数加密，和数据库里面的加密数据比对
		String md5Password = NoteUtil.md5(password);
		if (!(user.getPassword().equals(md5Password))) {
			throw new PasswordException("密码错误");
		}
		return user;
	}

	public User regist(String name, String password, String nick) throws NameException, PasswordException {
		// 参数校验
		if (name == null || name.trim().isEmpty()) {
			throw new NameException("用户名不能为空");
		}
		if (password == null || password.trim().isEmpty()) {
			throw new PasswordException("密码不能为空");
		}
		if (nick == null || nick.trim().isEmpty()) {
			nick = name;
		}
		// 判断用户名是否被占用
		User user = userDao.findByName(name);
		if (user != null) {
			throw new NameException("用户名已注册");
		}
		// 生成ID
		String id = NoteUtil.createId();
		// 用户密码加密
		password = NoteUtil.md5(password);
		// 生成对象
		user = new User(id, name, password, "", nick);
		// 添加到数据库中
		userDao.addUser(user);
		return user;
	}

	public User changePass(String userId, String lPass, String newPass)
			throws UserNotFoundException, PasswordException {
		// 参数校验
		if (userId == null || userId.trim().isEmpty()) {
			throw new UserNotFoundException("用户ID为空");
		}
		User user = userDao.findUserById(userId);
		if (user == null) {
			throw new UserNotFoundException("用户不存在");
		}
		if (lPass == null || lPass.trim().isEmpty()) {
			throw new PasswordException("请输入原密码");
		}
		lPass = NoteUtil.md5(lPass);
		if (!lPass.equals(user.getPassword())) {
			throw new PasswordException("原密码错误");
		}
		if (newPass == null || newPass.trim().isEmpty()) {
			throw new PasswordException("请输入新密码");
		}
		if (newPass.length() < 6) {
			throw new PasswordException("新密码长度太小");
		}
		newPass = NoteUtil.md5(newPass);
		if (newPass.equals(lPass)) {
			throw new PasswordException("不能和原密码相同");
		}
		user.setId(userId);
		user.setPassword(newPass);
		userDao.changeUserPass(user);
		return user;
	}

}

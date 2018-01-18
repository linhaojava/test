package cn.tedu.cloudnote.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.tedu.cloudnote.entity.User;
import cn.tedu.cloudnote.service.UserService;
import cn.tedu.cloudnote.util.JsonResult;

/**
 * 表示层控制器
 * 
 * @author soft01
 *
 */
@Controller
@RequestMapping("/user") // 匹配请求路径
public class UserController extends BaseController {
	@Resource(name = "userService")
	private UserService userService;

	@RequestMapping("/login.do") // 匹配请求
	@ResponseBody
	public Object login(String name, String password, HttpServletRequest req) {
		System.out.println("调用控制器login");
		User user = userService.login(name, password);
		return new JsonResult(user);
	}

	@RequestMapping("/regist.do")
	@ResponseBody
	public JsonResult regist(String name, String nick, String password) {
		User user = userService.regist(name, password, nick);
		return new JsonResult(user);
	}

	@RequestMapping("/changepass.do")
	@ResponseBody
	public JsonResult changePass(String userId, String lpass, String npass) {
		User user = userService.changePass(userId, lpass, npass);
		return new JsonResult(user);
	}
}

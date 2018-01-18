package cn.tedu.cloudnote.controller;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.tedu.cloudnote.service.NameException;
import cn.tedu.cloudnote.service.NoteBookNotFoundException;
import cn.tedu.cloudnote.service.NoteNotFoundException;
import cn.tedu.cloudnote.service.PasswordException;
import cn.tedu.cloudnote.service.UserNotFoundException;
import cn.tedu.cloudnote.util.JsonResult;

/**
 * 表示层用户异常处理类
 * 
 * @author soft01
 *
 */
public abstract class BaseController {
	@ExceptionHandler(Exception.class)
	@ResponseBody
	public JsonResult exceptionhandle(Exception e) {
		if (e instanceof NameException) {
			e.printStackTrace();
			return new JsonResult(2, e);
		}
		if (e instanceof PasswordException) {
			e.printStackTrace();
			return new JsonResult(3, e);
		}
		if (e instanceof UserNotFoundException) {
			e.printStackTrace();
			return new JsonResult(2, e);
		}
		if (e instanceof NoteBookNotFoundException) {
			e.printStackTrace();
			return new JsonResult(2, e);
		}
		if (e instanceof NoteNotFoundException) {
			e.printStackTrace();
			return new JsonResult(2, e);
		}
		e.printStackTrace();
		return new JsonResult(e);
	}

}
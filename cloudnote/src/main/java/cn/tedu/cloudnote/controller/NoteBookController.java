package cn.tedu.cloudnote.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.tedu.cloudnote.entity.NoteBook;
import cn.tedu.cloudnote.service.NoteBookService;
import cn.tedu.cloudnote.util.JsonResult;

/**
 * 笔记本表示层控制器
 * 
 * @author soft01
 *
 */
@Controller
@RequestMapping("/notebook")
public class NoteBookController extends BaseController {
	@Resource(name = "noteBookService")
	private NoteBookService noteBookService;

	@RequestMapping("/list.do")
	@ResponseBody
	public JsonResult listNoteBook(String userId) {
		// 调用业务层noteBookService
		List<Map<String, Object>> noteBooks = noteBookService.listNoteBooks(userId);
		return new JsonResult(noteBooks);
	}

	@RequestMapping("/createnotebook.do")
	@ResponseBody
	public JsonResult createNoteBook(String userId, String noteBookName) {
		NoteBook noteBook = noteBookService.createNoteBook(userId, noteBookName);
		return new JsonResult(noteBook);
	}

	@RequestMapping("/page.do")
	@ResponseBody
	public JsonResult listNoteBook(String userId, int page) {
		// 调用业务层noteBookService
		List<Map<String, Object>> noteBooks = noteBookService.listNoteBooks(userId, page);
		return new JsonResult(noteBooks);
	}

}

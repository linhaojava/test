package cn.tedu.cloudnote.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.tedu.cloudnote.entity.Note;
import cn.tedu.cloudnote.service.NoteService;
import cn.tedu.cloudnote.util.JsonResult;

/**
 * 笔记表示层控制器
 * 
 * @author soft01
 *
 */
@Controller
@RequestMapping("/note")
public class NoteController extends BaseController {
	@Resource(name = "noteService")
	private NoteService noteService;

	@RequestMapping("/list.do")
	@ResponseBody
	public JsonResult ListNote(String noteBookId) {
		List<Map<String, Object>> notes = noteService.listNotes(noteBookId);
		return new JsonResult(notes);
	}

	@RequestMapping("/getNote.do")
	@ResponseBody
	public JsonResult getNote(String noteId) {
		Note note = noteService.getNote(noteId);
		return new JsonResult(note);
	}

	@RequestMapping("/updateNote.do")
	@ResponseBody
	public JsonResult updateNote(String noteId, String title, String body) {
		Note note = noteService.updateNote(noteId, title, body);
		return new JsonResult(note);
	}

	@RequestMapping("/createNote.do")
	@ResponseBody
	public JsonResult createNote(String userId, String noteBookId, String title) {
		Note note = noteService.createNote(userId, noteBookId, title);
		return new JsonResult(note);
	}

	@RequestMapping("/delnotetocab.do")
	@ResponseBody
	public JsonResult delNoteToCab(String noteId, String statusId) {
		Note note = noteService.delNoteToCab(noteId, statusId);
		return new JsonResult(note);
	}

	@RequestMapping("/showdelnote.do")
	@ResponseBody
	public JsonResult findDelNote(String userId) {
		List<Map<String, Object>> listDelNotes = noteService.listDelNote(userId);
		return new JsonResult(listDelNotes);
	}

	@RequestMapping("/delnote.do")
	@ResponseBody
	public JsonResult delNote(String noteId) {
		noteService.delNote(noteId);
		return new JsonResult("");
	}

	@RequestMapping("/noterollback.do")
	@ResponseBody
	public JsonResult rollBackNote(String noteId, String statusId) {
		Note note = noteService.rollBackNote(noteId, statusId);
		return new JsonResult(note);
	}

	@RequestMapping("/movenote.do")
	@ResponseBody
	public JsonResult moveNote(String noteId, String notebookId) {
		Note note = noteService.moveNote(noteId, notebookId);
		return new JsonResult(note);
	}

	@RequestMapping("/searchnote.do")
	@ResponseBody
	public JsonResult searchNote(String userId, String title) {
		List<Map<String, Object>> notes = noteService.searchNote(userId, title);
		return new JsonResult(notes);
	}
}

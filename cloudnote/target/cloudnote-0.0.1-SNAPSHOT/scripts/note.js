$(function() {
	// 给笔记列表的X绑定单击事件，用于删除笔记
	$('#pc_part_2, .note_menu ').on('click', '.btn_delete', deleteNote);
	// 给笔记列表的移动致绑定单击事件
	$('#pc_part_2, .note_menu ').on('click', '.btn_move', loadmoveNote);
	// 给回收站绑定事件对其进行操作
	$('#rollback_button').click(showRollBack);
	// 给回收站的笔记类表绑定事件得到回收笔记信息
	$('#pc_part_4').on('click', 'li', getNotes);
	// 给回收站笔记列表的删除按钮绑定事件，用晕彻底删除
	$('#pc_part_4').on('click', '.btn_delete', deleteNoteTrue);
	// 给回收站笔记列表恢复按钮绑定单击事件，用于恢复删除笔记
	$('#pc_part_4').on('click', '.btn_replay', replayNote);
	// 给移动笔记弹出框中的确定移动按钮绑定单击事件
	$('#can').on('click', '.move_note', moveNote);
	// $('#can').on('change', '#moveSelect', moveNote);
	// 给搜索框绑定键盘回车事件
	$('#search_note').keyup(searchNote);
});
function searchNote(event) {
	if (event.keyCode == 13) {
		var ul = $('#pc_part_4').hide();
		var ul = $('#pc_part_2').show();
		$('#input_note_title').val("");
		$('#myEditor').html("");
		var userId = getCookie('userId');
		var title = $('#search_note').val();
		var url = "note/searchnote.do";
		var data = {
			"userId" : userId,
			"title" : title
		};
		$.getJSON(url, data, function(result) {
			if (result.state == 0) {
				var notes = result.data;
				var ul = $('#pc_part_2 .contacts-list');
				ul.empty();
				for (i = 0; i < notes.length; i++) {
					var note = notes[i];
					var li = noteTemplate
							.replace('[title]', note.cn_note_title);
					li = $(li).data('noteId', note.cn_note_id);
					ul.append(li);
				}
			}
			if (result.state == 1) {
				alert(result.message);
			}
			if (result.state == 2) {
				alert(result.message);
			}

		});
	}

}
function moveNote() {
	var li = $('#pc_part_2 .checked').parent();
	var noteId = li.data('noteId');
	var notebookId = $('#moveSelect').val();
	var url = 'note/movenote.do';
	var data = {
		'noteId' : noteId,
		'notebookId' : notebookId
	};
	$.getJSON(url, data, function(result) {
		if (result.state == 0) {
			li.remove();
			$('#input_note_title').val("");
			$('#myEditor').html("");
			closeAlert();
		}
		if (result.state == 1) {
			alert(result.message);
		}
		if (result.state == 2) {
			alert(result.message);
		}

	});
}
function loadmoveNote() {
	$('#can').load('alert/alert_move.html', function() {
		// 请求notebook/list.do
		var url = 'notebook/list.do';
		var data = {
			'userId' : getCookie('userId')
		};
		$.getJSON(url, data, function(result) {
			if (result.state == 0) {
				var list = result.data;
				// $('#moveSelect').innerHTML = "<option>--请选择--</option>"
				for (var i = 0; i < list.length; i++) {
					var notebook = list[i];
					var option = document.createElement("option");
					option.innerHTML = notebook.name;
					option.value = notebook.id;
					$('#moveSelect').append(option);
				}
			}
			if (result.state == 1) {
				alert(result.message);
			}
			if (result.state == 2) {
				alert(result.message);
			}

		});
	});
}
function replayNote() {
	var a = $(this).parent();
	var cls = a.attr('class');
	if (!cls) {
		return;
	}
	if (cls.indexOf('checked') < 0) {
		return;
	}
	var li = $('#pc_part_4 .checked').parent();
	var noteId = li.data('noteId');
	var canrollBack = confirm("你确定要恢复吗？");
	if (canrollBack) {
		var url = "note/noterollback.do";
		var data = {
			"noteId" : noteId,
			"statusId" : "1"
		};
		$.getJSON(url, data, function(result) {
			if (result.state == 0) {
				li.remove();
			}
			// 系统异常
			if (result.state == 1) {
				alert(result.message);
			}
			// 运行异常
			if (result.state == 2) {
				alert(result.message);
			}
		});
	}
}
function deleteNoteTrue() {
	var a = $(this).parent();
	var cls = a.attr('class');
	if (!cls) {
		return;
	}
	if (cls.indexOf('checked') < 0) {
		return;
	}
	var li = $('#pc_part_4 .checked').parent();
	var noteId = li.data('noteId');
	var canDel = confirm("你确定要删除吗？");
	if (canDel) {
		var url = "note/delnote.do";
		var data = {
			"noteId" : noteId
		};
		$.getJSON(url, data, function(result) {
			if (result.state == 0) {
				li.remove();
			}
			// 系统异常
			if (result.state == 1) {
				alert(result.message);
			}
			// 运行异常
			if (result.state == 2) {
				alert(result.message);
			}
		});
	}

}
/**
 * 删除笔记操作
 */
function deleteNote() {
	$('#can').load('alert/alert_delete_note.html');
	var li = $('#pc_part_2 .checked').parent();
	var noteId = li.data('noteId');
	// 给can下面的div的save_note按钮绑定单击事件，用于创建笔记
	if (noteId) {
		$('#can').on('click', '.delete_note', function() {
			var data = {
				"noteId" : noteId,
				"statusId" : "0"
			};
			var url = "note/delnotetocab.do";
			$.getJSON(url, data, function(result) {
				if (result.state == 0) {
					li.remove();
					closeAlert();
				}
				// 系统异常
				if (result.state == 1) {
					alert(result.message);
				}
				// 运行异常
				if (result.state == 2) {
					alert(result.message);
				}
			});
		});
	}

}
function showRollBack() {
	$('#input_note_title').val("");
	$('#myEditor').html("");
	$('#pc_part_1 .checked').removeClass('checked');
	$('#pc_part_2 .checked').removeClass('checked');
	// 点击回收站先清空笔记列表，将回收站中用户的笔记显示到笔记列表中
	var ul = $('#pc_part_2').hide();
	var userId = getCookie('userId');
	var data = {
		"userId" : userId
	};
	$.getJSON("note/showdelnote.do", data, function(result) {
		if (result.state == 0) {
			var notes = result.data;
			var ul = $('#pc_part_4').show();
			var ul = $('#pc_part_4 .contacts-list');
			ul.empty();
			var li = '';
			for (i = 0; i < notes.length; i++) {
				var note = notes[i];
				li = delNoteTemplate.replace('[title]', note.cn_note_title);
				li = $(li).data('noteId', note.cn_note_id);
				ul.append(li);
			}
		}
		// 系统异常
		if (result.state == 1) {
			alert(result.message);
		}
		// 运行异常
		if (result.state == 2) {
			alert(result.message);
		}
	});
}
var delNoteTemplate = '<li class="disable"><a><i class="fa fa-file-text-o" title="online" rel="tooltip-bottom"></i> [title]'
		+ '<button type="button" class="btn btn-default btn-xs btn_position btn_delete"><i class="fa fa-times"></i></button>'
		+ '<button type="button" class="btn btn-default btn-xs btn_position_2 btn_replay"><i class="fa fa-reply"></i></button></a></li>';
/**
 * 加载edit.html后执行次函数
 */
$(function() {
	// loadNotebooks();
	loadPagedNotebooks();
	// 利用冒泡，绑定li被点击时候执行时间程序
	// 绑定笔记本列表中li元素被点击的事件
	$('#pc_part_1').on('click', 'li.notebook', loadNotes);
	$('#pc_part_1').on('click', 'li.more', loadPagedNotebooks);
	// 绑定笔记列表中的li元素被点击事件
	$('#pc_part_2').on('click', 'li', getNotes);

	// 绑定保存笔记按钮点击事件
	$('#save_note').click(updateNote);
	// 得到创建笔记框
	$('#add_note').click(function() {
		$('#can').load('alert/alert_note.html');
	});
	// 得到创建笔记本框
	$('#add_notebook').click(function() {
		$('#can').load('alert/alert_notebook.html');
	});
	// 给can下面的div的close和cancle按钮绑定单击事件，用于取消弹出框
	$('#can').on('click', '.close,.cancle', closeAlert);
	// 给can下面的div的save_note按钮绑定单击事件，用于创建笔记
	$('#can').on('click', '.save_note', createNote);
	// 给can下面的div的save_note按钮绑定单击事件，用于创建笔记
	$('#can').on('click', '.save_notebook', createNoteBook);
	// 给笔记列表中的按钮绑定事件，弹出选择按钮
	$('#pc_part_2').on('click', '.btn_slide_down', showNoteMenu);
	// 点击文档任何位置都首期笔记子菜单
	$(document).click(closeNoteMenu);
});
function loadPagedNotebooks() {
	// 获取more按钮，如果没有则时第一页
	var li = $(this);
	var page = 0;
	if (li.data('page')) {
		page = li.data('page');
	}
	var url = "notebook/page.do";

	var data = {
		'userId' : getCookie('userId'),
		'page' : page
	}
	$.getJSON(url, data, function(result) {
		if (result.state == 0) {
			// 把数据显示到笔记本列表区域
			var list = result.data;
			showPaged(list, page);

		}
		if (result.state == 1) {
			alert(result.message);
		}
		if (result.state == 2) {
			alert(result.message);
		}
	});
}
function showPaged(list, page) {
	var ul = $('#pc_part_1 .contacts-list');
	if (page == 0) {
		ul.empty();
	} else {
		// 删除more
		ul.find('.more').remove();
	}

	for (i = 0; i < list.length; i++) {
		var notebook = list[i];
		var li = notebookTemplate.replace('[name]', notebook.name);
		li = $(li).data('noteBookId', notebook.id);
		ul.append(li);
	}
	// 在more上榜定page+1表示翻页到下一页
	var more = $(moreTemplate);
	more.data('page', page + 1);
	// 显示more按钮
	if (list.length != 0) {
		ul.append(more);
	} else {
		var li = overliTemplate;
		li = $(li);
		ul.append(li);
	}

}
var moreTemplate = '<li class="online more">' + '<a>'
		+ '<i class="fa fa-plus fa-book" title="online" rel="tooltip-bottom">'
		+ '</i>加载更多</a></li>';
var overliTemplate ='<li class="online"><a>没有更多了</a></li>';
function closeNoteMenu() {
	$('.note_menu').hide();
}
function showNoteMenu() {
	// 只有选定的笔记才可以点击子菜单
	// var a = $(this).parent();
	// var cls = a.attr('class');
	// if (!cls) {
	// return;
	// }
	// if (cls.indexOf('checked') < 0) {
	// return;
	// }
	// 找到子菜单，调用 show()方法
	// this就是点击的按钮对象，利用按钮上下文关系找到子菜单，调用show()方法
	var menu = $(this).parent('.checked').next();
	menu.toggle();
	return false;// 阻止事件冒泡
}
/**
 * 保存笔记
 */
function updateNote() {
	// 用户友好动画效果
	$('#save_note').html('保存......');
	setInterval(function() {
		$('#save_note').html('保存笔记');
	}, 1000);
	// 获取笔记标题输入框中绑定的笔记信息
	var note = $('#input_note_title').data('note');
	var noteId = note.cn_note_id;
	var title = $('#input_note_title').val();
	var body = um.getContent();
	var url = "note/updateNote.do";
	var data = {
		"noteId" : noteId,
		"title" : title,
		"body" : body
	};
	var canmodify = true;
	// 笔记标题不能为空，或者不能和元标题相同
	if (title == '' || title == note.cn_note_title) {
		cnamodify = false;
	}
	if (canmodify) {
		$.post(url, data, function(result) {
			if (result.state == 0) {
				var note = result.data;
				var a = $('#pc_part_2 .checked');
				var i = iTemplate.replace('[title]', note.cn_note_title);
				$(a).html(i);
			}
			if (result.state == 2) {
				alert(result.message);
			}
			if (result.state == 1) {
				alert(result.message);
			}
		});
	}

}
/**
 * 得到笔记信息
 */
function getNotes() {
	$('#input_note_title').val("");
	$('#myEditor').html("");
	var li = $(this);
	var noteId = li.data('noteId');
	li.parent().find('a').removeClass('checked');
	li.find('a').addClass('checked');
	var url = 'note/getNote.do';
	var data = {
		"noteId" : noteId
	};
	if (noteId) {
		$.getJSON(url, data, function(result) {
			if (result.state == 0) {
				var note = result.data;
				$('#input_note_title').val(note.cn_note_title);
				// um.setContent(note.cn_note_body);
				$('#myEditor').html(note.cn_note_body);
				// 将笔记信息绑定到笔记标题输入框上
				$('#input_note_title').data('note', note);
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
/**
 * 加载笔记信息
 */
function loadNotes() {
	var ul = $('#pc_part_4').hide();
	var ul = $('#pc_part_2').show();
	$('#input_note_title').val("");
	$('#myEditor').html("");
	// 在事件方法中this就是当前发生事件的对象li
	var li = $(this);
	// 获取在显示时候绑定到li的笔记本Id
	var noteBookId = li.data("noteBookId");
	console.log(noteBookId);
	// 设置选中高亮效果
	li.parent().find('a').removeClass('checked');
	li.find('a').addClass('checked');
	var url = 'note/list.do';
	var data = {
		"noteBookId" : noteBookId
	};
	$.getJSON(url, data, function(result) {
		if (result.state == 0) {
			var notes = result.data;
			var ul = $('#pc_part_2 .contacts-list');
			ul.empty();
			for (i = 0; i < notes.length; i++) {
				var note = notes[i];
				var li = noteTemplate.replace('[title]', note.title);
				li = $(li).data('noteId', note.id);
				ul.append(li);
			}
		}
		if (result.state == 2) {
			alert(result.message);
		}
		if (result.state == 1) {
			alert(result.message);
		}
	});

}
/**
 * 加载笔记本
 */
function loadNotebooks() {
	// 请求notebook/list.do
	var url = 'notebook/list.do';
	var data = {
		'userId' : getCookie('userId')
	};
	$.getJSON(url, data, function(result) {
		if (result.state == 0) {
			var list = result.data;
			var ul = $('#pc_part_1 .contacts-list');
			ul.empty();
			// 遍历list中的每个笔记本生成的li元素，添加到 ul中
			for (i = 0; i < list.length; i++) {
				var notebook = list[i];
				var li = notebookTemplate.replace('[name]', notebook.name);
				// JQuery提供了数据绑定的方法，可以在DOM元素上绑定任何数据
				// data。第一个参数时KEY，第二个参数时VALUE，data(key,value)
				// 获取数据：data(key)
				li = $(li).data('noteBookId', notebook.id);
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
/**
 * 笔记本列表信息的li模板
 */
var notebookTemplate = '<li class="online notebook">' + '<a>'
		+ '<i class="fa fa-book" title="online" rel="tooltip-bottom">'
		+ '</i>[name]</a></li>';
/**
 * 笔记新列表的li模板
 */
var noteTemplate = '<li class="online"><a><i class="fa fa-file-text-o" title="online" rel="tooltip-bottom"></i>[title]'
		+ '<button type="button" class="btn btn-default btn-xs btn_position btn_slide_down"><i class="fa fa-chevron-down"></i></button>'
		+ '</a>'
		+ '<div class="note_menu" tabindex="-1">'
		+ '<dl>'
		+ '<dt>'
		+ '<button type="button" class="btn btn-default btn-xs btn_move" title="移动至..."><i class="fa fa-random"></i></button>'
		+ '</dt>'
		+ '<dt>'
		+ '<button type="button" class="btn btn-default btn-xs btn_share" title="分享"><i class="fa fa-sitemap"></i></button>'
		+ '</dt>'
		+ '<dt>'
		+ '<button type="button"class="btn btn-default btn-xs btn_delete" title="删除"><i class="fa fa-times"></i></button>'
		+ '</dt>' + '</dl>' + '</div></li>';
var iTemplate = '<i class="fa fa-file-text-o" title="online" rel="tooltip-bottom"></i>[title]'
		+ '<button type="button" class="btn btn-default btn-xs btn_position btn_slide_down"><i class="fa fa-chevron-down"></i>';
function createNote() {
	var li = $('#pc_part_1 .checked').parent();
	var noteBookId = li.data("noteBookId");
	var userId = getCookie('userId');
	var title = $('#input_note').val();
	if ("undefined" == typeof noteBookId) {
		alert('请选择笔记本');
	}
	if (userId == "") {
		alert("请登录");
		window.location.href = "log_in.html";
	}
	if (title == "") {
		alert("请输入标题");
	}
	var url = "note/createNote.do";
	var data = {
		"userId" : userId,
		"noteBookId" : noteBookId,
		"title" : title
	};
	$.getJSON(url, data, function(result) {
		if (result.state == 0) {
			var note = result.data;// 获取创建成功的笔记
			// 调用隐藏创建笔记提示狂，再创见成功时隐藏提示狂
			closeAlert();
			var ul = $('#pc_part_2 .contacts-list');
			var li = noteTemplate.replace('[title]', note.cn_note_title);
			// 这个绑定视为了得到笔记信息
			li = $(li).data('noteId', note.cn_note_id);
			// 这个绑定视为了修改和保存笔记
			$('#input_note_title').data('note', note);
			ul.append(li);
			li.parent().find('a').removeClass('checked');
			li.find('a').addClass('checked');
			$('#input_note_title').val(note.cn_note_title);
			$('#myEditor').html("");
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
/**
 * 创建笔记本
 */
function createNoteBook() {
	var userId = getCookie('userId');
	var noteBookName = $('#input_notebook').val();
	var url = "notebook/createnotebook.do"
	var data = {
		"userId" : userId,
		"noteBookName" : noteBookName
	};
	var canCreate = true;
	if (userId == "") {
		canCreate = false;
		alert("请登录");
	}
	if (noteBookName == "") {
		canCreate = false;
		alert("请输入笔记本名称");
	}
	if (canCreate) {
		$.getJSON(url, data, function(result) {
			if (result.state == 0) {
				var noteBook = result.data;
				closeAlert();
				var ul = $('#pc_part_1 .contacts-list');
				var li = notebookTemplate.replace('[name]',
						noteBook.cn_notebook_name);
				// 这个绑定视为了得到笔记信息
				li = $(li).data('noteBookId', noteBook.cn_notebook_id);
				ul.append(li);
				li.parent().find('a').removeClass('checked');
				li.find('a').addClass('checked');
				var ul = $('#pc_part_2 .contacts-list');
				ul.empty();
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

function closeAlert() {
	$('#can').empty();
	$('.opacity_bg').hide();
}
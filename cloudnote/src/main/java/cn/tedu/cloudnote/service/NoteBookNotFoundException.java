package cn.tedu.cloudnote.service;
/**
 * 业务层笔记本不存在异常类
 * @author soft01
 *
 */
public class NoteBookNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5490454729222541669L;

	public NoteBookNotFoundException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public NoteBookNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

	public NoteBookNotFoundException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public NoteBookNotFoundException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public NoteBookNotFoundException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

}

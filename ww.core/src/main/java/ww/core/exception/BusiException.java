package ww.core.exception;

/**
 * 业务异常
 * <p>由业务逻辑产生的，编码强制抛出的异常信息。</p>
 * @author lizhiwei
 *
 */
public class BusiException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public BusiException(String message) {
		super(message);
	}
	
}

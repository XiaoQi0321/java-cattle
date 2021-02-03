package work.ambitlu.exception;

import work.ambitlu.domain.ExceptionCodes;

/**
 * 业务异常，从业务层代码抛出
 *
 * @author Ambi 赵帅
 * @date 2020/12/15 22:38
 * @since JDK 1.11
 */
public class BizException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public BizException() {
		super();
	}

	public BizException(String message) {
		super(message);
	}

	public BizException(Integer code, String message) {
		super(message);
		this.code = (code == null ? ExceptionCodes.UNKNOWN : code);
	}

	public BizException(Integer code, String message, Throwable cause) {
		super(message, cause);
		this.code = (code == null ? ExceptionCodes.UNKNOWN : code);
	}

	public BizException(String message, Throwable cause) {
		super(message, cause);
	}

	public static BizException InvalidParamException(String message) {
		return new BizException(ExceptionCodes.ILLEGAL_ARGUMENT, message);
	}

	protected Integer code = ExceptionCodes.UNKNOWN;

	public Integer getCode() {
		return code;
	}
}

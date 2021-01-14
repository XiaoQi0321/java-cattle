package work.ambitlu.exception;

/**
 * 一些声明信息
 *
 * @author Ambi 赵帅
 * @date 2021/1/14 21:38
 */
public class StorageException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public StorageException() {
		super();
	}

	public StorageException(String message, Throwable cause) {
		super(message, cause);
	}

	public StorageException(String message) {
		super(message);
	}

	public StorageException(Throwable cause) {
		super(cause);
	}

}

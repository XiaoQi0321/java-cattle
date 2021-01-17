package work.ambitlu.domain;

import java.util.HashMap;

/**
 * 前端、小程序返回对象封装
 *
 * @author Ambi 赵帅
 * @date 2021/1/12 22:00
 */
public class AccessResult extends HashMap<String, Object> {

	private static final long serialVersionUID = 1L;

	private static final String DEF_CODE_VALUE = "code";
	private static final String DEF_MESSAGE_VALUE = "message";

	public static final AccessResult SUCCESS = new AccessResult(true, ExceptionCodes.SUCCESS, "SUCCESS");
	public static final AccessResult FAILURE = new AccessResult(true,ExceptionCodes.FAILURE,  "ERROR");

	public static final AccessResult ERROR = new AccessResult(true,ExceptionCodes.UNKNOWN, "ERROR");

	private boolean notAllowExpansion; //禁止扩展

	private AccessResult(boolean notAllowExpansion,Integer code, String message) {
		this(code,message);
		this.notAllowExpansion = notAllowExpansion;
	}

	public AccessResult(Integer code) {
		this(code,null);
	}

	public AccessResult(Integer code, String message) {
		this(code,message,null);
	}

	public AccessResult(Integer code, String message, Object data) {
		super();
		if (code != null) {
			super.put(DEF_CODE_VALUE, code);
		}
		if (message != null) {
			super.put(DEF_MESSAGE_VALUE, message);
		}
		if (data != null) {
			this.put("result", data);
		}
	}

	/**
	 * 返回成功消息
	 *
	 * @param dataName 需要返回的对象名称
	 * @param data 返回对象value
	 * @return
	 */
	public static AccessResult newSuccessMessage(String dataName, Object data) {
		AccessResult resultMessage = new AccessResult(ExceptionCodes.SUCCESS, "SUCCESS");
		if (dataName != null && data != null) {
			resultMessage.put(dataName, data);
		}
		return resultMessage;
	}

	/**
	 * 返回成功消息
	 *
	 * @param data 返回对象value
	 * @return
	 */
	public static AccessResult newSuccessMessage(Object data) {
		return newSuccessMessage("result", data);
	}

	/**
	 * 返回失败消息
	 *
	 * @param message 失败消息
	 * @return
	 */
	public static AccessResult newErrorMessage(String message) {
		return new AccessResult(ExceptionCodes.UNKNOWN, message);
	}

	/**
	 * 返回失败消息(兼容原ResultMessage返回值)
	 * @param message
	 * @return
	 */
	public static AccessResult newFailureMessage(String message) {
		return new AccessResult(ExceptionCodes.FAILURE, message);
	}


	/**
	 * 参数错误消息
	 * @param detail
	 * @return
	 */
	public static AccessResult illegalArgumentMessage(String detail) {
		return new AccessResult(ExceptionCodes.ILLEGAL_ARGUMENT, detail);
	}

	///**
	// * 分页列表
	// *
	// * @param page
	// * @return
	// */
	//public static AccessResult newPageResultMessage(Page<?> page) {
	//	AccessResult resultMessage = AccessResult.newSuccessMessage(page.getResult());
	//	Map<String, Object> p = new HashMap<>();
	//	p.put("pageNo", page.getPageNo());
	//	p.put("pageSize", page.getPageSize());
	//	p.put("totalCount", (int) page.getTotalCount());
	//	p.put("totalPages", (int) page.getTotalPages());
	//	resultMessage.put("page", p);
	//	return resultMessage;
	//}



	public Integer getCode() {
		if (this.get(DEF_CODE_VALUE) != null) {
			return (Integer) this.get(DEF_CODE_VALUE);
		}
		return null;
	}

	public String getMessage() {
		if (this.get(DEF_MESSAGE_VALUE) != null) {
			return (String) this.get(DEF_MESSAGE_VALUE);
		}
		return null;
	}

	public void setMessage(String message) {
		if (this.get(DEF_MESSAGE_VALUE) != null) {
			super.put(DEF_MESSAGE_VALUE,message);
		}
	}

	@Override
	public Object put(String key, Object value) {
		if(DEF_CODE_VALUE.equals(key)) {
			throw new RuntimeException("AccessResult is not allow to use special key '"+DEF_CODE_VALUE+"'.");
		}
		if(DEF_MESSAGE_VALUE.equals(key)) {
			throw new RuntimeException("AccessResult is not allow to use special key '"+DEF_MESSAGE_VALUE+"'.");
		}
		if(this.notAllowExpansion) {
			throw new RuntimeException("AccessResult is not allow to expansion");
		}
		return super.put(key, value);
	}
}


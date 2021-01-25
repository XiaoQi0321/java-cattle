package work.ambitlu.core.entity;

import cn.hutool.http.HttpStatus;

/**
 * 一些声明信息
 *
 * @author Ambi 赵帅
 * @date 2020/12/13 14:42
 * @since JDK 1.11
 */
public enum ServiceError {

	/** Global Error */
	NORMAL(0, "操作成功"),
	UN_KNOW_ERROR(100, "未知错误"),

	/** Login Error */
	GLOBAL_ERR_NO_SIGN_IN(HttpStatus.HTTP_UNAUTHORIZED,"未登录或登录过期/Not sign in"),
	GLOBAL_ERR_NO_CODE(102,"code错误/error code"),
	GLOBAL_ERR_NO_AUTHORITY(103, "没有操作权限/No operating rights"),
	;

	private int code;
	private String msg;

	private ServiceError(int code, String msg)
	{
		this.code=code;
		this.msg=msg;
	}

	public int getCode() {
		return code;
	}

	public String getMsg() {
		return msg;
	}
}


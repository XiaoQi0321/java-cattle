package work.ambitlu.domain;

/**
 * 一些声明信息
 *
 * @author Ambi 赵帅
 * @date 2021/1/12 22:01
 */
public class ExceptionCodes {


	public final static int SUCCESS = 0; // 正确返回code；由于对外错误码统一统计在此处，所以正确返回code也定义在此
	public final static int FAILURE = 100; // 错误code

	// Common exception 1000xxx
	public final static int UNKNOWN = 1000000; // 未知错误，对外是内部错误

	// Common exception 1000xxx
	public final static int ILLEGAL_ARGUMENT = 1000001; // 参数错误
	public final static int SMS_PIN_ERROR = 1000002; // 短信验证码不正确

}

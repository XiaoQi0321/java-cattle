package work.ambitlu.config;

import lombok.extern.log4j.Log4j2;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import work.ambitlu.domain.AccessResult;

import java.util.HashMap;
import java.util.Map;

/**
 * 全局异常处理
 *
 * @author Ambi 赵帅
 * @date 2021/1/17 16:47
 */
@Log4j2
@RestControllerAdvice
public class GlobalExceptionHandler {

	/**
	 * TODO 可优化
	 */
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public AccessResult handleValidationException(MethodArgumentNotValidException e) {
		log.error("exception message:", e);
		//Map<String, String> errors = new HashMap<>();
		//e.getBindingResult().getAllErrors().forEach((error) -> {
		//	String fieldName = ((FieldError) error).getField();
		//	String errorMessage = error.getDefaultMessage();
		//	errors.put(fieldName, errorMessage);
		//});
		return AccessResult.newFailureMessage("错误");
	}


}

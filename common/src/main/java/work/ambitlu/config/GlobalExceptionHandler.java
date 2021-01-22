package work.ambitlu.config;

import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

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

	@ExceptionHandler(BindException.class)
	public ResponseEntity<String> bindExceptionHandler(BindException e) {
		e.printStackTrace();
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getBindingResult().getFieldErrors().get(0).getDefaultMessage());

	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<String> methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException e) {
		e.printStackTrace();
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getBindingResult().getFieldErrors().get(0).getDefaultMessage());
	}

	/**
	 * 全局异常捕捉处理
	 * @param ex
	 */
	@ExceptionHandler(value = Exception.class)
	public ResponseEntity<Map> errorHandler(Exception ex) {
		ex.printStackTrace();
		Map map = new HashMap();
		map.put("code", 100);
		map.put("message", "error");
		return ResponseEntity.status(HttpStatus.OK).body(map);
	}


}

package work.ambitlu.controller;

import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import work.ambitlu.domain.AccessResult;

/**
 * 一些声明信息
 *
 * @author Ambi 赵帅
 * @date 2021/1/25 23:08
 */
@RestController
@RequestMapping("/p")
@Api(tags = "DemoController", description = "Swagger测试")
public class PController {

	@GetMapping
	private AccessResult as(){
		return AccessResult.SUCCESS;
	}


}

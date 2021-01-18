package work.ambitlu.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import work.ambitlu.domain.AccessResult;
import work.ambitlu.domain.HomeContentResult;
import work.ambitlu.mvc.AbstractController;
import work.ambitlu.service.HomeService;

/**
 * 首页内容管理Controller
 *
 * @author Ambi 赵帅
 * @date 2021/1/18 22:17
 */
@RestController
@Api(tags = "HomeController", description = "首页内容管理")
@RequestMapping("/home")
public class HomeController extends AbstractController {

	@Autowired
	private HomeService homeService;

	@ApiOperation("首页内容页信息展示")
	@GetMapping(value = "/content")
	public AccessResult content() {
		HomeContentResult contentResult = homeService.content();
		return AccessResult.newSuccessMessage(contentResult);
	}

}

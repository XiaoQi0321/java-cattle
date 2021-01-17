package work.ambitlu.controller.system;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import work.ambitlu.common.Version;
import work.ambitlu.domain.AccessResult;
import work.ambitlu.mvc.AbstractController;

/**
 * 一些声明信息
 *
 * @author Ambi 赵帅
 * @date 2021/1/17 16:35
 */
@RequestMapping("/version")
@RestController
@Api(value = "版本", tags = {"版本"})
public class VersionController extends AbstractController {

	@ApiOperation(value = "版本", notes = "版本")
	@GetMapping(path = "/info")
	public AccessResult getVersionInfo() {
		return AccessResult.newSuccessMessage(Version.SERVER + "." + Version.DATE);
	}

}

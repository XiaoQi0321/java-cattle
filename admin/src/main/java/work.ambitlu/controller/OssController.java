package work.ambitlu.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import work.ambitlu.aliyun.OssPolicyResult;
import work.ambitlu.aliyun.OssService;
import work.ambitlu.domain.AccessResult;
import work.ambitlu.storage.response.OssCallbackResult;

import javax.servlet.http.HttpServletRequest;

/**
 * 一些声明信息
 *
 * @author Ambi 赵帅
 * @date 2021/1/12 21:58
 */
@RestController
@Api(tags = "OssController", description = "Oss管理")
@RequestMapping("/aliyun/oss")
public class OssController {
	@Autowired
	private OssService ossService;

	@ApiOperation(value = "oss上传签名生成")
	@RequestMapping(value = "/policy", method = RequestMethod.GET)
	public AccessResult policy() {
		OssPolicyResult result = ossService.policy();
		return AccessResult.newSuccessMessage(result);
	}

	@ApiOperation(value = "oss上传成功回调")
	@RequestMapping(value = "/callback", method = RequestMethod.POST)
	public AccessResult callback(HttpServletRequest request) {
		OssCallbackResult ossCallbackResult = ossService.callback(request);
		return AccessResult.newSuccessMessage(ossCallbackResult);
	}

}
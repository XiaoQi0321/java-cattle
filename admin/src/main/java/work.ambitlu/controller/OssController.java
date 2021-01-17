package work.ambitlu.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import work.ambitlu.aliyun.OssPolicyResult;
import work.ambitlu.aliyun.OssService;
import work.ambitlu.domain.AccessResult;
import work.ambitlu.mvc.AbstractController;
import work.ambitlu.storage.StorageService;
import work.ambitlu.storage.response.FileResponseInfo;
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
public class OssController extends AbstractController {

	@Autowired
	private OssService ossService;
	@Autowired
	private StorageService storageService;

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

	@PostMapping("/upload")
	public AccessResult uploadImg(@RequestParam("file") MultipartFile file){
		FileResponseInfo upload = storageService.upload(file);
		return AccessResult.SUCCESS;
	}

}
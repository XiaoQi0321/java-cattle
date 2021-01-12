package work.ambitlu.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import work.ambitlu.model.Demo;
import work.ambitlu.service.demo.DemoService;

import java.util.UUID;

/**
 * 一些声明信息
 *
 * @author Ambi 赵帅
 * @date 2021/1/9 17:22
 */
@RestController
@RequestMapping("/demo")
@Api(tags = "DemoController", description = "Swagger测试")
public class DemoController {

	@Autowired
	private DemoService demoService;

	@ApiOperation("欢迎开发者")
	@GetMapping
	public ResponseEntity<String> index(){
		return new ResponseEntity<>("Hello zlg 的高级开发者!", HttpStatus.OK);
	}

	@ApiOperation("Demo插入测试")
	@GetMapping("/insert")
	public ResponseEntity<Boolean> insert(){
		Demo demo = new Demo();
		demo.setDemo(UUID.randomUUID().toString());
		boolean b = demoService.saveOrUpdate(demo);
		return ResponseEntity.ok(b);
	}

}

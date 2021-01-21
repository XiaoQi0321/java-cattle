package work.ambitlu.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import work.ambitlu.domain.AccessResult;
import work.ambitlu.mvc.AbstractController;
import work.ambitlu.service.OzsAppOrderService;

import java.util.List;

/**
 * 订单管理Controller
 *
 * @Date: 2021/1/21 16:46
 * @Author: Ambi 赵帅
 */
@RestController
@Api(tags = "OmsPortalOrderController", description = "订单管理")
@RequestMapping("/order")
public class OmsAppOrderController extends AbstractController {

    @Autowired
    private OzsAppOrderService appOrderService;

    @ApiOperation("根据购物车信息生成确认单信息")
    @RequestMapping(value = "/generateOrder", method = RequestMethod.POST)
    public AccessResult generateConfirmOrder(@RequestBody List<Long> cartIds) {
        return AccessResult.SUCCESS;
    }

    @ApiOperation("根据购物车信息生成订单")
    @RequestMapping(value = "/generateOrder", method = RequestMethod.POST)
    public AccessResult generateOrder() {
        return AccessResult.SUCCESS;
    }

    @ApiOperation("用户支付成功的回调")
    @RequestMapping(value = "/paySuccess", method = RequestMethod.POST)
    public AccessResult paySuccess() {
        return AccessResult.SUCCESS;
    }

    @ApiOperation("自动取消超时订单")
    @RequestMapping(value = "/cancelTimeOutOrder", method = RequestMethod.POST)
    public AccessResult cancelTimeOutOrder(@RequestBody List<Long> cartIds) {
        return AccessResult.SUCCESS;
    }

    @ApiOperation("取消单个超时订单")
    @RequestMapping(value = "/cancelOrder", method = RequestMethod.POST)
    public AccessResult cancelOrder(@RequestBody List<Long> cartIds) {
        return AccessResult.SUCCESS;
    }


}

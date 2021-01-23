package work.ambitlu.controller.pay;

import com.github.binarywang.wxpay.bean.order.WxPayMpOrderResult;
import com.github.binarywang.wxpay.bean.request.WxPayUnifiedOrderRequest;
import com.github.binarywang.wxpay.constant.WxPayConstants;
import com.github.binarywang.wxpay.service.WxPayService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import work.ambitlu.core.user.ZlgUser;
import work.ambitlu.domain.AccessResult;
import work.ambitlu.mvc.AbstractController;
import work.ambitlu.ozs.OzsPayService;
import work.ambitlu.ozs.model.OzsPayInfoDto;
import work.ambitlu.ozs.model.OzsPayParam;
import work.ambitlu.utils.Arith;
import work.ambitlu.utils.HttpContextUtils;
import work.ambitlu.utils.IPHelper;

/**
 * 一些声明信息
 *
 * @author Ambi 赵帅
 * @date 2021/1/22 23:34
 */
@RestController
@RequestMapping("/order")
@Api(tags = "订单接口")
@AllArgsConstructor
public class PayController extends AbstractController {

	private final OzsPayService payService;

	//private final ApiConfig apiConfig;

	private final WxPayService wxMiniPayService;

	/**
	 * 支付接口
	 */
	@PostMapping("/pay")
	@ApiOperation(value = "根据订单号进行支付", notes = "根据订单号进行支付")
	@SneakyThrows
	public AccessResult pay(@RequestBody OzsPayParam payParam) {

		ZlgUser zlgUser = zUser();
		// 进行小程序支付
		OzsPayInfoDto payInfo = payService.pay(zlgUser.getUserId(), payParam);


		// 封装给前端的价格
		WxPayUnifiedOrderRequest orderRequest = new WxPayUnifiedOrderRequest();
		orderRequest.setBody(payInfo.getBody());
		orderRequest.setOutTradeNo(payInfo.getPayNo());
		orderRequest.setTotalFee((int) Arith.mul(payInfo.getPayAmount(), 100));
		orderRequest.setSpbillCreateIp(IPHelper.getIpAddr());
		orderRequest.setNotifyUrl(HttpContextUtils.getDomain() + "/notice/pay/order");
		orderRequest.setTradeType(WxPayConstants.TradeType.JSAPI);
		orderRequest.setOpenid(zlgUser.getBizUserId());
		return AccessResult.newSuccessMessage(wxMiniPayService.createOrder(orderRequest));
	}

}

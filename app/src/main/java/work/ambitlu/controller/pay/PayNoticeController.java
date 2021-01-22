package work.ambitlu.controller.pay;

import com.github.binarywang.wxpay.bean.notify.WxPayOrderNotifyResult;
import com.github.binarywang.wxpay.exception.WxPayException;
import com.github.binarywang.wxpay.service.WxPayService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;
import work.ambitlu.domain.AccessResult;
import work.ambitlu.ozs.OzsPayService;

/**
 * 一些声明信息
 *
 * @author Ambi 赵帅
 * @date 2021/1/22 23:55
 */
@ApiIgnore
@RestController
@RequestMapping("/notice/pay")
@AllArgsConstructor
public class PayNoticeController {

	/**
	 * 小程序支付
	 */
	private final WxPayService wxMiniPayService;

	private final OzsPayService payService;


	@RequestMapping("/order")
	public AccessResult submit(@RequestBody String xmlData) throws WxPayException {
		WxPayOrderNotifyResult parseOrderNotifyResult = wxMiniPayService.parseOrderNotifyResult(xmlData);

		String payNo = parseOrderNotifyResult.getOutTradeNo();
		String bizPayNo = parseOrderNotifyResult.getTransactionId();

		// 根据内部订单号更新order settlement
		payService.paySuccess(payNo, bizPayNo);


		return AccessResult.SUCCESS;
	}
}

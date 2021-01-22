package work.ambitlu.ozs.model;

import lombok.Data;

/**
 * 支付信息
 *
 * @author Ambi 赵帅
 * @date 2021/1/22 23:36
 */
@Data
public class OzsPayInfoDto {

	/**
	 * 支付信息，如商品名称
	 */
	private String body;

	/**
	 * 支付单号
	 */
	private String payNo;

	/**
	 * 付款金额
	 */
	private Double payAmount;

}

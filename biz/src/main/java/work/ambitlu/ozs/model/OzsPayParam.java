package work.ambitlu.ozs.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * 支付参数
 *
 * @author Ambi 赵帅
 * @date 2021/1/22 23:37
 */
@ApiModel(value= "支付参数")
public class OzsPayParam {

	/**
	 * 订单号
	 */
	@NotBlank(message="订单号不能为空")
	@ApiModelProperty(value = "订单号",required=true)
	private String orderNumbers;

	/**
	 * 支付方式
	 */
	@NotNull(message="支付方式不能为空")
	@ApiModelProperty(value = "支付方式 (1:微信支付 2:支付宝)",required=true)
	private Integer payType;

	public Integer getPayType() {
		return payType;
	}

	public void setPayType(Integer payType) {
		this.payType = payType;
	}

	public String getOrderNumbers() {
		return orderNumbers;
	}

	public void setOrderNumbers(String orderNumbers) {
		this.orderNumbers = orderNumbers;
	}
}

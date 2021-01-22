package work.ambitlu.ozs.model;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import work.ambitlu.domain.BaseEntity;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 一些声明信息
 *
 * @author Ambi 赵帅
 * @date 2021/1/17 22:17
 */
@TableName("OMS_ORDER")
@Data
public class OzsOrder extends BaseEntity {

	@ApiModelProperty(value = "订单编号")
	private String orderSn;

	@ApiModelProperty(value = "用户ID")
	private Long userId;

	@ApiModelProperty(value = "订单总金额")
	private BigDecimal totalAmount;

	@ApiModelProperty(value = "应付金额（实际支付金额）")
	private BigDecimal payAmount;

	@ApiModelProperty(value = "运费金额")
	private BigDecimal freightAmount;

	@ApiModelProperty(value = "管理员后台调整订单使用的折扣金额")
	private BigDecimal discountAmount;

	@ApiModelProperty(value = "支付方式：0->未支付；1->支付宝；2->微信")
	private Integer payType;

	@ApiModelProperty(value = "订单来源：0->PC订单；1->app订单")
	private Integer sourceType;

	@ApiModelProperty(value = "订单状态：0->待付款；1->待发货；2->已发货；3->已完成；4->已关闭；5->无效订单")
	private Integer status;

	@ApiModelProperty(value = "订单类型：0->正常订单；1->秒杀订单")
	private Integer orderType;

	@ApiModelProperty(value = "物流公司(配送方式)")
	private String deliveryCompany;

	@ApiModelProperty(value = "物流单号")
	private String deliverySn;

	@ApiModelProperty(value = "自动确认时间（天）")
	private Integer autoConfirmDay;

	@ApiModelProperty(value = "收票人电话")
	private String billReceiverPhone;

	@ApiModelProperty(value = "收票人邮箱")
	private String billReceiverEmail;

	@ApiModelProperty(value = "收货人姓名")
	private String receiverName;

	@ApiModelProperty(value = "收货人电话")
	private String receiverPhone;

	@ApiModelProperty(value = "省份/直辖市")
	private String receiverProvince;

	@ApiModelProperty(value = "城市")
	private String receiverCity;

	@ApiModelProperty(value = "区")
	private String receiverRegion;

	@ApiModelProperty(value = "详细地址")
	private String receiverDetailAddress;

	@ApiModelProperty(value = "订单备注")
	private String note;

	@ApiModelProperty(value = "确认收货状态：0->未确认；1->已确认")
	private Integer confirmStatus;

	@ApiModelProperty(value = "支付时间")
	private Date paymentTime;

	@ApiModelProperty(value = "发货时间")
	private Date deliveryTime;

	@ApiModelProperty(value = "确认收货时间")
	private Date receiveTime;

	@ApiModelProperty(value = "评价时间")
	private Date commentTime;

	@ApiModelProperty(value = "修改时间")
	private Date modifyTime;


}

package work.ambitlu.ozs.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 购物车项
 *
 * @author Ambi 赵帅
 * @date 2021/1/21 21:30
 */
@Getter
@Setter
public class OzsCartItem {

	private Long productId;

	@ApiModelProperty(value = "购买数量")
	private Integer quantity;

	@ApiModelProperty(value = "添加到购物车的价格")
	private BigDecimal price;

	@ApiModelProperty(value = "商品主图")
	private String productPic;

	@ApiModelProperty(value = "商品名称")
	private String productName;

	@ApiModelProperty(value = "商品副标题（卖点）")
	private String productSubTitle;

	@ApiModelProperty(value = "会员昵称")
	private String memberNickname;

	@ApiModelProperty(value = "创建时间")
	private Date createDate;

	@ApiModelProperty(value = "是否删除")
	private Integer deleteStatus;

	@ApiModelProperty(value = "商品分类")
	private Long productCategoryId;

	private String productSn;

}

package work.ambitlu.sms;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import work.ambitlu.domain.BaseEntity;

/**
 * 轮播广告
 *
 * @author Ambi 赵帅
 * @date 2021/1/18 21:36
 */
@Setter
@Getter
public class SzsHomeAdvertise extends BaseEntity {

	@ApiModelProperty(value = "商品id")
	private Long producetId;

	@ApiModelProperty(value = "轮播名称")
	private String name;

	@ApiModelProperty(value = "上下线状态：0->下线；1->上线")
	private Integer status;

	@ApiModelProperty(value = "备注")
	private String note;

	@ApiModelProperty(value = "排序")
	private Integer sort;

	@ApiModelProperty(value = "轮播资源地址")
	private String url;


}

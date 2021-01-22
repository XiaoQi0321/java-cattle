package work.ambitlu.pzs.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import work.ambitlu.domain.BaseEntity;

/**
 * 商品分类
 *
 * @author Ambi 赵帅
 * @date 2021/1/18 21:58
 */
@Setter
@Getter
public class PzsProductCategory extends BaseEntity {

	@ApiModelProperty(value = "上机分类的编号：0表示一级分类")
	private Long parentId;

	@ApiModelProperty(value = "分类名称")
	private String name;

	@ApiModelProperty(value = "分类级别：0->1级；1->2级")
	private Integer level;

	@ApiModelProperty(value = "是否显示在导航栏：true->显示")
	private Boolean navStatus;

	@ApiModelProperty(value = "显示状态：true->显示")
	private Boolean showStatus;

	private Integer sort;

	@ApiModelProperty(value = "图标")
	private String icon;

	@ApiModelProperty(value = "描述")
	private String description;

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(getClass().getSimpleName());
		sb.append(" [");
		sb.append("Hash = ").append(hashCode());
		sb.append(", id=").append(getId());
		sb.append(", parentId=").append(parentId);
		sb.append(", name=").append(name);
		sb.append(", level=").append(level);
		sb.append(", navStatus=").append(navStatus);
		sb.append(", showStatus=").append(showStatus);
		sb.append(", sort=").append(sort);
		sb.append(", icon=").append(icon);
		sb.append(", description=").append(description);
		sb.append("]");
		return sb.toString();
	}

}

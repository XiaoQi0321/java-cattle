package work.ambitlu.domain;


import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * 公共实体类
 *
 * @author Ambi 赵帅
 * @date 2021/1/9 19:57
 */
@Data
public abstract class BaseEntity implements Serializable{

	private static final long serialVersionUID = 1L;

	@TableId
	private Long id;

	@ApiModelProperty(value = "逻辑删除，1：已删除，0：未删除")
	@TableLogic()
	private Boolean deleted;

	@ApiModelProperty(value = "创建时间")
	private LocalDateTime createTime;

	//@ApiModelProperty(value = "修改时间")
	//private LocalDateTime utime;

	@Override
	public boolean equals(Object obj) {
		if (null == obj) {
			return false;
		}
		if (this == obj) {
			return true;
		}
		if (!getClass().equals(obj.getClass())) {
			return false;
		}
		BaseEntity that = (BaseEntity) obj;
		return null != this.getId() && this.getId().equals(that.getId());
	}

}

package work.ambitlu.domain;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;

import java.io.Serializable;
import java.util.Date;

/**
 * 一些声明信息
 *
 * @author Ambi 赵帅
 * @date 2021/1/17 17:27
 */
public abstract class BaseEntity2<T extends Model> extends Model {

	/**
	 * 实体编号（唯一标志）
	 *
	 */
	protected  Long id;
	protected Date createTime;

	public BaseEntity2() {

	}

	public BaseEntity2(Long id) {
		this();
		this.id = id;
	}

	@JsonSerialize(using= ToStringSerializer.class)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

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
		BaseEntity2<?> that = (BaseEntity2<?>) obj;
		return null != this.getId() && this.getId().equals(that.getId());
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
}


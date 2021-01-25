package work.ambitlu.uzs.model;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.format.annotation.DateTimeFormat;
import work.ambitlu.domain.BaseEntity;
import work.ambitlu.serializer.json.EmojiJsonSerializer;

/**
 * 一些声明信息
 *
 * @author Ambi 赵帅
 * @date 2021/1/23 22:55
 */
@EqualsAndHashCode(callSuper = true)
@Data
@TableName("UZS_USERS")
public class UzsUser extends BaseEntity {

	private String name;

	/**
	 * 用户昵称
	 */
	@JsonSerialize(using =  EmojiJsonSerializer.class)
	private String nickName;

	/**
	 * 例如：2009-11-27
	 */
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private String birthDate;

	/**
	 * 头像图片路径
	 */
	private String pic;



}

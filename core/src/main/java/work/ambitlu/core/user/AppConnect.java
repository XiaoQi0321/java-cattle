package work.ambitlu.core.user;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * 用户与三方系统的关联
 *
 * @author Ambi 赵帅
 * @date 2021/1/23 22:23
 */
@Data
@TableName("Uzs_app_connect")
public class AppConnect {
	/**
	 * id
	 */
	@TableId
	private Long id;

	/**
	 * 本系统userId
	 */

	private Long userId;

	/**
	 * 第三方系统id 1：微信小程序
	 */

	private Integer appId;

	/**
	 * 第三方系统昵称
	 */

	private String nickName;

	/**
	 * 第三方系统头像
	 */

	private String imageUrl;

	/**
	 * 第三方系统userid
	 */

	private String bizUserId;

	/**
	 * 第三方系统unionid
	 */

	private String bizUnionid;

}

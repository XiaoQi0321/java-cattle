package work.ambitlu.mvc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import work.ambitlu.core.user.ZlgUser;

/**
 * 一些声明信息
 *
 * @author Ambi 赵帅
 * @date 2021/1/17 16:36
 */
public abstract class AbstractController {

	protected final Logger logger = LoggerFactory.getLogger(getClass());

	public ZlgUser ZUser(){
		ZlgUser zlgUser = new ZlgUser();
		zlgUser.setUserId(1L);
		zlgUser.setBizUserId("1");
		return zlgUser;
	}

}


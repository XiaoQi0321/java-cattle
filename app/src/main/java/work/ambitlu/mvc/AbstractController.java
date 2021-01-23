package work.ambitlu.mvc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import work.ambitlu.core.user.ZlgUser;

/**
 * 小成功功能基础类
 *
 * @author Ambi 赵帅
 * @date 2021/1/17 16:36
 */
public abstract class AbstractController {

	protected final Logger logger = LoggerFactory.getLogger(getClass());

	public ZlgUser zUser(){
		return new ZlgUser(1L,"1",true);
	}

}


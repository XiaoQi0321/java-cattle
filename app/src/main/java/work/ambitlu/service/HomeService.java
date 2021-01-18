package work.ambitlu.service;

import work.ambitlu.domain.HomeContentResult;

/**
 * 首页内容管理Service
 *
 * @author Ambi 赵帅
 * @date 2021/1/18 21:42
 */
public interface HomeService {

	/**
	 * 获取首页内容
	 */
	HomeContentResult content();
}

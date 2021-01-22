package work.ambitlu.service.Impl;

import org.springframework.stereotype.Service;
import work.ambitlu.domain.HomeContentResult;
import work.ambitlu.service.HomeService;
import work.ambitlu.szs.SzsHomeAdvertise;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 首页内容管理Service实现类
 *
 * @author Ambi 赵帅
 * @date 2021/1/18 21:43
 */
@Service
public class HomeServiceImpl implements HomeService {

	@Override
	public HomeContentResult content() {
		HomeContentResult result = new HomeContentResult();
		//获取首页广告
		result.setAdvertiseList(getHomeAdvertiseList());
		//获取新品推荐
		result.setNewProductList(Collections.emptyList());
		//获取人气推荐
		result.setHotProductList(Collections.emptyList());
		return result;
	}

	private List<SzsHomeAdvertise> getHomeAdvertiseList() {
		List<SzsHomeAdvertise> list = new ArrayList<>();
		SzsHomeAdvertise szsHomeAdvertise = new SzsHomeAdvertise();
		szsHomeAdvertise.setId(1L);
		szsHomeAdvertise.setName("ahah");
		szsHomeAdvertise.setUrl("/static/img/banner_01.png");
		list.add(szsHomeAdvertise);
		return list;
	}
}

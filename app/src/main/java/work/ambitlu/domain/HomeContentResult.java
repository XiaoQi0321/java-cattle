package work.ambitlu.domain;

import lombok.Getter;
import lombok.Setter;
import work.ambitlu.pzs.model.PzsProduct;
import work.ambitlu.szs.SzsHomeAdvertise;

import java.util.List;

/**
 * 首页内容返回信息封装
 *
 * @author Ambi 赵帅
 * @date 2021/1/18 21:35
 */
@Getter
@Setter
public class HomeContentResult {

	/** 轮播广告 */
	private List<SzsHomeAdvertise> advertiseList;
	/** 新品推荐 */
	private List<PzsProduct> newProductList;
	/** 人气推荐 */
	private List<PzsProduct> hotProductList;

}

package work.ambitlu.ozs;

import work.ambitlu.ozs.model.OzsPayInfoDto;
import work.ambitlu.ozs.model.OzsPayParam;

import java.util.List;

/**
 * 一些声明信息
 *
 * @author Ambi 赵帅
 * @date 2021/1/22 23:36
 */
public interface OzsPayService {


	OzsPayInfoDto pay(Long userId, OzsPayParam payParam);

	List<String> paySuccess(String payNo, String bizPayNo);

}

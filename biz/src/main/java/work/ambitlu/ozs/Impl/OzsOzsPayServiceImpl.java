package work.ambitlu.ozs.Impl;

import cn.hutool.core.lang.Snowflake;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import work.ambitlu.ozs.OzsPayService;
import work.ambitlu.ozs.model.OzsPayInfoDto;
import work.ambitlu.ozs.model.OzsPayParam;

import java.util.Collections;
import java.util.List;

/**
 * 一些声明信息
 *
 * @author Ambi 赵帅
 * @date 2021/1/22 23:38
 */
@Slf4j
@Service
public class OzsOzsPayServiceImpl implements OzsPayService {

	@Autowired
	private Snowflake snowflake;

	@Override
	@Transactional(rollbackFor = Exception.class)
	public OzsPayInfoDto pay(Long userId, OzsPayParam payParam) {


		// 支付单号
		String payNo = String.valueOf(snowflake.nextId());

		OzsPayInfoDto payInfoDto = new OzsPayInfoDto();
		payInfoDto.setBody("123");
		payInfoDto.setPayAmount(12D);
		payInfoDto.setPayNo(payNo);
		return payInfoDto;
	}

	@Override
	public List<String> paySuccess(String payNo, String bizPayNo) {

		log.info("订单支付成功~~~~~~payNo：{},bizPayNo{}",payNo,bizPayNo);


		return Collections.emptyList();
	}
}

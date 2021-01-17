package work.ambitlu.oms.Impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import work.ambitlu.oms.OmsOrderService;
import work.ambitlu.oms.mapper.OmsOrderMapper;
import work.ambitlu.oms.model.OmsOrder;

/**
 * 订单信息
 *
 * @author Ambi 赵帅
 * @date 2021/1/17 22:33
 */
@Service
public class OmsOrderServiceImpl extends ServiceImpl<OmsOrderMapper, OmsOrder> implements OmsOrderService {
}

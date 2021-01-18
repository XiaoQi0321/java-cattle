package work.ambitlu.oms.Impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import work.ambitlu.oms.OzsOrderService;
import work.ambitlu.oms.mapper.OzsOrderMapper;
import work.ambitlu.oms.model.OzsOrder;

/**
 * 订单信息
 *
 * @author Ambi 赵帅
 * @date 2021/1/17 22:33
 */
@Service
public class OzsOrderServiceImpl extends ServiceImpl<OzsOrderMapper, OzsOrder> implements OzsOrderService {
}

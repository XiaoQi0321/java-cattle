package work.ambitlu.pms.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import work.ambitlu.pms.model.PzsProduct;
import work.ambitlu.pms.PzsProductService;
import work.ambitlu.pms.mapper.PzsProductMapper;

/**
 * 商品信息
 *
 * @author Ambi 赵帅
 * @date 2021/1/17 17:55
 */
@Service
public class PzsProductServiceImpl extends ServiceImpl<PzsProductMapper, PzsProduct> implements PzsProductService {
}

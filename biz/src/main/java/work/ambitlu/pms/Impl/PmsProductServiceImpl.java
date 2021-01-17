package work.ambitlu.pms.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import work.ambitlu.pms.model.PmsProduct;
import work.ambitlu.pms.PmsProductService;
import work.ambitlu.pms.mapper.PmsProductMapper;

/**
 * 商品信息
 *
 * @author Ambi 赵帅
 * @date 2021/1/17 17:55
 */
@Service
public class PmsProductServiceImpl extends ServiceImpl<PmsProductMapper, PmsProduct> implements PmsProductService {
}

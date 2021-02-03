package work.ambitlu.uzs.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import work.ambitlu.uzs.mapper.UzsUserMapper;
import work.ambitlu.uzs.model.UzsUser;
import work.ambitlu.uzs.service.IUzsUserService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 会员表 服务实现类
 * </p>
 *
 * @author 李磊
 * @since 2021-02-03
 */
@Service
public class UzsUserServiceImpl extends ServiceImpl<UzsUserMapper, UzsUser> implements IUzsUserService {

}

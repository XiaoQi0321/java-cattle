package work.ambitlu.uzs.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import work.ambitlu.uzs.model.UzsUser;

/**
 * 一些声明信息
 *
 * @author Ambi 赵帅
 * @date 2021/1/23 22:54
 */
public interface UzsUserMapper extends BaseMapper<UzsUser> {

	UzsUser getUserByBizUserId(@Param("bizUserId")String bizUserId);

}

package work.ambitlu.model;

import lombok.Data;
import work.ambitlu.domain.BaseEntity;

/**
 * 一些声明信息
 *
 * @author Ambi 赵帅
 * @date 2021/1/9 20:13
 */
@Data
public class Demo extends BaseEntity<Demo> {

	private String demo;

}

package work.ambitlu.aliyun;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * oss上传成功后的回调参数
 *
 * @author Ambi 赵帅
 * @date 2021/1/12 21:54
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class OssCallbackParam {
	@ApiModelProperty("请求的回调地址")
	private String callbackUrl;
	@ApiModelProperty("回调是传入request中的参数")
	private String callbackBody;
	@ApiModelProperty("回调时传入参数的格式，比如表单提交形式")
	private String callbackBodyType;
}

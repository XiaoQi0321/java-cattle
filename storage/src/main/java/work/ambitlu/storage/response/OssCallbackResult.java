package work.ambitlu.storage.response;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 获取OSS上传文件授权返回结果
 *
 * @author Ambi 赵帅
 * @date 2021/1/12 21:31
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class OssCallbackResult extends FileResponseInfo {

	@ApiModelProperty("文件名称")
	private String filename;
	@ApiModelProperty("文件大小")
	private String size;
	@ApiModelProperty("文件的mimeType")
	private String mimeType;
	@ApiModelProperty("图片文件的宽")
	private String width;
	@ApiModelProperty("图片文件的高")
	private String height;

}

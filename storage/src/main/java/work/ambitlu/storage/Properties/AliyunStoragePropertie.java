package work.ambitlu.storage.Properties;

import lombok.Data;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;

/**
 * 一些声明信息
 *
 * @author Ambi 赵帅
 * @date 2021/1/16 20:16
 */
@Data
public class AliyunStoragePropertie implements Serializable {

	private static final long serialVersionUID = 1L;
	private String key = "LTAI4FymxGzN8gSq7PrgWnA8";
	private String secret = "Ecutud2CRSvoxIisdkIAjQfdqt5fHW";
	private String endpoint = "oss-cn-shanghai.aliyuncs.com";
	private String bucket = "zlg-projects";
	private String path = "wnct/data";
	private Long storageId;       //文件存储id

	public boolean validate() {
		return StringUtils.isNoneBlank(key,endpoint,bucket,path);
	}
}

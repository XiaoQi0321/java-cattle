package work.ambitlu.storage.response;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 文件返回信息
 *
 * @author Ambi 赵帅
 * @date 2021/1/12 21:30
 */
@Data
@NoArgsConstructor
public class FileResponseInfo {

	/** 文件key */
	private String fileKey;
	/** 文件组 */
	private String fileGroup;
	/** 文件路径 */
	private String filePath;

	public FileResponseInfo(String fileKey) {
		super();
		this.fileKey = fileKey;
	}


}

package work.ambitlu.storage.response;

import lombok.Data;
import lombok.NoArgsConstructor;
import work.ambitlu.service.FileMeta;

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


	public static final class FileResponseInfoBuilder {
		private String fileKey;
		private String fileGroup;
		private String filePath;

		public FileResponseInfoBuilder() {
		}

		public static FileResponseInfoBuilder aFileResponseInfo() {
			return new FileResponseInfoBuilder();
		}

		public FileResponseInfoBuilder fileKey(String fileKey) {
			this.fileKey = fileKey;
			return this;
		}

		public FileResponseInfoBuilder fileGroup(String fileGroup) {
			this.fileGroup = fileGroup;
			return this;
		}

		public FileResponseInfoBuilder filePath(String filePath) {
			this.filePath = filePath;
			return this;
		}

		public FileResponseInfo build() {
			FileResponseInfo fileResponseInfo = new FileResponseInfo();
			fileResponseInfo.setFileKey(fileKey);
			fileResponseInfo.setFileGroup(fileGroup);
			fileResponseInfo.setFilePath(filePath);
			return fileResponseInfo;
		}
	}
}

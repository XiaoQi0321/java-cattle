package work.ambitlu.service;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import work.ambitlu.domain.BaseEntity;
import work.ambitlu.storage.response.FileResponseInfo;

import javax.activation.MimeType;

/**
 * 一些声明信息
 *
 * @author Ambi 赵帅
 * @date 2021/1/16 21:47
 */
@Data
@TableName("FILE_META")
public class FileMeta  extends BaseEntity<FileMeta> {

	private static final long serialVersionUID = 1L;

	@TableField(value="filekey")
	private String key; // 文件唯一标识

	private MimeType mimeType; // 文件类型
	@TableField(value="sizes")
	private Long size; // 文件大小


	public static final class FileMetaBuilder {
		private String key; // 文件唯一标识
		private MimeType mimeType; // 文件类型

		private Long size; // 文件大小

		public FileMetaBuilder() {
		}

		public static FileMetaBuilder aFileMeta() {
			return new FileMetaBuilder();
		}

		public FileMetaBuilder key(String key) {
			this.key = key;
			return this;
		}

		public FileMetaBuilder mimeType(MimeType mimeType) {
			this.mimeType = mimeType;
			return this;
		}

		public FileMetaBuilder size(Long size) {
			this.size = size;
			return this;
		}

		public FileMeta build() {
			FileMeta fileMeta = new FileMeta();
			fileMeta.setKey(key);
			fileMeta.setMimeType(mimeType);
			fileMeta.setSize(size);
			return fileMeta;
		}
	}
}

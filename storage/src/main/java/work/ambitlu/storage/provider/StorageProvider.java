package work.ambitlu.storage.provider;

import work.ambitlu.storage.StorageStrategy;
import work.ambitlu.storage.response.FileResponseInfo;

import java.io.InputStream;
import java.nio.file.FileSystems;

/**
 * 文件存储服务
 *
 * @author Ambi 赵帅
 * @date 2021/1/14 21:13
 */

public interface StorageProvider {

	String SEPERATOR = FileSystems.getDefault().getSeparator();

	public FileResponseInfo upload(InputStream in,String fileType);

	/**
	 * Description: 判断OSS服务文件上传时文件的contentType
	 *
	 * @param filenameExtension 文件后缀
	 * @return String
	 */
	default String getcontentType(String filenameExtension) {
		if (filenameExtension.equalsIgnoreCase("bmp")) {
			return "image/bmp";
		}
		if (filenameExtension.equalsIgnoreCase("gif")) {
			return "image/gif";
		}
		if (filenameExtension.equalsIgnoreCase("jpeg") ||
				filenameExtension.equalsIgnoreCase("jpg") ||
				filenameExtension.equalsIgnoreCase("png")) {
			return "image/jpeg";
		}
		if (filenameExtension.equalsIgnoreCase("html")) {
			return "text/html";
		}
		if (filenameExtension.equalsIgnoreCase("txt")) {
			return "text/plain";
		}
		if (filenameExtension.equalsIgnoreCase("vsd")) {
			return "application/vnd.visio";
		}
		if (filenameExtension.equalsIgnoreCase("pptx") ||
				filenameExtension.equalsIgnoreCase("ppt")) {
			return "application/vnd.ms-powerpoint";
		}
		if (filenameExtension.equalsIgnoreCase("docx") ||
				filenameExtension.equalsIgnoreCase("doc")) {
			return "application/msword";
		}
		if (filenameExtension.equalsIgnoreCase("xml")) {
			return "text/xml";
		}
		return "image/jpeg";
	}

}

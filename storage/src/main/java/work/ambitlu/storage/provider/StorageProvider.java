package work.ambitlu.storage.provider;

import work.ambitlu.storage.response.FileResponseInfo;

import java.io.InputStream;

/**
 * 文件存储服务
 *
 * @author Ambi 赵帅
 * @date 2021/1/14 21:13
 */

public interface StorageProvider {

	public FileResponseInfo upload(InputStream in,String fileType);

}

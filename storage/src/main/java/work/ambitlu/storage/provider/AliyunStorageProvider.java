package work.ambitlu.storage.provider;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.common.auth.CredentialsProvider;
import work.ambitlu.storage.response.FileResponseInfo;

import java.io.InputStream;

/**
 * 一些声明信息
 *
 * @author Ambi 赵帅
 * @date 2021/1/14 22:57
 */
public class AliyunStorageProvider implements StorageProvider{

	//private CredentialsProvider credsProvider;
	//
	//OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

	@Override
	public FileResponseInfo upload(InputStream in, String fileType) {
		return null;
	}
}

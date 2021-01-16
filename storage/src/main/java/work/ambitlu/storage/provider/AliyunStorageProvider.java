package work.ambitlu.storage.provider;

import cn.hutool.core.img.ImgUtil;
import cn.hutool.core.io.FileTypeUtil;
import cn.hutool.core.io.FileUtil;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.common.auth.CredentialsProvider;
import com.aliyun.oss.common.auth.DefaultCredentialProvider;
import com.aliyun.oss.model.ObjectMetadata;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import work.ambitlu.storage.StorageStrategy;
import work.ambitlu.storage.response.FileResponseInfo;

import javax.activation.MimetypesFileTypeMap;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.UUID;

/**
 * 一些声明信息
 *
 * @author Ambi 赵帅
 * @date 2021/1/14 22:57
 */
@Slf4j
public class AliyunStorageProvider implements StorageProvider{

	static  final  String SEPERATOR = "/";

	private String endpoint;
	private String bucket;
	private CredentialsProvider credsProvider;
	private String path;

	//private CredentialsProvider credsProvider;
	//
	private OSS ossClient ;
	//OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

	public AliyunStorageProvider(String accessKey, String accessSecret, String endpoint, String bucket,String path) {
		super();
		this.credsProvider = new DefaultCredentialProvider(accessKey, accessSecret);
		this.endpoint = endpoint;
		this.bucket = bucket;
		this.path=path;
	}



	@Override
	public FileResponseInfo upload(InputStream in, String fileType) {

		String fileKey = UUID.randomUUID().toString();

		String filePath = handlePath(fileKey + "." + fileType);
		OSSClient client = new OSSClient(endpoint,credsProvider,null);

		// 提交节点
		String realPath = "http://" + bucket + endpoint + filePath;

		ObjectMetadata objectMetadata = new ObjectMetadata();
		try {
			objectMetadata.setContentLength(in.available());
			objectMetadata.setCacheControl("no-cache");
			objectMetadata.setHeader("Pragma", "no-cache");
			objectMetadata.setContentType(getcontentType(fileType));
			objectMetadata.setContentDisposition("inline;filename=" + fileKey);

			client.putObject(bucket, filePath, in,objectMetadata);
		} catch (IOException e) {
			log.error(e.getMessage(), e);
		} finally {
			try {
				client.shutdown();
				if (in != null) {
					in.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return new FileResponseInfo.FileResponseInfoBuilder().fileKey(fileKey).filePath(realPath).build();
	}

	/**
	 * 上传到指定目录
	 */
	private String handlePath(String fileKey){
		if(StringUtils.isNotBlank(path)){
			if(path.endsWith(SEPERATOR)){
				return path+fileKey;
			}
			return path+SEPERATOR+fileKey;
		}
		return fileKey;
	}
}

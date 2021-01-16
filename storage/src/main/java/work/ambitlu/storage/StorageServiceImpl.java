package work.ambitlu.storage;

import cn.hutool.core.io.FileUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import org.springframework.web.multipart.MultipartFile;
import work.ambitlu.exception.StorageException;
import work.ambitlu.service.FileMeta;
import work.ambitlu.service.FileMetaService;
import work.ambitlu.storage.Properties.AliyunStoragePropertie;
import work.ambitlu.storage.provider.AliyunStorageProvider;
import work.ambitlu.storage.provider.LocalStorageProvider;
import work.ambitlu.storage.provider.StorageProvider;
import work.ambitlu.storage.response.FileResponseInfo;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * 一些声明信息
 *
 * @author Ambi 赵帅
 * @date 2021/1/12 21:22
 */
@Slf4j
@Component
public class StorageServiceImpl implements StorageService{


	@Autowired
	private ObjectMapper objectMapper;
	@Autowired
	private FileMetaService fileMetaService;

	@Override
	public FileResponseInfo upload(MultipartFile file) {

		StorageProvider storageProvider = getStorageProvider(StorageStrategy.ALIYUN);

		long fileSize = file.getSize();
		String originalFilename = file.getOriginalFilename();
		String extName = FileUtil.extName(originalFilename);

		try {
			InputStream inputStream = file.getInputStream();
			FileResponseInfo fileResponseInfo = storageProvider.upload(inputStream, extName);
			FileMeta fileMeta = new FileMeta.FileMetaBuilder()
					.key(fileResponseInfo.getFileKey())
					.size(fileSize)
					.build();
			fileMetaService.save(fileMeta);
		} catch (IOException e) {

			log.error("文件上传失败：{}",e.getMessage());
		}


		return null;
	}

	private StorageProvider getStorageProvider(StorageStrategy source)  {
		if (source == null){
			throw new StorageException("123");
		}
		switch (source){
			case ALIYUN:
				AliyunStoragePropertie aliyun = new AliyunStoragePropertie();
				//AliyunStoragePropertie aliyun = getObject(fileStorage.getProperties(),AliyunStoragePropertie.class);
				if (aliyun == null || !aliyun.validate()) {
					throw new StorageException("阿里云配置信息无效!");
				}
				return new AliyunStorageProvider(aliyun.getKey()
						,aliyun.getSecret()
						,aliyun.getEndpoint()
						,aliyun.getBucket()
						,aliyun.getPath());
			case LOCAL:
				return new LocalStorageProvider("data");
			default:
				throw new StorageException("暂不支持的文件上传方式");
		}
	}

	private <T> T getObject(String value, Class<T> valueType) {
		try {
			return objectMapper.readValue(value, valueType);
		} catch (IOException e) {
			throw new StorageException(e.getMessage(), e);
		}
	}
}

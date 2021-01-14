package work.ambitlu.storage.provider;

import org.apache.commons.lang3.StringUtils;
import work.ambitlu.exception.StorageException;
import work.ambitlu.storage.response.FileResponseInfo;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.*;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.UUID;

/**
 * 一些声明信息
 *
 * @author Ambi 赵帅
 * @date 2021/1/14 21:13
 */
public class LocalStorageProvider implements StorageProvider{


	private static final String SEPERATOR = FileSystems.getDefault().getSeparator();
	private final DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyyMMdd");

	/** 文件存储路径 */
	private final String path;

	/**
	 * 本地存储服务接口
	 * @param path 文件存储目录，不可为空，为空则抛出异常
	 */
	public LocalStorageProvider(String path) throws StorageException, IOException {
		super();
		if (path == null) {
			throw new StorageException("Storage path is NULL!");
		}

		this.path = path.endsWith(SEPERATOR) ? path : path + SEPERATOR;
		// 初始化目录
		//File file = new File(this.path);
		//if (!file.exists()) {
		//	boolean mkdirs = file.mkdirs();
		//}
		// 文件目录
		Path filePath = Paths.get(this.path);
		if (!Files.exists(filePath, LinkOption.NOFOLLOW_LINKS)) {
			Files.createDirectories(filePath);
		}
	}


	@Override
	public FileResponseInfo upload(InputStream in,String fileType) {
		String uuid = UUID.randomUUID().toString();
		String date = this.format.format(LocalDate.now());
		String fileKey = date + "-" + uuid;
		if (StringUtils.isNotBlank(fileType)){
			fileKey += "." + fileType;
		}
		try {
			Path filePath = getPath(fileKey);
			Files.copy(in, filePath, StandardCopyOption.REPLACE_EXISTING);
			return new FileResponseInfo(fileKey);
		} catch (IOException e) {
			throw new StorageException(e);
		}
	}

	/**
	 * 创建文件目录，文件按天存放
	 * @param fileKey 格式：yyyyMMdd-xxxxxxxx-xxxx-xxxx-xxxx-xxxxxxxxxxxx
	 */
	protected  Path getPath(String fileKey) throws IOException {
		String[] components = fileKey.split("-");
		String uuid = fileKey.substring(9);

		StringBuilder pathString = new StringBuilder(path).append(components[0]).append(SEPERATOR);

		// 文件目录
		Path filePath = Paths.get(pathString.toString());
		if (!Files.exists(filePath, LinkOption.NOFOLLOW_LINKS)) {
			Files.createDirectories(filePath);
		}
		// 文件路径
		String fileString = pathString.append(uuid).toString();
		return Paths.get(fileString);
	}

	public static void main(String[] args) {
		String date = DateTimeFormatter.ofPattern("yyyyMMdd").format(LocalDate.now());
		System.out.println(date);
	}

}

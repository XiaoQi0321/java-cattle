package work.ambitlu.storage;

import org.springframework.web.multipart.MultipartFile;
import work.ambitlu.storage.response.FileResponseInfo;

/**
 * 文件存储服务接口，隐藏文件保存的实现细节，业务模块直接引用该接口即可
 *
 * @author Ambi 赵帅
 * @date 2021/1/12 21:21
 */

public interface StorageService {

	FileResponseInfo upload(MultipartFile file);

}

package work.ambitlu.aliyun;

import work.ambitlu.storage.response.OssCallbackResult;

import javax.servlet.http.HttpServletRequest;

/**
 * Oss对象存储管理Service
 *
 * @author Ambi 赵帅
 * @date 2021/1/12 21:52
 */
public interface OssService {
	/**
	 * oss上传策略生成
	 */
	OssPolicyResult policy();
	/**
	 * oss上传成功回调
	 */
	OssCallbackResult callback(HttpServletRequest request);
}

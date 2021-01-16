package work.ambitlu.storage;

/**
 * 一些声明信息
 *
 * @author Ambi 赵帅
 * @date 2021/1/16 19:46
 */
public enum StorageStrategy {

	// 服务器提供源
	LOCAL("本地"),
	ALIYUN("阿里云");

	private StorageStrategy(String desc) {
	}

}

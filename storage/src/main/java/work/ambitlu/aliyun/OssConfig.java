package work.ambitlu.aliyun;

import com.aliyun.oss.OSSClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 一些声明信息
 *
 * @author Ambi 赵帅
 * @date 2021/1/12 22:19
 */
@Configuration
public class OssConfig {

	private String ALIYUN_OSS_ENDPOINT = "oss-cn-shanghai.aliyuncs.com";;
	private String ALIYUN_OSS_ACCESSKEYID = "LTAI4FymxGzN8gSq7PrgWnA8";;
	private String ALIYUN_OSS_ACCESSKEYSECRET = "Ecutud2CRSvoxIisdkIAjQfdqt5fHW";;

	@Bean
	public OSSClient ossClient(){
		return new OSSClient(ALIYUN_OSS_ENDPOINT,ALIYUN_OSS_ACCESSKEYID,ALIYUN_OSS_ACCESSKEYSECRET);
	}
}

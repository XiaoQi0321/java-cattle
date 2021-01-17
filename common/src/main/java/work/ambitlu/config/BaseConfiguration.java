package work.ambitlu.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.annotation.Configuration;
import work.ambitlu.common.Version;

import java.util.Properties;

/**
 * 系统环境
 *
 * @author Ambi 赵帅
 * @date 2021/1/17 16:52
 */
@Configuration
public class BaseConfiguration implements InitializingBean {
	protected static Logger logger = LoggerFactory.getLogger(BaseConfiguration.class);
	@Override
	public void afterPropertiesSet() {
		Properties props = System.getProperties();
		logger.error("=============>操作系统：" + props.getProperty("os.name") + " " + props.getProperty("os.arch") + " " + props.getProperty("os.version"));
		logger.error("=============>Java环境：" + props.getProperty("java.vendor") + " " + props.getProperty("java.version"));
		logger.error("=============>私有云版本：" + Version.SERVER);
	}
}

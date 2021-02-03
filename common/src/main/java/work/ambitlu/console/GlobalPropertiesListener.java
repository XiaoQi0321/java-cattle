package work.ambitlu.console;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import work.ambitlu.core.cache.GlobalPropertiesCache;
import work.ambitlu.core.entity.GlobalProperties;
import work.ambitlu.exception.BizException;

/**
 * 全局配置监听器
 * 容器启动完成后进行加载
 *
 * @Date: 2021/1/22 14:12
 * @Author: Ambi 赵帅
 */

@Component
public class GlobalPropertiesListener implements ApplicationListener<ApplicationReadyEvent> {

    @Autowired
    private GlobalPropertiesCache globalPropertiesCache;

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        GlobalProperties globalProperties = this.initProperties();
        boolean result = globalPropertiesCache.set(globalProperties);
        if(!result) {
            throw new BizException("加入缓存失败!");
        }
    }

    private GlobalProperties initProperties() {
        GlobalProperties globalProperties = new GlobalProperties();

        return globalProperties;
    }
}

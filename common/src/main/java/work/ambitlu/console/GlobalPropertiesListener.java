package work.ambitlu.console;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * 一些声明信息
 *
 * @Date: 2021/1/22 14:12
 * @Author: Ambi 赵帅
 */

@Component
public class GlobalPropertiesListener implements ApplicationListener<ApplicationReadyEvent> {
    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        System.out.println(event);
        System.out.println("GlobalPropertiesListener.onApplicationEvent");
    }
}

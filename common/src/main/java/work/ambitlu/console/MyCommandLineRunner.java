package work.ambitlu.console;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * 一些声明信息
 *
 * @Date: 2021/1/22 14:17
 * @Author: Ambi 赵帅
 */
@Component
public class MyCommandLineRunner implements CommandLineRunner {
    @Override
    public void run(String... args) throws Exception {
        System.out.println("MyCommandLineRunner.run");
    }
}

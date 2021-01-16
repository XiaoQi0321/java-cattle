package work.ambitlu.storage;

import cn.hutool.core.io.FileTypeUtil;
import cn.hutool.core.io.FileUtil;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;

/**
 * 一些声明信息
 *
 * @author Ambi 赵帅
 * @date 2021/1/16 11:55
 */
@SpringBootTest
public class StorageServiceTest {

	@Autowired
	private StorageService storageService;


	@Test
	public void mimeType(){
		File file = new File("C:\\Users\\Administrator\\Pictures\\girl\\183147-1526553107e340.jpg");


		String mimeType = FileUtil.getType(file);
		System.out.println(mimeType);
	}

}

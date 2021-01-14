package work.ambitlu.storage.provider;

import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import work.ambitlu.storage.response.FileResponseInfo;

import java.io.*;


/**
 * 一些声明信息
 *
 * @author Ambi 赵帅
 * @date 2021/1/14 22:23
 */
@Slf4j
@SpringBootTest
public class LocalStorageProviderTest {

	@Rule
	public ExpectedException thrown = ExpectedException.none();
	private LocalStorageProvider provider;

	@Before
	public void setUp() throws Exception {
		String path = System.getProperty("java.io.tmpdir");
		provider = new LocalStorageProvider(path);
	}


	@Test
	public void upload() throws Exception{
		//String text = "LocalStoragePovider";
		//byte[] bytes = text.getBytes();
		//InputStream input = new ByteArrayInputStream(bytes);
		File file = new File("C:\\Users\\Administrator\\Pictures\\sakura\\20200412171034.jpg");
		//InputStream input = new FileInputStream(file);
		BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file));
		FileResponseInfo upload = provider.upload(bis,"jpg");
		System.out.println(upload.getFileKey());
	}

}
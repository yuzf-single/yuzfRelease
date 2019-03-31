package test;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.junit.Test;

import sun.misc.BASE64Encoder;

public class CartoonTest {
	@Test
	public static void main(String[] args) {
		// 将图片文件转化为字节数组字符串，并对其进行Base64编码处理
	    String imgFile = "C:\\Users\\max\\Downloads\\0142《非常家庭》北条司 14卷完结 高清\\《非常家庭》北条司 14卷完结 高清\\001_第01卷\\第01卷_004.jpg";// 待处理的图片
	    InputStream in = null;
	    byte[] data = null;
	    // 读取图片字节数组
	    try {
	        in = new FileInputStream(imgFile);
	        data = new byte[in.available()];
	        in.read(data);
	        in.close();
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	    // 对字节数组Base64编码
	    BASE64Encoder encoder = new BASE64Encoder();
	    // 返回Base64编码过的字节数组字符串
	    System.out.println(encoder.encode(data));
	}
	

}

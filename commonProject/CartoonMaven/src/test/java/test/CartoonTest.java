package test;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.junit.Test;

import sun.misc.BASE64Encoder;

public class CartoonTest {
	@Test
	public static void main(String[] args) {
		// ��ͼƬ�ļ�ת��Ϊ�ֽ������ַ��������������Base64���봦��
	    String imgFile = "C:\\Users\\max\\Downloads\\0142���ǳ���ͥ������˾ 14����� ����\\���ǳ���ͥ������˾ 14����� ����\\001_��01��\\��01��_004.jpg";// �������ͼƬ
	    InputStream in = null;
	    byte[] data = null;
	    // ��ȡͼƬ�ֽ�����
	    try {
	        in = new FileInputStream(imgFile);
	        data = new byte[in.available()];
	        in.read(data);
	        in.close();
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	    // ���ֽ�����Base64����
	    BASE64Encoder encoder = new BASE64Encoder();
	    // ����Base64��������ֽ������ַ���
	    System.out.println(encoder.encode(data));
	}
	

}

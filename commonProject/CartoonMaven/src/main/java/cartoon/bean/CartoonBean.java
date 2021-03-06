package cartoon.bean;

import java.io.Serializable;

public class CartoonBean implements Serializable{

	private static final long serialVersionUID = -7740765187220751333L;
	
	private String rspCode;//���ر���
	
	private String rspDesc;//��������
	
	private String cartoonName;//������
	
	private String serialNumber;//�������
	
	private String readChaper;//�Ķ��½�
	
	private String downloadUrl;//��������

	public String getCartoonName() {
		return cartoonName;
	}

	public void setCartoonName(String cartoonName) {
		this.cartoonName = cartoonName;
	}

	public String getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}

	public String getReadChaper() {
		return readChaper;
	}

	public void setReadChaper(String readChaper) {
		this.readChaper = readChaper;
	}

	public String getDownloadUrl() {
		return downloadUrl;
	}

	public void setDownloadUrl(String downloadUrl) {
		this.downloadUrl = downloadUrl;
	}

	public String getRspDesc() {
		return rspDesc;
	}

	public void setRspDesc(String rspDesc) {
		this.rspDesc = rspDesc;
	}

	public String getRspCode() {
		return rspCode;
	}

	public void setRspCode(String rspCode) {
		this.rspCode = rspCode;
	}

}

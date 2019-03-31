package cartoon.bean;

import java.io.Serializable;

public class UserNeedBean implements Serializable{

	private static final long serialVersionUID = 9037492088357261754L;
	
	private String userName;
	
	private String userSuggest;
	
	private String userAge;
	
	private String cartoonName;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserSuggest() {
		return userSuggest;
	}

	public void setUserSuggest(String userSuggest) {
		this.userSuggest = userSuggest;
	}

	public String getUserAge() {
		return userAge;
	}

	public void setUserAge(String userAge) {
		this.userAge = userAge;
	}

	public String getCartoonName() {
		return cartoonName;
	}

	public void setCartoonName(String cartoonName) {
		this.cartoonName = cartoonName;
	}

}

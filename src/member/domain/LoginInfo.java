package member.domain;

public class LoginInfo {
	
	private String userId;
	private String userName;
	private String photo;
	
	
	public LoginInfo(String userId, String userName, String photo) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.photo = photo;
	}

	public String getUserId() {
		return userId;
	}

	public String getUserName() {
		return userName;
	}

	public String getPhoto() {
		return photo;
	}

	@Override
	public String toString() {
		return "LoginInfo [userId=" + userId + ", userName=" + userName + ", photo=" + photo + "]";
	}
	

	
	
	
}

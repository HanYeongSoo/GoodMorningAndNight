package member.domain;

public class EditRequest {
	
	private int idx;
	private String pw;
	private String username;
	private String fileName;
	
	public EditRequest() {}
	
	public EditRequest(int idx, String pw, String username, String fileName) {
		super();
		this.idx = idx;
		this.pw = pw;
		this.username = username;
		this.fileName = fileName;
	}

	public String getPw() {
		return pw;
	}

	public void setPw(String pw) {
		this.pw = pw;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public int getIdx() {
		return idx;
	}

	public void setIdx(int idx) {
		this.idx = idx;
	}

	@Override
	public String toString() {
		return "EditRequest [idx=" + idx + ", pw=" + pw + ", username=" + username + ", fileName=" + fileName + "]";
	}
	
	
	
	
}	

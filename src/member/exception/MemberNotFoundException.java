package member.exception;

public class MemberNotFoundException extends Exception {

	public MemberNotFoundException(String message) {
		super(message+"는 존재하지 않는 회원입니다.");
		
	}
	
}

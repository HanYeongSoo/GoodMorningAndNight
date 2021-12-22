package member.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Command {

	// 추상 메소드 : public abstract
	// 반환 데이터 : viewPage의 경로
	// 내부 처리    : 사용자가 요청하는 데이터 처리 -> 결과 data를 request의 속성에 save data
	
	String getPage(HttpServletRequest req, HttpServletResponse res);

}

package member.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class BadCommandimpl implements Command{

	@Override
	public String getPage(HttpServletRequest req, HttpServletResponse res) {
		System.out.println("으악!!!");

		// 사용자 요청 처리
		// JDBC, 데이터 분석, 다른 서버의 API처리 
		String str = "잘못된 요청입니다.";
		
		// 결과 데이터를 request 속성에 저장
		req.setAttribute("result", str);
		
		// view page 경로를 반환
		String viewPage = "/WEB-INF/views/simpleView.jsp";
		
		return viewPage;	
	}

}

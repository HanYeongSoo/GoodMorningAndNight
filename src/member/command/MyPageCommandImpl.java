package member.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import member.service.MyPageService;

public class MyPageCommandImpl implements Command {

	@Override
	public String getPage(HttpServletRequest req, HttpServletResponse res) {
		
		// 세션에 로그인 정보가 있는지 여부를 확인
		
		boolean loginChk = false;
		HttpSession session = req.getSession();
		
		if (session != null && session.getAttribute("loginInfo") != null) {
			loginChk = true;
			
			// 로그인 한 회원의 Member 객체 생성하고 결과 데이터 view로 전달
			req.setAttribute("member", MyPageService.getInstance().myPageInfo(req));
		}
		
		req.setAttribute("loginChk", loginChk);
		
		return "/WEB-INF/views/member/my/mypage.jsp";
	}

}

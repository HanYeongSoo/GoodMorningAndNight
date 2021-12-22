package member.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import member.service.MemberRegService;

public class MemberRegCommandImpl implements Command {

	@Override
	public String getPage(HttpServletRequest req, HttpServletResponse res) {
		// 회원 가입을 위한 form (get) -> 가입처리 (post)
		
		String view = null;
		
		if (req.getMethod().equals("GET")) {
			view = "/WEB-INF/views/member/regform.jsp";
			
		} else if(req.getMethod().equals("POST")) {
//			int cnt = MemberRegService.getInstance().insertMember(req, res);
			
			// request 속성 처리
//			req.setAttribute("result", cnt);
			req.setAttribute("result", MemberRegService.getInstance().insertMember(req, res));
			
			
			view = "/WEB-INF/views/member/reg.jsp";
		}
		
		// request 속성 처리
		
		return view;
	}

}

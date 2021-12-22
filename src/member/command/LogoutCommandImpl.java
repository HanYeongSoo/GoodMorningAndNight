package member.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LogoutCommandImpl implements Command {

	@Override
	public String getPage(HttpServletRequest req, HttpServletResponse res) {
		
		req.getSession().invalidate();
		
		return "/WEB-INF/views/member/logout.jsp";
	}

}

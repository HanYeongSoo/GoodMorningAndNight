package member.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MemberIndexCommandImpl implements Command{
	
	@Override
	public String getPage(HttpServletRequest request, HttpServletResponse response) {
		
		return "/WEB-INF/views/member/index.jsp";
	}
}

package member.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import member.service.MemberEditService;

public class MemberEditCommandImpl implements Command {

	@Override
	public String getPage(HttpServletRequest req, HttpServletResponse res) {
		
		String view = null;	
		
		if (req.getMethod().equals("GET")) {
			
			String index = req.getParameter("idx");
			
//			System.out.println(MemberEditService.getInstance().getMember(Integer.parseInt(index)));
			req.setAttribute("member", MemberEditService.getInstance().getMember(Integer.parseInt(index)));
			
			view = "/WEB-INF/views/member/manager/editform.jsp";
			
		} else if (req.getMethod().equals("POST")) {
			
			req.setAttribute("result", MemberEditService.getInstance().editMember(req,res));
			
			view = "/WEB-INF/views/member/manager/edit.jsp";
		}
		
		return view;
	}

}

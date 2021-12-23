package member.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import member.service.MemberListService;

public class MemberListCommandImpl implements Command {

	@Override
	public String getPage(HttpServletRequest req, HttpServletResponse res) {
		
		// http://localhost:8181/op/member/manager/list.do?p=숫자
		String pageNumber = req.getParameter("p");
		int pageNum = 1; // 0페이지는 없으니까 기본이 1페이지
		
		if (pageNumber != null && pageNumber.length() > 0) {
			try {
				pageNum = Integer.parseInt(pageNumber);
			} catch (NumberFormatException e) {
				e.printStackTrace();
			}
			
		}
		
		req.setAttribute("listView", MemberListService.getInstance().getPage(pageNum));
		
//		System.out.println(MemberListService.getInstance().getPage(1));	페이지 요청 시 잘 나오는 지 확인
		
		return "/WEB-INF/views/member/manager/list.jsp";
	}

}

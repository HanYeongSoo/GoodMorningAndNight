package member.service;

import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jdbc.ConnectionProvider;
import jdbc.util.JdbcUtil;
import member.dao.MemberDao;
import member.domain.Member;

public class LoginService {
	
	private LoginService() {
		
	}
	private static LoginService service = new LoginService();
	public static LoginService getInstance() {
		return service;
	}
	
	// 로그인 체크 후 Member 객체를 반환하는 메소드
	
	public Member login(HttpServletRequest request, HttpServletResponse response) throws SQLException {
		Member member = null;
		
		// 데이터 받기
		String userId = request.getParameter("userid");
		String pw = request.getParameter("pw");
		String saveId = request.getParameter("saveid");
		
		// dao.selectByIdPw()
		Connection conn = null;
		
		try {
			conn = ConnectionProvider.getConnection();
			
			member = MemberDao.getInstance().selectByIdPw(conn, userId, pw);
			
			// 인증이 되었다면 쿠키 설정
			if (member != null && saveId != null && saveId.equals("on")) {
				Cookie cookie = new Cookie("saveId", userId);
				cookie.setPath("/");
				cookie.setMaxAge(60*60*24*365);		// 초 단위
				
				response.addCookie(cookie);
			} else if (member != null && (saveId == null || !saveId.equals("on"))) {
				Cookie cookie = new Cookie("saveId", userId);
				cookie.setPath("/");
				cookie.setMaxAge(0);		// 초 단위
				
				response.addCookie(cookie);
			}
			
		} finally {
			JdbcUtil.close(conn);
		}
		
		
		return member;
	}
	
}

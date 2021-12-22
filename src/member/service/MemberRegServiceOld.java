package member.service;

import java.sql.Connection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jdbc.ConnectionProvider;
import member.dao.MemberDao;
import member.domain.Member;
import member.domain.RegRequest;

public class MemberRegServiceOld {
	
	// Service 클래스도 메소드만 있는 클래스다! -> 싱글톤
	private MemberRegServiceOld() {
		
	}
	private static MemberRegServiceOld regService = new MemberRegServiceOld();
	public static  MemberRegServiceOld getInstance() {
		return regService;
	}
		
	
	// 사용자가 전달한 요청 데이터를 받아서 DAO insert 메소드로 전달
	// 입력결과 반환하는 메소드, 사용자 요청 처리, 응답 처리
	public int insertMember(HttpServletRequest request, HttpServletResponse response) {
		
		// 파일업로드 추가 (2021.12.21)
		// 결과 생성
		// Connection 객체 생성 -> Dao.insertMember(conn, member)
		
		// 데이터받기 (나중에 스프링컨테이너가 처리)
		String userId = request.getParameter("userid");
		String pw = request.getParameter("pw");
		String userName = request.getParameter("username");
		
//		RegRequest regRequest = new RegRequest(userId, pw, userName);
		
		Member member = null;
//		member = regRequest.getMember(); 
		
		// Dao 호출
		Connection conn = null;
		int resultCnt = 0;
		
		try {
			conn = ConnectionProvider.getConnection();
			
//			resultCnt = MemberDao.getInstance().insertMember(conn, member);
//			resultCnt = MemberDao.getInstance().insertMember(conn, regRequest.getMember());
		} catch (Exception e) {
			resultCnt = -1;
			e.printStackTrace();
		}
		
		return resultCnt;
	}
}

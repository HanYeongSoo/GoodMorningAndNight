package member.service;

import java.io.File;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jdbc.ConnectionProvider;
import jdbc.util.JdbcUtil;
import member.dao.MemberDao;
import member.domain.Member;
import member.exception.MemberNotFoundException;

public class MemberDeleteService {
	
	private MemberDeleteService() {}
	private static MemberDeleteService service = new MemberDeleteService();
	public static MemberDeleteService getInstance() {
		return service;
	}
	
	// 사용자의 요청 idx를 받아서 삭제 결과를 반환
	public int deleteMember(HttpServletRequest request, HttpServletResponse response) throws SQLException, MemberNotFoundException {
		
		int resultCnt = 0;
		
		// 1. idx로 회원이 존재하는지 유무 확인, 파일 이름 삭제시 사용
		// 	if 존재하지 않으면 MemberNotFoundException 만듬
		//		if 존재 시 행 삭제, 파일 확인 후 삭제
		
		String idx = request.getParameter("idx");
		
		Member member = null;
		Connection conn = null;
		
		try {
			conn = ConnectionProvider.getConnection();
			
			member = MemberDao.getInstance().selectByIdx(conn, (Integer.parseInt(idx)));
			
			//회원 정보가 존재하지 않으면 예외 발생
			if (member == null) {
				throw new MemberNotFoundException(idx);
			}
			
			// 여기까지 문제 없다면 삭제하고자 하는 회원이 존재 : delete, remove
			resultCnt = MemberDao.getInstance().deleteMemberByIdx(conn, (Integer.parseInt(idx)));
			
			// 회원의 프로필 사진을 삭제
			// 먼저 시스템경로 구하기
			String uploadURI = "/uploadfile";
			String saveDir = request.getSession().getServletContext().getRealPath(uploadURI);
			
			// File객체 생성
			if (member != null && !member.getPhoto().equals("starwars.png")) {
				File delFile = new File(saveDir, member.getPhoto());
				// 저장 공간에 파일이 존재하는지 확인
				if (delFile.exists()) {
					delFile.delete();
				} 
			}
			
		} finally {
			JdbcUtil.close(conn);
		}
		
		return resultCnt;
	}
}

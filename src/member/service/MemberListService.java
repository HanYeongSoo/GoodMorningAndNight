package member.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import jdbc.ConnectionProvider;
import member.dao.MemberDao;
import member.domain.Member;
import member.domain.MemberListView;

public class MemberListService {
	
	private MemberListService() {
		
	}
	private static MemberListService service = new MemberListService();
	public static MemberListService getInstance() {
		return service;
	}
	
	public MemberListView getPage(int pageNum) {
		MemberListView listView = null;
		Connection conn = null;
		
		try {
			conn = ConnectionProvider.getConnection();
			
			// 현재 페이지 번호
			int currentPage = pageNum;
			
			// 페이지 당 표현할 개수
			final int COUNT_PER_PAGE = 5;
			
			// 항목 가져오기 : limit index
			int index = (currentPage - 1) * COUNT_PER_PAGE;
			
			// List<Member> 구하기
			List<Member> list = MemberDao.getInstance().selectList(conn, index, COUNT_PER_PAGE);
			
			// 전체 회원의 수 구하기
			int totalCount = MemberDao.getInstance().selectTotalCount(conn);
			
			listView = new MemberListView(totalCount, COUNT_PER_PAGE, currentPage, list);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return listView;
	}
}

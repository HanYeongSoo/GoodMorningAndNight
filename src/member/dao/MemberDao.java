package member.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import jdbc.util.JdbcUtil;
import member.domain.Member;
import member.domain.RegRequest;

public class MemberDao {

	// 싱글톤 처리 시작
	private MemberDao() {

	}

	private static MemberDao dao = new MemberDao();

	public static MemberDao getInstance() {
		return dao;
	}
	// 싱글톤 처리 끝

	PreparedStatement pstmt = null;
	ResultSet rs = null;
	Statement stmt = null;

	// Connection 객체, Member 객체를 전달 받아 데이터베이스에 데이터 입력
	public int insertMember(Connection conn, RegRequest request) throws SQLException {
		int resultCnt = 0;

		String sql = "INSERT INTO member (userid, password, username, photo) VALUES (?,?,?,?)";

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, request.getUserid());
			pstmt.setString(2, request.getPw());
			pstmt.setString(3, request.getUsername());
			pstmt.setString(4, request.getFileName());
			resultCnt = pstmt.executeUpdate();
		} finally { // 예외가 발생해도 무조건 실행
			JdbcUtil.close(pstmt);
		}

		return resultCnt;
	}

	// 로그인 처리를 위한 select 메소드
	public Member selectByIdPw(Connection conn, String userId, String pw) throws SQLException {
		Member member = null;

		String sql = "SELECT * FROM member WHERE userid=? AND password=?"; // userid는 유니크기 때문에 여기선 결과가 1개 아니면 안나오게 되어있음

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userId);
			pstmt.setString(2, pw);

			rs = pstmt.executeQuery();

			if (rs.next()) {
//				member = new Member(rs.getInt("idx"), // 1
//						rs.getString("userid"), // 2
//						rs.getString("password"), // 3
//						rs.getString("username"), // 4
//						rs.getString("regdate"), // 5
//						rs.getString("photo")); // 6
				member = getMember(rs);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(pstmt);
			JdbcUtil.close(rs);
		}

		return member;
	}

	public Member selectById(Connection conn, String userId) throws SQLException {
		
		Member member = null;

		String sql = "SELECT * FROM member WHERE userid=?"; // userid는 유니크기 때문에 여기선 결과가 1개 아니면 안나오게 되어있음

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userId);

			rs = pstmt.executeQuery();

			if (rs.next()) {
//				member = new Member(rs.getInt("idx"), // 1
//						rs.getString("userid"), // 2
//						rs.getString("password"), // 3
//						rs.getString("username"), // 4
//						rs.getString("regdate"), // 5
//						rs.getString("photo")); // 6
				member = getMember(rs);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(pstmt);
			JdbcUtil.close(rs);
		}

		return member;
	}

	public List<Member> selectList(Connection conn, int index, int countPerPage) throws SQLException {
		List<Member> list = new ArrayList<Member>();
		
		String sql = "SELECT * FROM member ORDER BY regdate desc limit ?, ?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, index);
			pstmt.setInt(2, countPerPage);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				
//				Member member = new Member(
//												rs.getInt("index"), 
//												rs.getString("userid"), 
//												rs.getString("pw"), 
//												rs.getString("username"), 
//												rs.getString("regdate"), 
//												rs.getString("photo"));
				
				list.add(getMember(rs));
			}
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	
		
		return list;
	}
	
	private Member getMember(ResultSet rs) throws SQLException {
		
		return new Member(
				rs.getInt("idx"), 
				rs.getString("userid"), 
				rs.getString("password"), 
				rs.getString("username"), 
				rs.getString("regdate"), 
				rs.getString("photo"));
	}

	public int selectTotalCount(Connection conn) throws SQLException {
		int totalCount = 0;
		
		String sql = "SELECT count(*) FROM member";
		
		// 미리 만들어둔 sql 에서 가져오는건 pstmt가 아니고 stmt
		stmt = conn.createStatement();
		rs = stmt.executeQuery(sql);
		
		try {
			// while을 쓸 필요가 없는게 어짜피 행이 1개나옴 count라
			if (rs.next()) {
				totalCount = rs.getInt(1);
			}
		}  finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(stmt);
		}
		
		
		return totalCount;
	}

}

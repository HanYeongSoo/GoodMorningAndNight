package jdbc;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;


public class DriverLoader extends HttpServlet {
	
	public void init(ServletConfig config) throws ServletException {
		String driver = config.getInitParameter("driver");	// param-value값이 들어온다  -> com.mysql.cj.jdbc.Driver
		
		try {
			Class.forName(driver);
			System.out.println("mysql 드라이버 로드 성공!!");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.out.println("mysql 드라이버 로드 실패 ㅠㅠ");
		}
	}
	
}

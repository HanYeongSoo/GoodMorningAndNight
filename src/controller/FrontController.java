package controller;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import member.command.BadRequestCommandImpl;
import member.command.Command;

public class FrontController extends HttpServlet  {
	
	private Map<String, Command> commands = new HashMap<String, Command>();

	@Override
	public void init(ServletConfig config) throws ServletException {
			
		System.out.println("서블릿이 생성될때 한번 실행합니다 -> 초기화 처리 합니다!!!");
		
		// 설정 파일에 init-param 설정된 경로 가져오기
		String configFile = config.getInitParameter("configPath");
		// 시스템 경로 실제 경로
		String configPath = config.getServletContext().getRealPath(configFile);
		
				
		// 프로퍼티(MAP 파생된 클래스) 파일 생성
		// 파일 -> 객체
		
		Properties prop = new Properties();
		FileInputStream fis = null;
		
		try {
			fis = new FileInputStream(configPath);
			
			// 파일 -> 객체 Properties
			prop.load(fis);
			
			fis.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		Iterator<Object> itr = prop.keySet().iterator();
		
		while(itr.hasNext()) {
			
			String command = (String) itr.next();
			String commandClassName = prop.getProperty(command);
			
			System.out.println(command+"="+commandClassName);
			
			// commands Map 에 command(key), commandClassName의 인스턴스를 저장
			
			// commandClassName 이름의 클래스로 인스턴스 생성 -> Class 클래스를 이용
			
			try {
				// 클래스 이름(full name)으로 인스턴스 생성
				Class commandClass = Class.forName(commandClassName);
				
				// 인스턴스 생성
				Command commandObj = (Command)commandClass.newInstance();
				
				// commands Map에 <요청, 객체> 저장
				commands.put(command, commandObj);
				
				
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (InstantiationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		
		
		
		
		
		// Command 설정
		//commands.put("/date.do", new DateCommandImpl());
		//commands.put("/greeting.do", new GreetingCommandImpl());
		
		System.out.println("초기화된 요청 : " + commands.size());
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1. 사용자 요청 받기
		doProcess(request, response);		
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1. 사용자 요청 받기
		doProcess(request, response);
	}

	private void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 2. 사용자의 요청을 분석 : URI를 이용한 사용자 요청 분석
		
		String commandURI = request.getRequestURI();
		// http://localhost:8080/mvc/date.do : commandURI => /mvc/date.do
		// http://localhost:8080/mvc/greeting.do : commandURI => /mvc/greeting.do
		
		// request.getContextPath() => /mvc
		if(commandURI.startsWith(request.getContextPath())) {
			commandURI = commandURI.substring(request.getContextPath().length());
		}
		// http://localhost:8080/mvc/date.do : commandURI => /date.do
		// http://localhost:8080/mvc/greeting.do : commandURI => /greeting.do
				
		
		
		
		// 3. 사용자 요청에 맞는 데이터 처리 : 요청에 따른 분기
		// 결과 데이터를 생성
		
		// 응답 결과 객체
		//Object result = null;  // view 페이지에서 toString()으로 출력
		// view page 경로
		
		Command command = commands.get(commandURI);
		if(command == null) {
			command = new BadRequestCommandImpl();
		}
		
		
//		if(commandURI.equals("/greeting.do")) {
//			command = new GreetingCommandImpl();
//		} else if(commandURI.equals("/date.do")) {
//			command = new DateCommandImpl();
//		} else { // /*.do
//			command = new BadRequestCommandImpl();
//		}
		
		String viewPage = command.getPage(request, response);
		
		// 5.  view 페이지를 지정(선택) -> 포워딩
		RequestDispatcher dispatcher = request.getRequestDispatcher(viewPage);
		dispatcher.forward(request, response);
		
	}
	
	
}

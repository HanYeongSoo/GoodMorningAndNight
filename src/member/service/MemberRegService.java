package member.service;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import jdbc.ConnectionProvider;
import jdbc.util.JdbcUtil;
import member.dao.MemberDao;
import member.domain.RegRequest;


public class MemberRegService {
	
	// Service 클래스도 메소드만 있는 클래스다! -> 싱글톤
	private MemberRegService() {
		
	}
	private static MemberRegService regService = new MemberRegService();
	public static  MemberRegService getInstance() {
		return regService;
	}
		
	
	// 사용자가 전달한 요청 데이터를 받아서 DAO insert 메소드로 전달
	// 입력결과 반환하는 메소드, 사용자 요청 처리, 응답 처리
	public int insertMember(HttpServletRequest request, HttpServletResponse response) {
		int resultCnt = 0;
		
		// 파일업로드 추가 (2021.12.21)
		// 파라미터 받기, 파일 업로드, 저장 파일 이름을 생성
		// RegRequest 객체 생성
		
		String userId = null;
		String pw = null;
		String userName = null;
		String fileName = null;		// 파일이 있으면 업로드 없으면 이름 저장
		
		File newFile = null;
		Connection	 conn = null;
		
		// 1. multipart 여부 확인
					boolean isMultipart = ServletFileUpload.isMultipartContent(request);
					
					if (isMultipart) {
						
						// 2. 파일 저장을 위한 Factory 객체 생성
						DiskFileItemFactory factory = new DiskFileItemFactory();

						// 3. 요청을 구분( form 안에 있는 input 들을 분리 )
						ServletFileUpload upload = new ServletFileUpload(factory);
						
						try {
							List<FileItem> items = upload.parseRequest(request);	// form에서 넘어오는 파라미터값들을 List FileItem형식으롤 감싸줌
							
							Iterator<FileItem> itr = items.iterator();
							
							while (itr.hasNext()) {
								FileItem item = itr.next();
								
								// 일반 파라미터와 file을 구분해서 처리
								if (item.isFormField()) {
									// isFormField() 면 일반 파라미터
									// 파라미터 이름에 맞는 데이터를 받고 변수에 저장
									String paramName = item.getFieldName();
									
									if (paramName.equals("userid")) {
										userId = item.getString("UTF-8");
									} else if (paramName.equals("pw")) {
										pw = item.getString("UTF-8");
									} else if (paramName.equals("username")) {
										userName = item.getString("UTF-8");
									} 
									
								} else {
									// if문에 해당이 아니면 file
									
									if (item.getFieldName().equals("photo") && item.getSize() > 0) {
										// 웹 저장경로
										String uploadURI = "/uploadfile";
										// 시스템 경로
										String saveDir = request.getSession().getServletContext().getRealPath(uploadURI);
										System.out.println(saveDir);
										// 새로운 파일 이름은 중복되지 않는 숫자로 생성
										String newFileName	=	String.valueOf(System.nanoTime());
										
										// file 저장을 위한 File 객체 생성
										newFile = new File(saveDir, newFileName);
										
										// 파일 저장
										item.write(newFile);		// catch Exception 관련
										System.out.println("파일저장완료");
										
										// 파일 이름 데이터 처리
										fileName = newFileName;	
									}
								}
							}	// try문 끝
							// try문 깔끔하게 처리됐다면 여기서 Dao 호출
							
							if (fileName == null) {
								fileName = "starwars.png";
							}
							
							conn = ConnectionProvider.getConnection();		// catch SQLException 관련
							
							RegRequest regRequest = new RegRequest(userId, pw, userName, fileName);
							resultCnt = MemberDao.getInstance().insertMember(conn, regRequest);	
							
						} catch (FileUploadException  e) {
							e.printStackTrace();
						} catch (UnsupportedEncodingException e) {
							e.printStackTrace();
						} catch (SQLException e) {
							e.printStackTrace();
							// 저장된 파일이 있다면 삭제
							if (newFile != null && newFile.exists()) {
								newFile.delete();
							}
						} catch (Exception e) {
							e.printStackTrace();
						} finally {
							JdbcUtil.close(conn);
						}
							
					}
					
		return resultCnt;
	}
}

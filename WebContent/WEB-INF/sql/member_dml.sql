-- insert
INSERT INTO member(userid,password,username) VALUES (?,?,?);

-- select
SELECT * FROM member;
SELECT * FROM member WHERE userid=? AND password=?;
SELECT * FROM member WHERE userid='test@naver.com' AND password=1234567;

-- 사진 파일 저장되는 위치
-- C:\Users\YeongSoo\Desktop\springEclipse\JSP-blog\.metadata\.plugins\org.eclipse.wst.server.core\tmp0\wtpwebapps\OpenProject\uploadfile
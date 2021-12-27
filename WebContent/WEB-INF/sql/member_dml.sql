-- insert
INSERT INTO member(userid,password,username) VALUES (?,?,?);

-- select
SELECT * FROM member;
SELECT * FROM member WHERE userid=? AND password=?;
SELECT * FROM member WHERE userid='test@naver.com' AND password=1234567;

-- limit index, count
-- 계산 방식(index) : (page - 1) * 페이지표현갯수       -->> index는 0부터고 page는 1부터니까!
SELECT * FROM member limit 0, 2; -- 1페이지
SELECT * FROM member limit 2, 2; -- 2페이지
SELECT * FROM member ORDER BY regdate desc limit ?, ?;

-- idx로 특정 회원 조회 후 수정
SELECT * FROM member WHERE idx=4;

-- total개수 가져오기
SELECT count(*) FROM member; 




-- 사진 파일 저장되는 위치
-- C:\Users\YeongSoo\Desktop\springEclipse\JSP-blog\.metadata\.plugins\org.eclipse.wst.server.core\tmp0\wtpwebapps\OpenProject\uploadfile


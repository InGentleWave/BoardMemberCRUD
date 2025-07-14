package kr.or.ddit.controller.chapt08;

public class Chapter08MybatisController {
	/*
		[ 8장. 마이바티스 ]
		
		1. Oracle Database 설치
		
			- https://junesker.tistory.com/98 (Oracle 23c 설치 (Oracle-23ai-Free 버전))
			- https://junesker.tistory.com/99 (SqlDeveloper 이용하여 Oracle 23c 시스템 계정 접속)
		
		2. Oracle SQLDeveloper 설치
			
			# 오라클 SqlDeveloper 설치 참고
			- https://junesker.tistory.com/81 (SqlDeveloper 설치)
			
		3. 데이터 소스 설정
		
			- SpringBoot가 아닌 일반적인 Spring Legacy 설정에서는 xml을 활용하여 dataSource를 설정한다.
				dataSource를 설정하기 위한 여러가지 자원들이 존재하는데, mybatis 설정, db 접속정보, mapper 위치 등을 활용해
				SqlSessionFactory빈 생성을 통해 SqlSessionTemplate라는 최종 생산품에 해당하는 객체를 만들 수 있다.
				SqlSessionTemplate 객체는 데이터베이스 접속 정보, mapper의 위치, mybatis의 설정 정보를 가지고 있기 때문에
				요청하고자 하는 기능에 따른 쿼리의 결과를 요청할 수 있다.
				SpringBoot는 이와 같은 설정 자체가 이미 시스템화 되어 있다. 우리가 별 다른 설정을 하지 않아도 이미 설정이 되어 있기
				때문에 설정에 번거로움과 설정에 어려움이 많이 줄어 들었다.
				
			- application.properties 설정
			- oracle database 및 mybatis 설정
			
		4. JPA(Java Persistence API)
		
			- JPA는 자바 표준 ORM입니다.
				즉, 구현되어 있는 클래스에 테이블을 매핑하기 위한 프레임워크입니다.
			
			# [장점]
			- SQL 위주의 쿼리를 작성하지 않아도 된다.
			- 코드량이 엄청 줄어든다.(생산성)
			- 객체위주의 코드 작성으로 가독성이 좋아진다.(생산성)
			- 객체를 사용하는 시점에 쿼리를 전달하거나 동일한 트랜잭션 안에서 처리 내용의 같음을 보장한다. (패러다임의 불일치 해결)
			
				*** 패러다임의 불일치 해결
				객체지향 프로그래밍과 관계형 데이터베이스(RDB) 사이의 구조적인 차이를 극복할 수 있다는 의미로 Java는 객체 지향 언어이고 RDB는 관계형 모델을 사용하는데
				객체지향 언어는 객체 구조로 구성되어 있는데 반면 관계형 DB는 테이블 구조로 구성되어 있다. 이미 여기서부터 구조 방식의 불일치가 일어난다. 
				그런데 이런 구조적인 부분, 관계, 상속 등등에서 발생하는 불일치 현상들을 JPA는 자동으로 매핑해줌으로써 패러다임의 불일치를 해결한다.
				> 객체 <---> 테이블
				> 상속 <---> 단일 테이블 / JOIN
				
			# Entity
			- 데이터베이스에서 지속적으로 저장된 데이터를 자바 객체에 매핑한 것이다.
			- 메모리 상에 자바 객체의 인스턴스 형태로 존재하며 EntityManager에 의해 데이터베이스의 데이터와 동기화한다.
			
			# EntityManager
			- 필요에 따라 Entity와 데이터베이스의 데이터를 동기화한다.
			- EntityManager에서 제공하는 Entity 조작 API를 이용하여 Entity에 대해 CRUD 작업을 할 수 있다.
			
			# Entity 상태
			- new 상태
			- 관리 상태
			- 분리 상태
			- 삭제된 상태
			
			# JPA관련 어노테이션
			자바빈즈 클래스 객체가 곧 테이블 구조와 같은 형태이기 때문에, VO 클래스 내 @Entity 어노테이션을 통해 해당 객체가 테이블과 같은 Entity임을 설정하고
			@Table 어노테이션으로 테이블명을 설정해주면 VO 클래스는 데이터베이스의 생성되어있는 테이블과 연결되기 위한 준비가 된다.
			
			ex)
			@Entity
			@Table(name="board")
			public class Board{...}
			
			그리고 각 필드(멤버변수)에 @Column(name="board_no") 어노테이션으로 테이블 컬럼과 매핑 설정을 한다.
			설정된 Entity들을 이용하여 JPA 객체를 통해서 함수를 호출하면(persist, find 등등)
			호출된 함수를 통해 Spring Data JPA가 자동으로 Entity를 분석하여 함수에 따른 쿼리를 자동생성하여 결과를 실행, 조회, 수정 등을 처리해준다.
			기존 SQL 쿼리를 이용하여 서비스를 처리할 때에 필드가 추가되면
			필드와 관련되어 있는 기능, SQL, 추가된 필드 등을 수정해야 하는 번거로움이 발생하지만
			JPA는 필드만 추가 한 후, 테이블 정보와 매핑만 시켜주면 JPA가 자동으로 분석을 진행한다.
			그리고, 사용하는 데이터베이스 종류가 변경된다 하더라도 문제가 없다.
			(JPA는 SQL을 직접 건드리지 앟고 함수와 같은 기능들로만 쿼리를 요청하고 데이터베이스와 통신하기 때문)
			
			JPA는 JPA구현체인 hibernate를 이용하여 사용합니다. 그러기 위해서는 관련 의존관계를 등록 후 사용해야 합니다.
			SQL문을 이용하여 데이터베이스를 연동하여 데이터를 가공하는게 아닌, Method를 통해 데이터베이스를 조작할 수 있다는 장점이 있어
			객체모델을 이용하여 비즈니스 로직을 구성하는데에만 집중을 할 수 있습니다. (쿼리를 직접 구성하지 않기 때문이죠.)
			하지만, *** 프로젝트의 규모와 복잡도가 클 때는 설계가 잘 못 되는 경우 해당 프로젝트의 품질이 떨어진다는 문제가 있습니다.
			그만큼 설계라인의 정확성이 필요하고 여러 방면에서 정확한 검증이 제대로 끝나지 않으면 적용하기가 까다롭습니다.
			
			[용어]
			*** ORM 이란?
				- 객체에 데이터를 읽고 쓰는 방법으로 관계형 데이터베이스에 데이터를 읽고 쓰는 기술입니다.
				
			***	기업 면접에서 ORM은 뭐 쓰셨어요? 라는 질문을 받는 경우가 있다.
				- 저는 mybatis를 사용했습니다. 와 같은 대답을 하면 됩니다.
				
		5. 마이바티스란?
		
			마이바티스는 자바 퍼시스턴스 프레임워크의 하나로 XML 서술자나 어노테이션을 사용하여 저장 프로시저나 SQL문으로 객체들을 연결시킨다.
			마이바티스는 Apache 라이선스 2.0으로 배포되는 자유 소프트웨어입니다.
			
			1) 마이바티스를 사용함으로써 얻을 수 있는 이점
			- SQL의 체계적인 관리
			- 자바 객체의 SQL 입출력 값의 투명한 바인딩
			- 동적 SQL 조합
			
			2) 마이바티스 설정
			
				# mybatis 설정
				2-1) pom.xml 설정
				- starter project 생성 시, 추가한 dependency
					> mybatis-spring-boot-starter
					> spring-boot-starter-jdbc
					
				2-2) application.properties 설정
				- datasource, mybatis 설정
			
			3) 마이바티스 플러그인 설정
			
				# Mybatipse 설정
				- Help > Eclipse Marketplace > mybatipse 검색 > install
				
			4) 관련 테이블 생성
			
				4-1) board 테이블 생성
				4-2) member 테이블 생성
				4-3) member_auth 테이블 생성
				
			5) log 출력 형태 변경
			
				5-1) pom.xml 설정
				- p6spy-spring-boot-starter 라이브러리 추가
				
				5-2) application.properties 설정
				- 기존 설정했던 logging.level 부분을 info level로 변경
				- 새롭게 추가한 logging.level.p6spy=debug level로 변경
		
		6. Mapper 인터페이스
		
			- 인터페이스의 구현을 mybatis-spring에서 자동으로 생성할 수 있다.
			
			1) 마이바티스 구현
			
				1-1) Mapper 인터페이스
				- kr.or.ddit.controller.chapt08.board.mapper 패키지
				- IBoardMapper.java 생성 (인터페이스)
				
				1-2) Mapper 인터페이스 매핑할 Mapper
				- mybatis/mapper/board_Mapper.xml 생성
				
				1-3) 게시판 구현 설명
				- 게시판 컨트롤러 만들기 (CrudBoardController.java)
				- 게시판 등록 화면 컨트롤러 메소드 만들기 (CrudRegister:get)
				- 게시판 등록 화면 만들기 (crud/board/register.jsp)
				- 여기까지 확인
				
				- 게시판 등록 기능 컨트롤러 메소드 만들기 (crudRegister:post)
				- 게시판 등록 기능 서비스 인터페이스 메소드 만들기
				- 게시판 등록 기능 서비스 클래스 메소드 만들기
				- 게시판 등록 기능 Mapper 인터페이스 메소드 만들기
				- 게시판 등록 기능 Mapper xml 쿼리 만들기
				- 게시판 등록 완료 페이지 만들기(crud/board/read.jsp)
				- 여기까지 확인
				
				- 게시판 목록 기능 컨트롤러 메소드 만들기 (crudList:get)
				- 게시판 목록 기능 서비스 인터페이스 메소드 만들기
				- 게시판 목록 기능 서비스 클래스 메소드 만들기
				- 게시판 목록 기능 Mapper 인터페이스 메소드 만들기
				- 게시판 목록 기능 Mapper xml 쿼리 만들기
				- 게시판 목록 화면 페이지 만들기(crud/board/list.jsp)
				- 여기까지 확인
				
				- 게시판 상세 화면 컨트롤러 메소드 만들기 (crudList:get)
				- 게시판 상세 화면 서비스 인터페이스 메소드 만들기
				- 게시판 상세 화면 서비스 클래스 메소드 만들기
				- 게시판 상세 화면 Mapper 인터페이스 메소드 만들기
				- 게시판 상세 화면 Mapper xml 쿼리 만들기
				- 게시판 상세 화면 페이지 만들기(crud/board/read.jsp)
				- 여기까지 확인
				
				- 게시판 수정 화면 컨트롤러 메소드 만들기 (crudModify:get)
				- 게시판 수정 화면 서비스 인터페이스 메소드 만들기
				- 게시판 수정 화면 서비스 클래스 메소드 만들기
				- 게시판 수정 화면 Mapper 인터페이스 메소드 만들기
				- 게시판 수정 화면 Mapper xml 쿼리 만들기
				- 게시판 수정 화면 페이지 만들기(crud/board/register.jsp)
				- 게시판 수정 기능 컨트롤러 메소드 만들기 (crudModify:post)
				- 게시판 수정 기능 서비스 인터페이스 메소드 만들기
				- 게시판 수정 기능 서비스 클래스 메소드 만들기
				- 게시판 수정 기능 Mapper 인터페이스 메소드 만들기
				- 게시판 수정 기능 Mapper xml 쿼리 만들기
				- 게시판 수정 기능 완료 페이지 만들기(crud/board/success.jsp)
				- 여기까지 확인
				
				- 게시판 삭제 기능 컨트롤러 메소드 만들기 (crudRemove:post)
				- 게시판 삭제 기능 서비스 인터페이스 메소드 만들기
				- 게시판 삭제 기능 서비스 클래스 메소드 만들기
				- 게시판 삭제 기능 Mapper 인터페이스 메소드 만들기
				- 게시판 삭제 기능 Mapper xml 쿼리 만들기
				- 게시판 삭제 기능 완료 페이지 만들기(crud/board/success.jsp)
				- 여기까지 확인
				
				- 게시판 검색 기능 컨트롤러 메소드 만들기 (crudSearch:get)
				- 게시판 검색 기능 서비스 인터페이스 메소드 만들기
				- 게시판 검색 기능 서비스 클래스 메소드 만들기
				- 게시판 검색 기능 Mapper 인터페이스 메소드 만들기
				- 게시판 검색 기능 Mapper xml 쿼리 만들기
				- 게시판 검색 기능 완료 페이지 만들기(crud/board/list.jsp)
				- 여기까지 확인
				
				- 게시판 목록 화면 검색 페이지 추가 (crud/board/list.jsp)
				- 게시판 검색 기능 컨트롤러 메소드 만들기 (crudSearch:post)
				- 게시판 검색 기능 서비스 인터페이스 메소드 만들기
				- 게시판 검색 기능 서비스 클래스 메소드 만들기
				- 게시판 검색 기능 Mapper 인터페이스 메소드 만들기
				- 게시판 검색 기능 Mapper xml 쿼리 만들기
				- 게시판 검색 기능 완료 페이지 만들기(crud/board/list.jsp)
				- 여기까지 확인
				
				- 기본 CRUD 끝
				
		7. 별칭 적용
		
			- TypeAlias로 맵핑 파일에서 반복적으로 사용될 패키지의 이름을 정의한다.
			
			1) 마이바티스 설정
				
				1-1) application.properties 설정
				- mybatis.type-aliases-package=kr.or.ddit.vo
				
				# TypeAlias 설정 방법
				1) application.properties 파일 내, aliase-package 설정
					> 해당 클래스의 위치를 패키지와 클래스명까지 작성해준다.
				2) VO 클래스의 @Alias 어노테이션으로 alias 설정
		8. '_'로 구분된 컬럼명 자동 매핑
		
			- 마이바티스 설정의 mapUnderscoreToCamelCase 프로퍼티 값을 true로 지정하면 '_'로 구분된 컬럼명을
				소문자 낙타표기법의 프로퍼티명으로 자동 매핑할 수 있다.
			
			1) 마이바티스 설정
			
				1-1) application.properties 설정
				- mybatis.configuration.map-underscore-to-camel-case=true

		9. 기본키 취득
		
		 	- 마이바티스는 useGeneratedKeys 속성을 이용하여 insert 할 때 데이터 베이스 측에서 채번된 기본키를 취득할 수 있다.
		 	
		 	1) 마이바티스 설정
		 	
		 		1-1) 매핑 파일 수정 (board_Mapper.xml)
		 		- create 부분의 속성 추가
		 		> <insert id="create" parameterType="Board" useGeneratedKeys="true">
		 		>	<selectKey order="BEFORE" resultType="int" keyProperty="boardNo">
	 			>		select seq_board.nextval from dual
	 			>	</selectKey>	
	 			>	insert into board(...)	
	 			>	values (#{boardNo},#{title},...)
	 			>	</insert>
	 			
	 			아래 insert 쿼리가 실행 되기 전, selectKey 태그 내에 있는 쿼리가 먼저 실행되어
	 			최신의 boardNo를 생성하고 생성된 boardNo를 sql쿼리가 담겨있는 insert 태그까지 넘어올 때
	 			넘겨주고 있는 파라미터(board)의 property인 boardNo에 세팅한다.
	 			세팅된 boardNo를 아래에서 insert 시, 등록 값으로 사용하고 board라는 자바빈즈 객체에 담겨
	 			최종 컨트롤러까지 넘어간다.
	 			
	 			*** seq_board.nextval 대신 현재의 seq 번호를 가져오기 위해 currval을 사용하게 되는 경우가 있습니다.
	 				이 때 사용시 주의사항이 있습니다.
	 				
	 				- select seq_board.currval from dual
	 				> 위 select 쿼리를 사용시, currval을 사용하는데 있어서 사용 불가에 대한 에러가 발생할 수 있습니다.
	 				 	currval을 사용할 때는 select seq_board.nextval from dual와 같이 먼저 최초로 실행 후,
	 				 	select seq_board.currval from dual을 사용하면 에러가 없습니다.
 				 	> 같은 세션내에서의 실행이 이뤄지지 않았기 때문에 currval로 데이터를 가져오는데 에러가 발생합니다.
 				 	
 				 	# 그럼에도 나는 가져와야겠다! 다 필요없고 그냥 내놔! 하고 싶으면
 				 	> select last_number from user_sequences where sequence_name = '시퀀스명';
 				 	> select last_number from user_sequences where sequence_name = 'seq_board';
 				 	쿼리로 가져오면 된다.(권장사항 아님)
			 	
			 	2-2) 컨트롤러 메소드에서 crudRegister 부분의 등록이 되고나서 리턴되는 방향성을 수정
			 		- 지금은 완료페이지로 전환되지만, board에 담겨있는 boardNo를 이용하여 상세보기 페이지로 전환해도 무방!
 		
 		
 		10. 동적 SQL
 		
 			- 마이바티스는 동적 SQL을 조립하는 구조를 지원하고 있으며, SQL 조립 규칙을 매핑 파일에 정의할 수 있다.
 			
 			1) 동적으로 SQL을 조립하기 위한 SQL 요소
 				- <where> 	: where 절 앞 뒤에 내용을 더 추가하거나 삭제할 때 사용하는 요소
 				- <if> 		: 조건을 만족할 때만 SQL을 조립할 수 있게 만드는 요소
 				- <choose> 	: 여러 선택 항목에서 조건에 만족할 때만 SQL을 조립할 수 있게 만드느 요소
 				- <foreach> : 컬렉션이나 배열에 대해 반복 처리를 하기 위한 요소
 				- <set> 	: set 절 앞 뒤에 내용을 더 추가하거나 삭제할 때 사용하는 요소
 				
		11. 일대다 관계 테이블 매핑
		
			- 마이바티스 기능을 활용하여 매핑 파일을 적절하게 정의하면 일대다 관계 테이블 매핑을 쉽게 처리할 수 있다.
			
			1) 게시판 구현 설명
			
			- 회원 등록 화면 컨트롤러 만들기 (member/CrudMemberController)
			- 회원 등록 화면 컨트롤러 메소드 만들기 (crudMemberRegisterForm:get)
			- 회원 등록 화면 만들기 (crud/member/register.jsp)
			- 여기까지 확인
			
			- 회원 등록 기능 컨트롤러 메소드 만들기 (crudMemberRegister:post)
			- 회원 등록 기능 서비스 인터페이스 메소드 만들기
			- 회원 등록 기능 서비스 클래스 메소드 만들기
			- 회원 등록 기능 Mapper 인터페이스 메소드 만들기
			- 회원 등록 기능 Mapper xml 쿼리 만들기
			- 회원 등록 완료 페이지 만들기(crud/member/success.jsp)
			- 여기까지 확인
			
			- 회원 목록 화면 컨트롤러 메소드 만들기 (crudMemberList:get)
			- 회원 목록 화면 서비스 인터페이스 메소드 만들기
			- 회원 목록 화면 서비스 클래스 메소드 만들기
			- 회원 목록 화면 Mapper 인터페이스 메소드 만들기
			- 회원 목록 화면 Mapper xml 쿼리 만들기
			- 회원 목록 화면 페이지 만들기 (crud/member/list.jsp)
			- 여기까지 확인
			
			- 회원 상세 화면 컨트롤러 메소드 만들기 (crudMemberRead:get)
			- 회원 상세 화면 서비스 인터페이스 메소드 만들기
			- 회원 상세 화면 서비스 클래스 메소드 만들기
			- 회원 상세 화면 Mapper 인터페이스 메소드 만들기
			- 회원 상세 화면 Mapper xml 쿼리 만들기
			- 회원 상세 화면 페이지 만들기 (crud/member/read.jsp)
			- 여기까지 확인
			
			-- Member 테이블 전체 조회
			select * from member;
			-- MemberAuth 테이블 전체 조회
			select * from member_auth;
			
			-- JOIN의 기준테이블과 조인테이블 선정
			-- 기준테이블 : 회원
			-- 조인테이블 : 회원권한
			select
			    m.USER_NO, USER_ID, USER_PW, USER_NAME, REG_DATE, UPD_DATE, ENABLED,
			    AUTH
			from member m
			left outer join member_auth ma on (m.user_no = ma.user_no);
			
			- 회원 수정 화면 컨트롤러 메소드 만들기 (crudMemberModifyForm:get)
			- 회원 수정 화면 서비스 인터페이스 메소드 만들기
			- 회원 수정 화면 서비스 클래스 메소드 만들기
			- 회원 수정 화면 Mapper 인터페이스 메소드 만들기
			- 회원 수정 화면 Mapper xml 쿼리 만들기
			- 회원 수정 화면 페이지 만들기 (crud/member/modify.jsp)
			- 여기까지 확인
			
			- 회원 수정 기능 컨트롤러 메소드 만들기 (crudMemberModify:post)
			- 회원 수정 기능 서비스 인터페이스 메소드 만들기
			- 회원 수정 기능 서비스 클래스 메소드 만들기
			- 회원 수정 기능 Mapper 인터페이스 메소드 만들기
			- 회원 수정 기능 Mapper xml 쿼리 만들기
			- 여기까지 확인
			
			- 회원 삭제 화면 컨트롤러 메소드 만들기 (crudMemberDelete:post)
			- 회원 삭제 화면 서비스 인터페이스 메소드 만들기
			- 회원 삭제 화면 서비스 클래스 메소드 만들기
			- 회원 삭제 화면 Mapper 인터페이스 메소드 만들기
			- 회원 삭제 화면 Mapper xml 쿼리 만들기
			- 여기까지 확인 							
	 */
	
	
}

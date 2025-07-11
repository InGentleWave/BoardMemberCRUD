package kr.or.ddit.vo.crud;

import java.util.Date;

import org.apache.ibatis.type.Alias;

import lombok.Data;

// application.properties 설정에서 type-aliases 설정을 vo 패키지 라인으로 설정해두었습니다.
// 그리고 mybatipse 플러그인에 의해서 vo 패키지 하위에 있는 모든 클래스들을 기반으로 alias를 검색 목록으로 제공합니다.
// 그렇지만, 별도의 alias를 설정하고 싶은 경우엔 @Alias 어노테이션을 활용해 특정 alias를 설정할 수 있습니다.
@Data
//@Alias("bVO")
public class Board {
	private int boardNo;
	private String title;
	private String writer;
	private String content;
	private Date regDate;
}

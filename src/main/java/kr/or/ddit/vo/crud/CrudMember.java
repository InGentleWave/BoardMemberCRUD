package kr.or.ddit.vo.crud;

import java.util.Date;
import java.util.List;

import lombok.Data;

@Data
public class CrudMember {
	private int userNo;
	private String userId;
	private String userPw;
	private String userName;
	private Date regDate;
	private Date updDate;
	private List<CrudMemberAuth> authList;
}

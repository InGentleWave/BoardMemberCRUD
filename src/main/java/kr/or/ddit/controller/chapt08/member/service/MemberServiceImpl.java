package kr.or.ddit.controller.chapt08.member.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.or.ddit.controller.chapt08.member.mapper.IMemberMapper;
import kr.or.ddit.vo.crud.CrudMember;
import kr.or.ddit.vo.crud.CrudMemberAuth;

@Service
public class MemberServiceImpl implements IMemberService {

	@Autowired
	private IMemberMapper mapper;
	
	@Override
	public void register(CrudMember member) {
		mapper.create(member);								// 회원정보 등록
		
		CrudMemberAuth memberAuth = new CrudMemberAuth();
		memberAuth.setUserNo(member.getUserNo());			// 회원 등록 후 얻어온 회원번호 설정
		memberAuth.setAuth("ROLE_USER");					// 회원 권한 설정(기본값 USER)
		mapper.createAuth(memberAuth);						// 회원권한정보 등록
	}

	@Override
	public List<CrudMember> list() {
		return mapper.list();
	}

	@Override
	public CrudMember read(int userNo) {
		return mapper.read(userNo);
	}

	@Override
	public void modify(CrudMember member) {
		// 1. 회원 기본정보 수정
		mapper.modify(member);
		// 2. 기존 회원권한정보 삭제
		int userNo = member.getUserNo();
		mapper.deleteAuth(userNo);
		//		회원권한 수정하기 위해 넘긴 데이터로 다시 등록
		List<CrudMemberAuth> authList = member.getAuthList();
		for(int i=0;i<authList.size();i++) {
			CrudMemberAuth memberAuth = authList.get(i);
			String auth = memberAuth.getAuth();
			if(auth == null) continue;
			if(auth.trim().length() == 0) continue;
			memberAuth.setUserNo(userNo);
			mapper.createAuth(memberAuth);
		}
		
	}

	@Override
	public void remove(int userNo) {
		mapper.deleteAuth(userNo);			// 자식 테이블인 auth 테이블에 데이터 삭제
		mapper.delete(userNo);				// 부모 테이블의 데이터 삭제
	}

}

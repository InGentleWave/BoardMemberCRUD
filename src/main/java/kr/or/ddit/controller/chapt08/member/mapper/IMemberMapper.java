package kr.or.ddit.controller.chapt08.member.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.or.ddit.vo.crud.CrudMember;
import kr.or.ddit.vo.crud.CrudMemberAuth;

@Mapper
public interface IMemberMapper {

	public void create(CrudMember member);

	public void createAuth(CrudMemberAuth memberAuth);

	public List<CrudMember> list();

	public CrudMember read(int userNo);

	public void modify(CrudMember member);

	public void deleteAuth(int userNo);

	public void delete(int userNo);

}

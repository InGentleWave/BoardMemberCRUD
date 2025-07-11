package kr.or.ddit.controller.chapt08.board.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.or.ddit.vo.crud.Board;

@Mapper
public interface IBoardMapper {
	
	public void create(Board board);

	public List<Board> list();

	public Board read(int boardNo);

	public void modify(Board board);

	public void remove(int boardNo);

	public List<Board> search(Board board);

}

package kr.or.ddit.controller.chapt08.board.service;

import java.util.List;

import kr.or.ddit.vo.crud.Board;

public interface IBoardService {

	public void register(Board board);

	public List<Board> list();

	public Board read(int boardNo);

	public void modify(Board board);

	public void remove(int boardNo);

	public List<Board> search(Board board);


}

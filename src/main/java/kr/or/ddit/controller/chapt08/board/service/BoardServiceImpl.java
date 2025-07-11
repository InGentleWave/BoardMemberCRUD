package kr.or.ddit.controller.chapt08.board.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.or.ddit.controller.chapt08.board.mapper.IBoardMapper;
import kr.or.ddit.vo.crud.Board;

@Service
public class BoardServiceImpl implements IBoardService {

	@Autowired
	private IBoardMapper mapper;
	
	@Override
	public void register(Board board) {
		mapper.create(board);
	}
	@Override
	public List<Board> list() {
		return mapper.list();
	}
	@Override
	public Board read(int boardNo) {
		return mapper.read(boardNo);
	}
	@Override
	public void modify(Board board) {
		mapper.modify(board);
	}
	@Override
	public void remove(int boardNo) {
		mapper.remove(boardNo);
	}
	@Override
	public List<Board> search(Board board) {
		return mapper.search(board);
	}

}

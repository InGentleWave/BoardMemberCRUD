package kr.or.ddit.controller.chapt08.board;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.or.ddit.controller.chapt08.board.service.IBoardService;
import kr.or.ddit.vo.crud.Board;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/crud/board")
public class CrudBoardController {

	@Autowired
	private IBoardService service;
	
	// Board 게시판 등록 페이지
	@GetMapping("/register")
	public String crudRegisterForm() {
		log.info("crudRegisterForm()실행!");
		return "chapt08/board/register";
	}
	
	@PostMapping("/register")
	public String crudRegister(Board board, Model model) {
		log.info("crudRegister()실행!");
		service.register(board);							// 게시글 등록 이벤트 실행
		model.addAttribute("msg","등록이 완료되었습니다.");
		
		// 등록 후, 성공 페이지로 이동
//		return "chapt08/board/success";
		
		// 등록 후, 최신 게시글 번호를 이용해 상세 정보로 넘어갈 때 사용
		return "redirect:/crud/board/read?boardNo="+board.getBoardNo();
	}
	
	@GetMapping("/list")
	public String crudList(Model model) {
		log.info("crudList()실행!");
		List<Board> boardList = service.list();
		model.addAttribute("boardList",boardList);
		
		return "chapt08/board/list";
	}
	
	@GetMapping("/read")
	public String crudRead(int boardNo, Model model) {
		log.info("crudRead()실행!");
		Board board = service.read(boardNo);
		model.addAttribute("board",board);
		
		return "chapt08/board/read";
	}
	
	@GetMapping("/modify")
	public String crudModifyForm(int boardNo, Model model) {
		log.info("crudModifyForm()실행!");
		Board board = service.read(boardNo);
		model.addAttribute("board",board);
		model.addAttribute("status","u");
		return "chapt08/board/register";
	}
	
	@PostMapping("/modify")
	public String crudModify(Board board, Model model) {
		log.info("crudModify()실행!");
		service.modify(board);
		model.addAttribute("msg","수정이 완료되었습니다!");
		return "chapt08/board/success";
	}
	
	@PostMapping("/remove")
	public String crudRemove(int boardNo,Model model) {
		log.info("crudRemove()실행!");
		service.remove(boardNo);
		model.addAttribute("msg","삭제가 완료되었습니다!");
		return "chapt08/board/success";
	}
	
	@PostMapping("/search")
	public String crudSearch(Board board,Model model) {
		log.info("crudSearch()실행!");
		List<Board> boardList = service.search(board);
		model.addAttribute("board",board);
		model.addAttribute("boardList",boardList);
		return "chapt08/board/list";
	}
}

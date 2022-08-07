package com.vue.springRest.api.board.service;

import com.vue.springRest.api.board.model.BoardModel;
import com.vue.springRest.api.board.model.CommentModel;
import com.vue.springRest.api.dto.BoardDTO;
import com.vue.springRest.api.dto.CommentDTO;

import java.util.List;

public interface BoardService {
    // 게시글 불러오기
    List<BoardModel> boardList();

    // 상세보기
    BoardModel getBoard(Long board_no);

    // 게시글작성
    void boardWrite(BoardDTO boardDTO);

    // 게시글 수정
    void boardUpdate(BoardDTO boardDTO, Long board_no);

    //게시글 삭제
    void boardDelete(Long board_no);
    /////
    // 게시글댓글목록
    List<CommentModel> commentListByBoardNo(Long board_no);
    // 댓글 달기
    CommentModel commentWrite(Long board_no,CommentDTO commentDTO);
    // 댓글 수정
    CommentModel commentModify(Long comment_no,CommentDTO commentDTO);
    // 댓글 삭제
    void commentDelete(Long comment_no);

}

package com.vue.springRest.api.board.service;

import com.vue.springRest.api.board.mapper.BoardMapper;
import com.vue.springRest.api.board.model.BoardModel;
import com.vue.springRest.api.board.model.CommentModel;
import com.vue.springRest.api.dto.BoardDTO;
import com.vue.springRest.api.dto.CommentDTO;
import com.vue.springRest.api.user.service.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {

    private final UserServiceImpl userService;

    private final BoardMapper boardMapper;

    @Override
    public List<BoardModel> boardList() {
        return boardMapper.boardList();
    }

    @Override
    public BoardModel getBoard(Long board_no) {
        return boardMapper.getBoard(board_no);
    }

    @Override
    @Transactional
    public void boardWrite(final BoardDTO boardDTO) {
        String writer = "";
        Long userNo = 0L;
        if (userService.getMyUserWithAuthorities().isPresent()) {
            writer = userService.getMyUserWithAuthorities().get().getUser_id();
            userNo = userService.getMyUserWithAuthorities().get().getUser_no();
        }
        BoardModel boardModel = BoardModel.builder()
                .user_no(userNo)
                .board_writer(writer)
                .board_content(boardDTO.getBoard_content())
                .board_title(boardDTO.getBoard_title())
                .build();


        boardMapper.boardWrite(boardModel);
    }

    @Override
    @Transactional //공부 테이블 2개 만들어서 테스트
    public void boardUpdate(final BoardDTO boardDTO,final Long board_no) {
        BoardModel boardModel = BoardModel.builder()
                .board_no(board_no)
                .board_title(boardDTO.getBoard_title())
                .board_content(boardDTO.getBoard_content())
                .build();

        boardMapper.boardUpdate(boardModel);
    }

    // 게시글 삭제
    @Override
    @Transactional
    public void boardDelete(Long board_no) {
        boardMapper.boardDelete(board_no);
    }

    // 댓글가져오기
    @Override
    public List<CommentModel> commentListByBoardNo(Long board_no) {

        return boardMapper.commentList(board_no);
    }

    //댓글작성
    @Override
    @Transactional
    public CommentModel commentWrite(Long board_no,CommentDTO commentDTO) {

        CommentModel commentModel = CommentModel.builder()
                .user_id(commentDTO.getUser_id())
                .board_no(board_no)
                .comment_content(commentDTO.getComment_content())
                .build();
                boardMapper.commentWrite(commentModel);

        return commentModel;
    }

    //댓글 수정
    @Override
    @Transactional
    public CommentModel commentModify(Long comment_no,CommentDTO commentDTO) {

        CommentModel commentModel = CommentModel.builder()
                .comment_no(comment_no)
                .comment_content(commentDTO.getComment_content())
                .build();

        boardMapper.commentModify(commentModel);

        return commentModel;
    }

    //댓글 삭제
    @Override
    @Transactional
    public void commentDelete(Long comment_no) {
        boardMapper.commentDelete(comment_no);
    }


}

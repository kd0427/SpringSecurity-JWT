package com.vue.springRest.api.board.mapper;

import com.vue.springRest.api.board.model.BoardModel;
import com.vue.springRest.api.board.model.CommentModel;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BoardMapper {

    List<BoardModel> boardList();

    BoardModel getBoard(Long board_no);
    void boardWrite(BoardModel boardModel);
    void boardUpdate(BoardModel boardModel);
    void boardDelete(Long board_no);

    /////
    void commentWrite(CommentModel commentModel);
    void commentModify(CommentModel commentModel);
    void commentDelete(Long comment_no);
    List<CommentModel> commentList(Long board_no);
}

package com.vue.springRest.api.board.controller;
import com.vue.springRest.api.board.model.BoardModel;
import com.vue.springRest.api.board.model.CommentModel;
import com.vue.springRest.api.board.service.BoardServiceImpl;
import com.vue.springRest.api.dto.BoardDTO;
import com.vue.springRest.api.dto.CommentDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.*;
import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class BoardController {

    private final BoardServiceImpl boardService;

    @GetMapping("/board")
    public ResponseEntity<List<BoardModel>> boardList(){
        return ResponseEntity.ok(boardService.boardList());
    }

    @GetMapping("/board/{board_no}")
    public ResponseEntity<BoardModel> getBoard(@PathVariable final Long board_no){
        return ResponseEntity.ok(boardService.getBoard(board_no));
    }

    @PostMapping("/board")
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public void boardWrite(@RequestBody BoardDTO boardDTO){ // 수정
        boardService.boardWrite(boardDTO);
    }

    @PatchMapping("/board/{board_no}")
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public void boardUpdate(@PathVariable Long board_no, @RequestBody Map<Object,Object> fields){
        BoardModel boardModel = boardService.getBoard(board_no);

        BoardDTO boardDTO = BoardDTO.builder()
                .board_title(boardModel.getBoard_title())
                .board_content(boardModel.getBoard_content())
                .board_no(boardModel.getBoard_no())
                .board_writer(boardModel.getBoard_writer())
                .user_no(boardModel.getUser_no())
                .board_date(boardModel.getBoard_date())
                .commentList(null) // 댓글 불러오기
                .build();

        fields.forEach((k,v)->{
            Field field = ReflectionUtils.findField(BoardDTO.class,(String) k);
            assert field != null;
            field.setAccessible(true);
            ReflectionUtils.setField(field, boardDTO, v);
        });
        boardService.boardUpdate(boardDTO,board_no);
    }
    @PutMapping("/board/{board_no}")
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public void boardUpdate2(@RequestBody BoardDTO boardDTO,@PathVariable Long board_no){
        boardService.boardUpdate(boardDTO,board_no);
    }

    @DeleteMapping("/board/{board_no}")
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public void boardDelete(@PathVariable Long board_no){
        boardService.boardDelete(board_no);
    }

    ///////
    // 댓글 리스트
    @GetMapping("/board/comment/{board_no}")
    public ResponseEntity<List<CommentModel>> commentList(@PathVariable Long board_no){

        return ResponseEntity.ok(boardService.commentListByBoardNo(board_no));
    }
    // 댓글작성
    @PostMapping("/board/comment/{board_no}")
    public ResponseEntity<CommentModel> comment(@PathVariable Long board_no, @RequestBody CommentDTO commentDTO){
        return ResponseEntity.ok(boardService.commentWrite(board_no,commentDTO));
    }
    // 댓글 수정
    @PutMapping("/board/comment/{comment_no}")
    public ResponseEntity<CommentModel> commentModify(@PathVariable Long comment_no, @RequestBody CommentDTO commentDTO){

        return ResponseEntity.ok(boardService.commentModify(comment_no,commentDTO));
    }
    // 댓글 삭제
    @DeleteMapping("/board/comment/{comment_no}")
    public ResponseEntity<String> commentDelete(@PathVariable Long comment_no){
        boardService.commentDelete(comment_no);
        return ResponseEntity.ok("삭제완료");
    }

}

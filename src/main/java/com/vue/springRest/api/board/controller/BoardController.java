package com.vue.springRest.api.board.controller;

import com.vue.springRest.api.board.model.BoardModel;
import com.vue.springRest.api.board.service.BoardServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class BoardController {

    private final BoardServiceImpl boardService;

    @GetMapping("/board")
    public List<BoardModel> boardList(){

        return boardService.boardList();
    }

}

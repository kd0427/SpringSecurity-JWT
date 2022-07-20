package com.vue.springRest.api.board.service;

import com.vue.springRest.api.board.model.BoardModel;

import java.util.List;

public interface BoardService {

    List<BoardModel> boardList();
}

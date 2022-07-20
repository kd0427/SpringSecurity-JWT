package com.vue.springRest.api.board.service;

import com.vue.springRest.api.board.mapper.BoardMapper;
import com.vue.springRest.api.board.model.BoardModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService{

    private final BoardMapper boardMapper;
    @Override
    public List<BoardModel> boardList() {
        return boardMapper.boardList();
    }
}

package com.vue.springRest.api.board.mapper;

import com.vue.springRest.api.board.model.BoardModel;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BoardMapper {

    List<BoardModel> boardList();
}

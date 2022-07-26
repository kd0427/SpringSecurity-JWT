package com.vue.springRest.api.board.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.vue.springRest.api.dto.CommentDTO;
import lombok.*;
import org.springframework.lang.Nullable;

import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BoardModel {

    private Long board_no;

    private String board_title;

    private String board_content;

    private String board_writer;

    @JsonIgnore
    private Long user_no;

    private String board_date;

    @Nullable
    private List<CommentDTO> CommentList;
}

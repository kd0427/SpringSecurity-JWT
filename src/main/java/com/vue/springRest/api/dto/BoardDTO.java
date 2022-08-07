package com.vue.springRest.api.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.lang.Nullable;

import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BoardDTO {
    @NotNull
    private String board_title;
    @NotNull
    private String board_content;

    private Long board_no;

    private String board_writer;

    @JsonIgnore
    private Long user_no;

    private String board_date;

    @Nullable
    private List<CommentDTO> commentList;
}

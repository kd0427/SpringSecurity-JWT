package com.vue.springRest.api.board.model;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CommentModel {

    private Long comment_no;

    private Long board_no;

    private String user_id;

    private String comment_content;

    private String comment_date;

}

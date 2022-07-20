package com.vue.springRest.api.board.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BoardModel {
    private Long board_id;
    private String board_title;
    private String board_content;
    private Long user_no;
    private String board_date;
    private Long reply_no;
}

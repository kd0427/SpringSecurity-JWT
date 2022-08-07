package com.vue.springRest.api.dto;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CommentDTO {
    private String user_id;
    private String comment_content;
}

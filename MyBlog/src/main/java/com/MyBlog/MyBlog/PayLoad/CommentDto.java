package com.MyBlog.MyBlog.PayLoad;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentDto {

    private String body;
    private String email;
    private String name;


}

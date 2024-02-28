package com.MyBlog.MyBlog.PayLoad;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class postDto {

    private long id;

    @NotEmpty
    @Size(min = 10, message = "Post description should have at least 10 character")
    private String title;

    @NotEmpty
    private String description;

    @NotEmpty
    private String content;

}

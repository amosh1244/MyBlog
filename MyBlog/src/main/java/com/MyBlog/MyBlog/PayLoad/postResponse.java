package com.MyBlog.MyBlog.PayLoad;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class postResponse {

    private List<postDto> dto;
    private int pageSize;
    private int pageNo;
    private Boolean lastPage;
    private  int totalPages;

}

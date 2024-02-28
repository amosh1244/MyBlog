package com.MyBlog.MyBlog.Service;

import com.MyBlog.MyBlog.PayLoad.postDto;
import com.MyBlog.MyBlog.PayLoad.postResponse;

import java.util.List;

public interface postService {

    postDto createPost(postDto postDto);

    void deletePostById(long id);

    postDto getPostById(long id);

    postDto updatePost(long id, postDto postDto);

    postResponse getAllPosts(int pageNo, int pageSize, String sortBy, String sortDir);
}

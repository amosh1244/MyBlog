package com.MyBlog.MyBlog.ServiceImpl;

import com.MyBlog.MyBlog.Entity.post;
import com.MyBlog.MyBlog.Exception.ResourceNotFound;
import com.MyBlog.MyBlog.PayLoad.postDto;
import com.MyBlog.MyBlog.PayLoad.postResponse;
import com.MyBlog.MyBlog.Repository.postRepository;
import com.MyBlog.MyBlog.Service.postService;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class postServiceImpl implements postService {

    private postRepository postRepo;

    private ModelMapper modelMapper;



    public postServiceImpl(postRepository postRepo, ModelMapper modelMapper) {
        this.postRepo = postRepo;
        this.modelMapper = modelMapper;
    }

    @Override
    public postDto createPost(postDto postDto) {
        post post = mapToEntity(postDto);
        post savedPost = postRepo.save(post);
        postDto dto = mapToDto(savedPost);
        return dto;
    }

    @Override
    public void deletePostById(long id) {
        postRepo.findById(id).orElseThrow(
                ()->  new ResourceNotFound("Post Not Found With Id: " + id)
        );

        postRepo.deleteById(id);
    }

    @Override
    public postDto getPostById(long id) {
       post post =  postRepo.findById(id).orElseThrow(
                ()->  new ResourceNotFound("Post Not Found With Id: " + id)


        );

        return mapToDto(post);
    }

    @Override
    public postDto updatePost(long id, postDto postDto) {

        post post =  postRepo.findById(id).orElseThrow(
                ()->  new ResourceNotFound("Post Not Found With Id: " + id)


        );

        post.setTitle(postDto.getTitle());
        post.setDescription(postDto.getDescription());
        post.setContent(postDto.getContent());

        post savedPost = postRepo.save(post);

        postDto dto = mapToDto(savedPost);

        return dto;
    }

    @Override
    public  postResponse getAllPosts(int pageNo, int pageSize, String sortBy, String sortDir) {

        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name())?Sort.by(sortDir).ascending():Sort.by(sortBy).descending();

        Pageable pageable = PageRequest.of(pageNo,pageSize,sort);

        Page<post> pagePostObjects = postRepo.findAll(pageable);

        List<post> posts = pagePostObjects.getContent();

        List<postDto> dtos = posts.stream().map(post -> mapToDto(post)).collect(Collectors.toList());

        postResponse response = new postResponse();
        response.setDto(dtos);
        response.setPageNo(pagePostObjects.getNumber());
        response.setTotalPages(pagePostObjects.getTotalPages());
        response.setLastPage(pagePostObjects.isLast());
        response.setPageSize(pagePostObjects.getSize());

        return response;



    }

          private postDto mapToDto(post savedPost) {

          postDto postDto = modelMapper.map(savedPost, postDto.class);
//        postDto postDto = new postDto();
//        postDto.setId(savedPost.getId());
//        postDto.setTitle(savedPost.getTitle());
//        postDto.setDescription(savedPost.getDescription());
//        postDto.setContent(savedPost.getContent());

        return postDto;
    }

    post mapToEntity(postDto postDto){

        post post = modelMapper.map(postDto, post.class);
//        post post = new post();
//        post.setTitle(postDto.getTitle());
//        post.setDescription(postDto.getDescription());
//        post.setContent(postDto.getContent());

        return post;

    }

}

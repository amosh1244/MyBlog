package com.MyBlog.MyBlog.ServiceImpl;

import com.MyBlog.MyBlog.Entity.Comment;
import com.MyBlog.MyBlog.Entity.post;
import com.MyBlog.MyBlog.Exception.ResourceNotFound;
import com.MyBlog.MyBlog.PayLoad.CommentDto;
import com.MyBlog.MyBlog.Repository.CommentRepository;
import com.MyBlog.MyBlog.Repository.postRepository;
import com.MyBlog.MyBlog.Service.CommentService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentService {

    private CommentRepository commentRepo;
    private postRepository postRepo;

    public CommentServiceImpl(CommentRepository commentRepo, postRepository postRepo) {
        this.commentRepo = commentRepo;
        this.postRepo = postRepo;
    }

    @Override
    public CommentDto createComment(long postId, CommentDto commentDto) {

        post post = postRepo.findById(postId).orElseThrow(
                () -> new ResourceNotFound("Post Not Found By Id:" + postId)
        );

        Comment comment = mapToEntity(commentDto);
        comment.setPost(post);
        Comment c = commentRepo.save(comment);

        return mapToDto(c);

    }

    @Override
    public void deleteCommentById(Long postId, long commentId) {

        post post = postRepo.findById(postId).orElseThrow(
                () -> new ResourceNotFound("Post Not Found By Id:" + postId)
        );

        commentRepo.deleteById(commentId);


    }

    @Override
    public List<CommentDto> getCommentByPostId(long postId) {

        List<Comment> comments = commentRepo.findByPostId(postId);
        List<CommentDto> dtos = comments.stream().map(comment -> mapToDto(comment)).collect(Collectors.toList());

        return dtos;
    }

    @Override
    public CommentDto updateComment(long commentId, CommentDto commentDto) {

        Comment com = commentRepo.findById(commentId).get();

        post post = postRepo.findById(com.getId()).get();

        Comment comment = mapToEntity(commentDto);

        comment.setPost(post);
        comment.setId(commentId);

        Comment savedComment = commentRepo.save(comment);
        CommentDto dto = mapToDto(savedComment);

        return dto;
    }

    Comment mapToEntity(CommentDto dto){
        Comment comment = new Comment();
        comment.setName(dto.getName());
        comment.setEmail(dto.getEmail());
        comment.setBody(dto.getBody());

        return comment;

    }

    CommentDto mapToDto(Comment comment){
        CommentDto dto = new CommentDto();
        dto.setName(comment.getName());
        dto.setEmail(comment.getEmail());
        dto.setBody(comment.getBody());

        return dto;

    }


}

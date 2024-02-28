package com.MyBlog.MyBlog.Controller;

import com.MyBlog.MyBlog.PayLoad.CommentDto;
import com.MyBlog.MyBlog.Service.CommentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/comments")
//http://localhost:8080/api/comments
public class CommentController {

    private CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping
    //http://localhost:8080/api/comments?postId=1
    public ResponseEntity<CommentDto> createComment(@RequestParam long postId, @RequestBody CommentDto commentDto){
        CommentDto dto = commentService.createComment(postId, commentDto);
        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }

    @DeleteMapping
    //http://localhost:8080/api/comments?postId=&commentId=
    public ResponseEntity<String> commentDeleteById(@RequestParam long commentId, @RequestParam long postId){
        commentService.deleteCommentById(commentId,postId);
        return new ResponseEntity<>("Comment isDeleted", HttpStatus.OK);
    }

    @GetMapping
    //http://localhost:8080/api/comments?postId=1
    public List<CommentDto>  getCommentByPostId(@RequestParam long postId){
        List<CommentDto> commentDtos = commentService.getCommentByPostId(postId);
        return  commentDtos;
    }

    @PutMapping
    //http://localhost:8080/api/comments?commentId=4
    public ResponseEntity<CommentDto> updateComment(@RequestParam long commentId,@RequestBody CommentDto commentDto){
        CommentDto dto = commentService.updateComment(commentId, commentDto);
        return new ResponseEntity<>(dto,HttpStatus.OK);
    }

}

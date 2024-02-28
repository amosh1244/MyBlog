package com.MyBlog.MyBlog.Controller;

import com.MyBlog.MyBlog.PayLoad.postDto;
import com.MyBlog.MyBlog.PayLoad.postResponse;
import com.MyBlog.MyBlog.Service.postService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("api/posts")
public class PostController {

    private postService postService;

    public PostController(postService postService) {
        this.postService = postService;
    }


    @PostMapping
    //http://localhost:8080/api/posts
    public ResponseEntity<?> createPost(@Valid @RequestBody postDto postDto, BindingResult result){

        if(result.hasErrors()){
            return new ResponseEntity<>(result.getFieldError().getDefaultMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }

        postDto dto = postService.createPost(postDto);
        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }

    @DeleteMapping("{id}")
    //http://localhost:8080/api/posts/1
    public ResponseEntity<String> deletePostById(@PathVariable long id){
        postService.deletePostById(id);
        return new ResponseEntity<>("Post is Deleted", HttpStatus.OK);
    }

    @GetMapping("/{id}")
    //http://localhost:8080/api/posts/4
    public ResponseEntity<postDto> getPostById(@PathVariable long id){
        postDto postDto = postService.getPostById(id);
        return new ResponseEntity<>(postDto,HttpStatus.OK);
    }

    @PutMapping("/{id}")
    //http://localhost:8080/api/posts/3
    public ResponseEntity<postDto> updatePost(@PathVariable long id, @RequestBody postDto postDto) {
        postDto dto = postService.updatePost(id, postDto);
        return new ResponseEntity<>(dto, HttpStatus.OK );
    }

    @GetMapping
    //http://localhost:8080/api/posts?pageNo=1&pageSize=3&sortBy=title&sortDir=desc
    public postResponse getAllPosts(

            @RequestParam(name = "pageNo", required = false, defaultValue ="0" )int pageNo,
            @RequestParam(name = "pageSize", required = false, defaultValue = "5") int pageSize,
            @RequestParam(name = "sortBy", defaultValue = "id",required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = "asc", required = false) String sortDir

    ){
        postResponse response = postService.getAllPosts(pageNo, pageSize, sortBy, sortDir);
        return  response;
    }



}

package com.MyBlog.MyBlog.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name= "posts")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name="title", unique = true)
    private String title;
    @Column(name="description", unique = true)
    private String description;
    @Column(name="content", unique = true)
    private String content;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)

    List<Comment> comments = new ArrayList<>();

}

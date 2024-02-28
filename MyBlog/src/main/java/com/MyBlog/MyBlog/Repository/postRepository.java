package com.MyBlog.MyBlog.Repository;

import com.MyBlog.MyBlog.Entity.post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface postRepository extends JpaRepository<post, Long> {
}

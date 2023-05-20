package com.CS622.BrewMe.repository;

import com.CS622.BrewMe.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface PostRepository<A extends Post> extends JpaRepository<Post, Long> {
}

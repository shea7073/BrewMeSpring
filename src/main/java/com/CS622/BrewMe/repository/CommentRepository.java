package com.CS622.BrewMe.repository;

import com.CS622.BrewMe.entity.Comment;
import jakarta.transaction.Transactional;

@Transactional
public interface CommentRepository extends PostRepository<Comment>{
}

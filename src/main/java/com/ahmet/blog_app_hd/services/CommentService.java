package com.ahmet.blog_app_hd.services;

import com.ahmet.blog_app_hd.DTO.CommentDto;
import com.ahmet.blog_app_hd.entities.Comment;
import com.ahmet.blog_app_hd.entities.Post;
import com.ahmet.blog_app_hd.entities.User;
import com.ahmet.blog_app_hd.exceptions.ResourceNotFoundExceptionLongValue;
import com.ahmet.blog_app_hd.repositories.CommentRep;
import com.ahmet.blog_app_hd.repositories.PostRep;
import com.ahmet.blog_app_hd.repositories.UserRep;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CommentService {

    private CommentRep commentRep;
    private PostRep postRep;
    private UserRep userRep;

    public List<Comment> getCommentsByPost(Long postId) {
        Post post = postRep.findById(postId)
                .orElseThrow(() -> new ResourceNotFoundExceptionLongValue("Post", "postId", postId));
        return commentRep.findByPost(post);
    }

    public Comment getOne(Long id) {
        return commentRep.findById(id)
                .orElseThrow(() -> new ResourceNotFoundExceptionLongValue("Comment", "commentId", id));
    }

    public Comment createCom(CommentDto request) {

        User commenter = userRep.findById(request.getUserId())
                .orElseThrow(() -> new ResourceNotFoundExceptionLongValue("User", "userId", request.getUserId()));
        Post commented = postRep.findById(request.getPostId())
                .orElseThrow(() -> new ResourceNotFoundExceptionLongValue("Post", "userId", request.getPostId()));
        Comment toSave = new Comment();
        toSave.setContent(request.getContent());
        toSave.setUser(commenter);
        toSave.setPost(commented);
        return commentRep.save(toSave);

    }

    public Comment updateCom(CommentDto request, Long id) {
        Comment updated = commentRep.findById(id)
                .orElseThrow(() -> new ResourceNotFoundExceptionLongValue("Comment", "commentId", id));

        updated.setContent(request.getContent());
        return commentRep.save(updated);

    }

    public void deleteCom(Long id) {
        Comment deleted = commentRep.findById(id)
                .orElseThrow(() -> new ResourceNotFoundExceptionLongValue("Comment", "commentId", id));

        commentRep.deleteById(deleted.getId());
    }
}

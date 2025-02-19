package com.javadevmz.my_social_media.controller;

import com.javadevmz.my_social_media.dao.entity.Like;
import com.javadevmz.my_social_media.service.LikeManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
public class LikeController {

    @Autowired
    private LikeManager likeManager;

    @GetMapping("/posts/{postId}/likes")
    public List<Like> getPostLikes(@PathVariable Long postId,
                                   @RequestParam(required = false) LocalDateTime oldestLikeTime)
    {
       if(oldestLikeTime ==null){
           oldestLikeTime = LocalDateTime.now();
       }
        return likeManager.getNext30EntryLikes(postId, oldestLikeTime);
    }

    @GetMapping("/posts/{postId}/likes/count")
    public Long getPostLikeCount(@PathVariable Long postId){
        return likeManager.getLikeCountByMediaId(postId);
    }

    @GetMapping("/posts/{postId}/comments/{commentId}/likes/count")
    public Long getCommentLikeCount(@PathVariable Long commentId){
        return likeManager.getLikeCountByMediaId(commentId);
    }

    @PostMapping("/posts/{postId}/likes")
    public void likePost(@PathVariable Long postId){
        likeManager.addLike(postId);
    }

    @PostMapping("/posts/{postId}/comments/{commentId}/likes")
    public void likeComment(@PathVariable Long commentId){
        likeManager.addLike(commentId);
    }

    @DeleteMapping("/posts/{postId}/likes")
    public void deletePostLike(@PathVariable Long postId){
        likeManager.deleteLike(postId);
    }

    @DeleteMapping("/posts/{postId}/comments/{commentId}/likes")
    public void deleteCommentLike(@PathVariable Long commentId){
        likeManager.deleteLike(commentId);
    }
}

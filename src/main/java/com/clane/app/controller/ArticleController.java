/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.clane.app.controller;

import com.clane.app.dto.ArticleRequest;
import com.clane.app.dto.ArticleResponse;
import com.clane.app.dto.BaseResponse;
import com.clane.app.dto.PagedResponse;
import com.clane.app.security.CurrentUser;
import com.clane.app.security.UserPrincipal;
import com.clane.app.service.ArticleService;
import static com.clane.app.util.Constant.DEFAULT_PAGE_NUMBER;
import static com.clane.app.util.Constant.DEFAULT_PAGE_SIZE;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author hp
 */
@RestController
@RequestMapping("/api/v1/article")
public class ArticleController {
        
    @Autowired
    private ArticleService articleService;
    
    @PostMapping
    public ResponseEntity<BaseResponse> createArticle(@Valid @RequestBody ArticleRequest articleRequest, 
            @CurrentUser UserPrincipal principal) {
        ArticleResponse articleResponse = articleService.addArticle(articleRequest, principal);
        if(articleResponse != null){
            return ResponseEntity.ok(new BaseResponse(true, "Article created successfully.",
                    articleResponse
            ));
        } else {
            return ResponseEntity.ok(new BaseResponse(false, "Failed to create article.",
                    null
            ));
        }
    }
    
    @GetMapping("/all")
    public ResponseEntity<BaseResponse<List<ArticleResponse>>> getArticles() {
        List<ArticleResponse> articleResponses = articleService.getArticles();
        if (articleResponses != null) {
            return ResponseEntity.ok(new BaseResponse<>(true, "Article retrieved successfully", articleResponses));
        } else {
            return ResponseEntity.ok(new BaseResponse<>(false, "Article not found", null));
        }
    }
    
    @GetMapping("/writer")
    public ResponseEntity<BaseResponse<List<ArticleResponse>>> getArticles(@CurrentUser UserPrincipal principal) {
        List<ArticleResponse> articleResponses = articleService.getArticles(principal.getId());
        if (articleResponses != null) {
            return ResponseEntity.ok(new BaseResponse<>(true, "Article retrieved successfully", articleResponses));
        } else {
            return ResponseEntity.ok(new BaseResponse<>(false, "Article not found", null));
        }
    }
    
    @GetMapping("/{articleId}")
    public ResponseEntity<BaseResponse<ArticleResponse>> getArticle(@PathVariable("articleId") Long articleId) {
        ArticleResponse articleResponse = articleService.getArticle(articleId);
        if (articleResponse != null) {
            return ResponseEntity.ok(new BaseResponse<>(true, "Article retrieved successful", articleResponse));
        } else {
            return ResponseEntity.ok(new BaseResponse<>(false, "Article not found", null));
        }
    }
    
    @PutMapping("/{articleId}")
    public ResponseEntity<BaseResponse> updateArticle(@PathVariable("articleId") Long articleId,
            @Valid @RequestBody ArticleRequest articleRequest) {        
        ArticleResponse articleResponse = articleService.updateArticle(articleId, articleRequest);
        if (articleResponse != null) {
            return ResponseEntity.ok(new BaseResponse<>(true, "Article updated successfully.",
                    articleResponse
            ));
        } else {
            return ResponseEntity.ok(new BaseResponse<>(false, "Failed to update article", null));
        }
    }
    
    @DeleteMapping("/{articleId}")
    public ResponseEntity<BaseResponse> deleteArticle(@PathVariable("articleId") Long articleId){ 
        articleService.deleteArticle(articleId);
        return ResponseEntity.ok(new BaseResponse(true, "Article deleted.",
                null
            ));         
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.clane.app.service;

import com.clane.app.dto.ArticleRequest;
import com.clane.app.dto.ArticleResponse;
import com.clane.app.dto.PagedResponse;
import com.clane.app.exception.BadRequestException;
import com.clane.app.model.Article;
import com.clane.app.model.User;
import com.clane.app.repository.ArticleRepository;
import com.clane.app.repository.UserRepository;
import com.clane.app.security.UserPrincipal;
import com.clane.app.util.Constant;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

/**
 *
 * @author hp
 */
@Service
public class ArticleService {

    @Autowired
    private ArticleRepository articleRepository;
    
    @Autowired
    private UserRepository userRepository;

    public ArticleResponse addArticle(ArticleRequest articleRequest, UserPrincipal principal) {
        ArticleResponse articleResponse = null;
        User user = userRepository.findById(principal.getId()).orElse(null);
        if (articleRequest != null && user != null) {
            Article article = new Article();
            BeanUtils.copyProperties(articleRequest, article);
            article.setUser(user);
            Article result = articleRepository.save(article);
            if (result != null) {
                articleResponse = new ArticleResponse();
                BeanUtils.copyProperties(result, articleResponse);
            }
        }
        return articleResponse;
    }

    public ArticleResponse updateArticle(Long articleId, ArticleRequest articleRequest) {
        ArticleResponse articleResponse = null;
        Article article = articleRepository.findById(articleId).orElse(null);
        if (article != null) {
            if (articleRequest.getTitle() != null) {
                article.setTitle(articleRequest.getTitle());
            }
            if (articleRequest.getContent() != null) {
                article.setContent(articleRequest.getContent());
            }
            if (articleRequest.getSlug() != null) {
                article.setSlug(articleRequest.getSlug());
            }
            Article result = articleRepository.save(article);
            if (result != null) {
                articleResponse = new ArticleResponse();
                BeanUtils.copyProperties(result, articleResponse);
            }
        }
        return articleResponse;
    }

    public ArticleResponse getArticle(Long articleId) {
        Article article = articleRepository.findById(articleId).orElse(null);
        ArticleResponse articleResponse = null;
        if (article != null) {
            articleResponse = new ArticleResponse();
            BeanUtils.copyProperties(article, articleResponse);
        }
        return articleResponse;
    }

    public List<ArticleResponse> getArticles() {
        List<ArticleResponse> articleResponses = new ArrayList<>();
        List<Article> articles = articleRepository.findAll();               
           
        articles.stream().forEach((article) -> {
            ArticleResponse articleResponse = new ArticleResponse();
            BeanUtils.copyProperties(article, articleResponse);
            articleResponses.add(articleResponse);
        });
        return articleResponses;        
    }
    
    public List<ArticleResponse> getArticles(Long userId) {
        List<ArticleResponse> articleResponses = new ArrayList<>();
        List<Article> articles = articleRepository.findByUserId(userId);               
           
        articles.stream().forEach((article) -> {
            ArticleResponse articleResponse = new ArticleResponse();
            BeanUtils.copyProperties(article, articleResponse);
            articleResponses.add(articleResponse);
        });
        return articleResponses;           
    }

    public void deleteArticle(Long articleId) {
        articleRepository.deleteById(articleId);
    }
 
    public static void validatePageNumberAndSize(int page, int size) {
        if (page < 0) {
            throw new BadRequestException("Page number cannot be less than zero.");
        }

        if (size > Constant.MAX_PAGE_SIZE) {
            throw new BadRequestException("Page size must not be greater than " + Constant.MAX_PAGE_SIZE);
        }
    }
}

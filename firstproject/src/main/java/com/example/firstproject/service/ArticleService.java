package com.example.firstproject.service;

import com.example.firstproject.dto.ArticleForm;
import com.example.firstproject.entity.Article;
import com.example.firstproject.repository.ArticleRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service    //서비스 객체 생성
public class ArticleService {
    @Autowired
    private ArticleRepository articleRepository;

    public List<Article> index() {
        return articleRepository.findAll();
    }

    public Article show(Long id) {
        return articleRepository.findById(id).orElse(null);
    }

    public Article create(ArticleForm dto) {
        Article article = dto.toEntity();
        if(article.getId() != null){
            return null;
        }
        return articleRepository.save(article);
    }

    public Article update(Long id, ArticleForm dto) {
        //수정용 엔티티 생성하기
        Article article = dto.toEntity();
        log.info("id :{}, article:{}", id, article.toString());
        //DB에 대상 엔티티가 있는지 조회하기
        Article target = articleRepository.findById(id).orElse(null);
        //대상 엔티티가 없거나 수정하려는 id가 잘못됐을 경우 처리하기
        if(target == null || id != article.getId()){
            //400, 잘못된 요청 응답
            log.info("잘못된 요청! id:{}, article:{}", id, article.toString());
            return null;
        }

        //대상 엔티티가 있으면 수정 내용으로 업데이트하고 정상 응답(200)보내기
        target.patch(article);
        Article updated = articleRepository.save(target);
        return updated;
    }

    public Article delete(Long id) {
        //DB에서 대상 엔티티가 있는지 조회하기
        Article target = articleRepository.findById(id).orElse(null);
        //대상 엔티티가 없어서 요청 자체가 잘못됐을 경우 처리하기
        if(target == null)
            return null;
        articleRepository.delete(target);
        //return ResponseEntity.status(HttpStatus.OK).body(null);
        return target;
        //대상 엔티티가 있으면 삭제하고 정상응답(200) 반환하기
    }

    @Transactional
    public List<Article> createArticles(List<ArticleForm> dtos) {
        //dto묶음을 엔티티 묶음으로 변환하기
        List<Article> articleList = dtos.stream()
                .map(dto -> dto.toEntity())
                .collect(Collectors.toList());
        //엔티티 묶음을 DB에 저장하기
        articleList.stream()
                .forEach(article -> articleRepository.save(article));
        //강제 예외 발생시키기
        articleRepository.findById(-1L)
                .orElseThrow(() -> new IllegalArgumentException("결제 실패"));
        //결과 값 반환하기
        return articleList;

    }
}

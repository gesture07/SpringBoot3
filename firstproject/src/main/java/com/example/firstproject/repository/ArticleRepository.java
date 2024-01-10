package com.example.firstproject.repository;

import com.example.firstproject.entity.Article;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;

public interface ArticleRepository extends CrudRepository<Article, Long> { //엔티티 관리(생성, 조회, 수정, 삭제)를 가능하게 함

    @Override
    ArrayList<Article> findAll();
}

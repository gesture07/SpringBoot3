package com.example.firstproject.dto;

import com.example.firstproject.entity.Article;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@AllArgsConstructor //생성자
@NoArgsConstructor  //기본 생성자
@ToString   //ToString()
public class ArticleForm {
    private Long id;
    private String title;   //제목을 받을 필드
    private String content; //내용을 받을 필드






    public Article toEntity() {
        return new Article(id, title, content);
    }
}



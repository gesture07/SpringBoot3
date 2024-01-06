package com.example.firstproject.dto;

import com.example.firstproject.entity.Article;

public class ArticleForm {
    private final String title;   //제목을 받을 필드
    private final String content; //내용을 받을 필드


    public ArticleForm(String title, String content) { //전송받은 제목과 내용을 필드에 저장하는 생성자 추가
        this.title = title;
        this.content = content;
    }

    @Override
    public String toString() {
        return "ArticleForm{" +
                "title='" + title + '\'' +
                ", content='" + content + '\'' +
                '}';
    }

    public Article toEntity() {
        return new Article(null, title, content);
    }
}



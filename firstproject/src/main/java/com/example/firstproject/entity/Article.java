package com.example.firstproject.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@AllArgsConstructor //Article 생성자 추가
@NoArgsConstructor //기본 생서자 추가 어노테이션
@ToString    //toString()메서드 추가
@Entity //엔티티 선언
public class Article {
    @Id //엔티티의 대푯값 지정
    @GeneratedValue(strategy = GenerationType.IDENTITY) //자동 생성 기능 추가(숫자가 자동으로 매겨짐)DB가 id 자동 생성
    private Long id;
    @JsonProperty
    @Column //title필드 선언, DB테이블의 title열과 연결됨
    private String title;
    @Column //content필드 선언, CB테이블의 content열과 연결됨
    private String content;


    public void patch(Article article) {
        if(article.title != null)
            this.title = article.title;
        if(article.content != null)
            this.content = article.content;
    }
}

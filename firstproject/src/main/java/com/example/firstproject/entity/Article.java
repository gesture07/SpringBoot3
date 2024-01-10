package com.example.firstproject.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor //Article 생성자 추가
@NoArgsConstructor //기본 생서자 추가 어노테이션
@ToString    //toString()메서드 추가
@Entity //엔티티 선언
public class Article {
    @Id //엔티티의 대푯값 지정
    @GeneratedValue //자동 생성 기능 추가(숫자가 자동으로 매겨짐)
    private Long id;
    @Column //title필드 선언, DB테이블의 title열과 연결됨
    private String title;
    @Column //content필드 선언, CB테이블의 content열과 연결됨
    private String content;






}

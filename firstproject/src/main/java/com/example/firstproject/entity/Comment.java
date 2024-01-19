package com.example.firstproject.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;    //대표키
    @ManyToOne //코멘트 엔티티와 알티클 엔티티를 다대일 관계로 설정
    @JoinColumn(name = "article_id")
    private Article article;    //해당 댓글의 부모 게시글
    @Column
    private String nickname;    //댓글을 단 사람
    @Column
    private String body;    //댓글 본문

}

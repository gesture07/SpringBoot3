package com.example.firstproject.controller;

import com.example.firstproject.dto.ArticleForm;
import com.example.firstproject.entity.Article;
import com.example.firstproject.repository.ArticleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Slf4j  //Simple Logging Facade for Java - 로깅기능 사용 가능
@Controller
public class ArticleController {
    @Autowired  //스프링부트에서 제공하는 어노테이션, 객체 생성 해서 가져와 줌
    private ArticleRepository articleRepository;
    @GetMapping("/articles/new")
    public String newArticleForm(){

        return "articles/new";
    }

    @PostMapping("/articles/create")
    public String createArticle(ArticleForm form) {
        log.info(form.toString());  //로깅 코드 추가
        //System.out.println(form.toString());
        //1. DTO를 entity로 변환
        Article article = form.toEntity();
        log.info(article.toString());
        //System.out.println(article.toString()); //DTO가 엔티티로 잘 변환되는지 확인 출력
        //2. repository로 entity를 DB에 저장
        Article saved = articleRepository.save(article);
        log.info(saved.toString());
        //System.out.println(saved.toString());   //article이 CB에 잘 저장되는 지 확인 출력
        return "redirect:/articles/" + saved.getId();
    }

    @GetMapping("/articles/{id}") //데이터 조회 요청 접수
    public String show(@PathVariable Long id, Model model){  //메개변수로 id 받아오기
        log.info("id= " + id);  //id를 잘 받았는지 확인하는 로그 찍기


        //id를 조회해 데이터 가져오기
        //Optional<Article> articleEntity = articleRepository.findById(id);
        Article articleEntity = articleRepository.findById(id).orElse(null);
        //모델에 데이터 등록하기
         model.addAttribute("article", articleEntity);   // name이라는 이름으로 value객체 추가
        //뷰 페이지 반환하기
        return "articles/show";
    }

    @GetMapping("/articles")
    public String index(Model model){

        //모든 데이터 가져오기
        List<Article> articleEntityList = articleRepository.findAll();
        //모델에 데이터 등록하기
        model.addAttribute("articleList", articleEntityList);
        //뷰 페이지 설정하기
        return "articles/index";
    }

    @GetMapping("/articles/{id}/edit")  //url요청 접수, 컨트롤러에서 변수 사용할 때 중괄호 한 개, 뷰 페이지는 두 개
    public String edit(@PathVariable Long id, Model model){   //메서드 생성 및 뷰 페이지 설정
        //수정항 데이터 가져오기
        Article articleEntity = articleRepository.findById(id).orElse(null);
        //모델에 데이터 등록하기
        model.addAttribute("article", articleEntity);
        //뷰 페이지 설정하기
        return "articles/edit";
    }

    @PostMapping("/articles/update")
    public String update(ArticleForm form){ //매개변수로 DTO 받아 오기
        log.info(form.toString());
        //DTO를 엔티티로 변환하기
        Article articleEntity = form.toEntity(); //DTO를 엔티티로 변환하기
        log.info(articleEntity.toString()); //엔티티로 잘 변환했는지 로그 찍기
        //엔티티를 DB에 저장하기
        //DB에서 기존 데이터 가져오기
        Article target = articleRepository.findById(articleEntity.getId()).orElse(null);
        //기존 데이터 값을 갱신하기
        if(target != null)
            articleRepository.save(articleEntity);
        //수정 결과 페이지로 리다이렉트하기
        return "redirect:/articles/" + articleEntity.getId();
    }

    @GetMapping("/articles/{id}/delete")
    public String delete(@PathVariable Long id, RedirectAttributes rttr){
        log.info("request delete");
        //삭제할 대상 가져오기
        Article target = articleRepository.findById(id).orElse(null);
        log.info(target.toString());
        //대상 엔티티 삭제하기
        if(target != null) {
            articleRepository.delete(target);
            rttr.addFlashAttribute("msg", "Delete!");
        }
        //결과 페이지로 리다이렉트하기
        return "redirect:/articles";
    }
}

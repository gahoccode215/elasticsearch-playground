package com.gahoccode.playground;

import com.fasterxml.jackson.core.type.TypeReference;
import com.gahoccode.playground.AbstractTest;

import com.gahoccode.playground.entity.Article;
import com.gahoccode.playground.repository.ArticleRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class QueryAnnotationTest extends AbstractTest {

    @Autowired
    private ArticleRepository repository;

    @BeforeAll
    public void dataSetup(){
        var articles = this.readResource("articles.json", new TypeReference<List<Article>>() {
        });
        this.repository.saveAll(articles);
        Assertions.assertEquals(11, this.repository.count());
    }

    @Test
    public void searchArticles(){
        var searchHits = this.repository.search("spring seasen"); //intentionally misspelled
        searchHits.forEach(this.print());
        Assertions.assertEquals(4, searchHits.getTotalHits());
    }

}
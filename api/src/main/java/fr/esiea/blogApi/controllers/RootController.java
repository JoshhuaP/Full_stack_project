package fr.esiea.blogApi.controllers;

import fr.esiea.blogApi.services.ArticleService;
import fr.esiea.blogApi.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RootController {

    @Autowired
    private ArticleService articleService;

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/")
    public String getRoot() {
        return "Blog API";
    }

}

package fr.esiea.blogApi.controllers;

import fr.esiea.blogApi.models.Article;
import fr.esiea.blogApi.models.Category;
import fr.esiea.blogApi.services.ArticleService;
import fr.esiea.blogApi.services.CategoryService;
import fr.esiea.blogApi.services.errors.NotFoundError;
import fr.esiea.blogApi.services.errors.PresentIdError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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

    @PostMapping("/populate")
    public String populate() throws NotFoundError, PresentIdError {
        // test dataset
        this.categoryService.insertCategory(new Category().setName("Informatique"));
        this.categoryService.insertCategory(new Category().setName("Cuisine"));
        this.articleService.insertArticle(new Article().setAuthor("Hydro").setCategory(this.categoryService.getCategory(1)).setContent("coucou!"));
        return "Populated!";
    }

}

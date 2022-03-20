package fr.esiea.blogApi.controllers;

import fr.esiea.blogApi.models.Article;
import fr.esiea.blogApi.models.Category;
import fr.esiea.blogApi.services.ArticleService;
import fr.esiea.blogApi.services.CategoryService;
import fr.esiea.blogApi.services.errors.NotFoundError;
import fr.esiea.blogApi.services.errors.PresentIdError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/articles")
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    @Autowired
    private CategoryService categoryService;

    @GetMapping("")
    public Iterable<Article> getArticles() {
        return articleService.getArticles();
    }

    @GetMapping("/{articleId}")
    public ResponseEntity getArticle(@PathVariable("articleId") final long id) {
        try {
            final Article article = articleService.getArticle(id);
            return new ResponseEntity<Article>(article, HttpStatus.OK);
        } catch (final NotFoundError e) {
            return new ResponseEntity<String>("Article not found", HttpStatus.NOT_FOUND);
        }
    }

    private void resolveCategory(final Article article) throws NotFoundError {
        Category category = article.getCategory();
        if (category != null) {
            final long categoryId = category.getId();
            category = categoryService.getCategory(categoryId);
            article.setCategory(category);
        }
    }

    @PostMapping("")
    public ResponseEntity postArticle(@RequestBody final Article article) {
        try {
            try {
                resolveCategory(article);
            } catch (final NotFoundError e) {
                return new ResponseEntity<String>("Category not found", HttpStatus.NOT_FOUND);
            }
            final Article newArticle = articleService.insertArticle(article);
            return new ResponseEntity<Article>(newArticle, HttpStatus.OK);
        } catch (final PresentIdError e) {
            return new ResponseEntity<String>("Id must not be set", HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("")
    public ResponseEntity putArticle(@RequestBody final Article article) {
        try {
            try {
                resolveCategory(article);
            } catch (final NotFoundError e) {
                return new ResponseEntity<String>("Category not found", HttpStatus.NOT_FOUND);
            }
            final Article updatedArticle = articleService.updateArticle(article);
            return new ResponseEntity<Article>(updatedArticle, HttpStatus.OK);
        } catch (final NotFoundError e) {
            return new ResponseEntity<String>("Article not found", HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{articleId}")
    public ResponseEntity deleteArticle(@PathVariable("articleId") final long id) {
        try {
            articleService.deleteArticle(id);
            return new ResponseEntity<Article>(HttpStatus.OK);
        } catch (final NotFoundError e) {
            return new ResponseEntity<String>("Article not found", HttpStatus.NOT_FOUND);
        }
    }

}

package fr.esiea.blogApi.controllers;

import fr.esiea.blogApi.models.Article;
import fr.esiea.blogApi.services.ArticleService;
import fr.esiea.blogApi.services.errors.CategoryNotFoundError;
import fr.esiea.blogApi.services.errors.NotFoundError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/articles")
@CrossOrigin(origins="*")
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    @GetMapping("")
    public Iterable<Article> getArticles() {
        return articleService.getArticles();
    }

    @GetMapping("/{articleId}")
    public ResponseEntity getArticle(@PathVariable("articleId") final long articleId) {
        try {
            final Article article = articleService.getArticle(articleId);
            return new ResponseEntity<Article>(article, HttpStatus.OK);
        } catch (final NotFoundError e) {
            return new ResponseEntity<String>("Article not found", HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("")
    public ResponseEntity postArticle(@RequestBody final ArticleService.InsertArticle body) {
        try {
            final Article newArticle = articleService.insertArticle(body);
            return new ResponseEntity<Article>(newArticle, HttpStatus.OK);
        } catch (final CategoryNotFoundError e) {
            return new ResponseEntity<String>("Category not found", HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{articleId}")
    public ResponseEntity putArticle(@PathVariable("articleId") final long articleId, @RequestBody final ArticleService.UpdateArticle body) {
        try {
            final Article updatedArticle = articleService.updateArticle(articleId, body);
            return new ResponseEntity<Article>(updatedArticle, HttpStatus.OK);
        } catch (final NotFoundError e) {
            return new ResponseEntity<String>("Article not found", HttpStatus.NOT_FOUND);
        } catch (final CategoryNotFoundError e) {
            return new ResponseEntity<String>("Category not found", HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{articleId}")
    public ResponseEntity deleteArticle(@PathVariable("articleId") final long articleId) {
        try {
            articleService.deleteArticle(articleId);
            return new ResponseEntity<Article>(HttpStatus.OK);
        } catch (final NotFoundError e) {
            return new ResponseEntity<String>("Article not found", HttpStatus.NOT_FOUND);
        }
    }

}

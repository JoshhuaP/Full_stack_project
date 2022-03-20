package fr.esiea.blogApi.services;

import fr.esiea.blogApi.models.Article;
import fr.esiea.blogApi.models.Category;
import fr.esiea.blogApi.repositories.ArticleRepository;
import fr.esiea.blogApi.services.errors.CategoryNotFoundError;
import fr.esiea.blogApi.services.errors.NotFoundError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ArticleService {

    @Autowired
    private ArticleRepository articleRepository;

    @Autowired
    private CategoryService categoryService;

    public Iterable<Article> getArticles() {
        return articleRepository.findAll();
    }

    public Article getArticle(final long id) throws NotFoundError {
        final Optional<Article> article = articleRepository.findById(id);
        if (article.isEmpty()) {
            throw new NotFoundError();
        }
        return article.get();
    }

    public static class InsertArticle {
        public Long categoryId;
        public String author;
        public String content;
    }

    public Article insertArticle(final InsertArticle insertArticle) throws CategoryNotFoundError {
        final Article article = new Article();
        if (insertArticle.categoryId != null) {
            try {
                final Category category = categoryService.getCategory(insertArticle.categoryId);
                article.setCategory(category);
            } catch (final NotFoundError e) {
                throw new CategoryNotFoundError();
            }
        } else {
            article.setCategory(null);
        }
        article.setAuthor(insertArticle.author);
        article.setContent(insertArticle.content);
        return articleRepository.save(article);
    }

    public static class UpdateArticle {
        public Long categoryId;
        public String author;
        public String content;
    }

    public Article updateArticle(final long articleId, final UpdateArticle updateArticle) throws NotFoundError, CategoryNotFoundError {
        final Article article = getArticle(articleId);
        if (updateArticle.categoryId != null) {
            try {
                final Category category = categoryService.getCategory(updateArticle.categoryId);
                article.setCategory(category);
            } catch (final NotFoundError e) {
                throw new CategoryNotFoundError();
            }
        } else {
            article.setCategory(null);
        }
        article.setAuthor(updateArticle.author);
        article.setContent(updateArticle.content);
        return articleRepository.save(article);
    }

    public void deleteArticle(final long id) throws NotFoundError {
        try {
            articleRepository.deleteById(id);
        } catch (final EmptyResultDataAccessException e) {
            throw new NotFoundError();
        }
    }
}

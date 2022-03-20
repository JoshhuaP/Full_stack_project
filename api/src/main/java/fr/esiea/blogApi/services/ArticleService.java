package fr.esiea.blogApi.services;

import fr.esiea.blogApi.models.Article;
import fr.esiea.blogApi.repositories.ArticleRepository;
import fr.esiea.blogApi.services.errors.NotFoundError;
import fr.esiea.blogApi.services.errors.PresentIdError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ArticleService {

    @Autowired
    private ArticleRepository articleRepository;

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

    public Article insertArticle(final Article article) throws PresentIdError {
        if (article.getId() != null) {
            throw new PresentIdError();
        }
        return articleRepository.save(article);
    }

    public Article updateArticle(final Article article) throws NotFoundError {
        if (article.getId() == null) {
            throw new NotFoundError();
        }
        try {
            return articleRepository.save(article);
        } catch (final EmptyResultDataAccessException e) {
            throw new NotFoundError();
        }
    }

    public void deleteArticle(final long id) throws NotFoundError {
        try {
            articleRepository.deleteById(id);
        } catch (final EmptyResultDataAccessException e) {
            throw new NotFoundError();
        }
    }
}

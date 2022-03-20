package fr.esiea.blogApi.services;

import fr.esiea.blogApi.models.Category;
import fr.esiea.blogApi.repositories.CategoryRepository;
import fr.esiea.blogApi.services.errors.NotFoundError;
import fr.esiea.blogApi.services.errors.PresentIdError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public Iterable<Category> getCategories() {
        return categoryRepository.findAll();
    }

    public Category getCategory(final long id) throws NotFoundError {
        final Optional<Category> category = categoryRepository.findById(id);
        if (category.isEmpty()) {
            throw new NotFoundError();
        }
        return category.get();
    }

    public Category insertCategory(final Category category) throws PresentIdError {
        if (category.getId() != null) {
            throw new PresentIdError();
        }
        return categoryRepository.save(category);
    }

    public Category updateCategory(final Category category) throws NotFoundError {
        if (category.getId() == null) {
            throw new NotFoundError();
        }
        try {
            return categoryRepository.save(category);
        } catch (final EmptyResultDataAccessException e) {
            throw new NotFoundError();
        }
    }

    public void deleteCategory(final long id) throws NotFoundError {
        try {
            categoryRepository.deleteById(id);
        } catch (final EmptyResultDataAccessException e) {
            throw new NotFoundError();
        }
    }
}

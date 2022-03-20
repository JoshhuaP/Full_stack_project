package fr.esiea.blogApi.services;

import fr.esiea.blogApi.models.Category;
import fr.esiea.blogApi.repositories.CategoryRepository;
import fr.esiea.blogApi.services.errors.NotFoundError;
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

    public static class InsertCategory {
        public String name;
    }

    public Category insertCategory(final InsertCategory insertCategory) {
        final Category category = new Category();
        category.setName(insertCategory.name);
        return categoryRepository.save(category);
    }

    public static class UpdateCategory {
        public String name;
    }

    public Category updateCategory(final long categoryId, final UpdateCategory updateCategory) throws NotFoundError {
        final Category category = getCategory(categoryId);
        category.setName(updateCategory.name);
        return categoryRepository.save(category);
    }

    public void deleteCategory(final long categoryId) throws NotFoundError {
        try {
            categoryRepository.deleteById(categoryId);
        } catch (final EmptyResultDataAccessException e) {
            throw new NotFoundError();
        }
    }
}

package fr.esiea.blogApi.controllers;

import fr.esiea.blogApi.models.Category;
import fr.esiea.blogApi.services.CategoryService;
import fr.esiea.blogApi.services.errors.NotFoundError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping({ "", "/" })
    public Iterable<Category> getCategories() {
        return categoryService.getCategories();
    }

    @GetMapping("/{categoryId}")
    public ResponseEntity<Category> getCategory(@PathVariable("categoryId") final long id) {
        try {
            final Category category = categoryService.getCategory(id);
            return new ResponseEntity<Category>(category, HttpStatus.OK);
        } catch (final NotFoundError e) {
            return new ResponseEntity<Category>(HttpStatus.NOT_FOUND);
        }
    }

}

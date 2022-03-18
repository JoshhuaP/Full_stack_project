package fr.esiea.blogApi.controllers;

import fr.esiea.blogApi.models.Category;
import fr.esiea.blogApi.services.CategoryService;
import fr.esiea.blogApi.services.errors.NotFoundError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping("")
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

    @PostMapping("")
    public ResponseEntity<Category> postCategory(@RequestBody final Category category) {
        try {
            Category newCategory = categoryService.insertCategory(category);
            return new ResponseEntity<Category>(newCategory, HttpStatus.OK);
        } catch (final IllegalArgumentException e) {
            return new ResponseEntity<Category>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("")
    public ResponseEntity<Category> putCategory(@RequestBody final Category category) {
        try {
            final Category updatedCategory = categoryService.updateCategory(category);
            return new ResponseEntity<Category>(updatedCategory, HttpStatus.OK);
        } catch (final NotFoundError e) {
            return new ResponseEntity<Category>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{categoryId}")
    public ResponseEntity<Category> deleteCategory(@PathVariable("categoryId") final long id) {
        try {
            categoryService.deleteCategory(id);
            return new ResponseEntity<Category>(HttpStatus.OK);
        } catch (final NotFoundError e) {
            return new ResponseEntity<Category>(HttpStatus.NOT_FOUND);
        }
    }

}
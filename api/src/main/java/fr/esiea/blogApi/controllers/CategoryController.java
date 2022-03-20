package fr.esiea.blogApi.controllers;

import fr.esiea.blogApi.models.Article;
import fr.esiea.blogApi.models.Category;
import fr.esiea.blogApi.services.CategoryService;
import fr.esiea.blogApi.services.errors.NotFoundError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categories")
@CrossOrigin(origins="*")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping("")
    public Iterable<Category> getCategories() {
        return categoryService.getCategories();
    }

    @GetMapping("/{categoryId}")
    public ResponseEntity getCategory(@PathVariable("categoryId") final long categoryId) {
        try {
            final Category category = categoryService.getCategory(categoryId);
            return new ResponseEntity<Category>(category, HttpStatus.OK);
        } catch (final NotFoundError e) {
            return new ResponseEntity<String>("Category not found", HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("")
    public ResponseEntity postCategory(@RequestBody final CategoryService.InsertCategory body) {
        Category newCategory = categoryService.insertCategory(body);
        return new ResponseEntity<Category>(newCategory, HttpStatus.OK);
    }

    @PutMapping("/{categoryId}")
    public ResponseEntity putCategory(@PathVariable("categoryId") final long categoryId, @RequestBody final CategoryService.UpdateCategory body) {
        try {
            final Category updatedCategory = categoryService.updateCategory(categoryId, body);
            return new ResponseEntity<Category>(updatedCategory, HttpStatus.OK);
        } catch (final NotFoundError e) {
            return new ResponseEntity<String>("Category not found", HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{categoryId}")
    public ResponseEntity deleteCategory(@PathVariable("categoryId") final long categoryId) {
        try {
            categoryService.deleteCategory(categoryId);
            return new ResponseEntity<Category>(HttpStatus.OK);
        } catch (final NotFoundError e) {
            return new ResponseEntity<String>("Category not found", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{categoryId}/articles")
    public ResponseEntity getCategoryArticles(@PathVariable("categoryId") final long categoryId) {
        try {
            final Category category = categoryService.getCategory(categoryId);
            return new ResponseEntity<List<Article>>(category.getArticles(), HttpStatus.OK);
        } catch (final NotFoundError e) {
            return new ResponseEntity<String>("Category not found", HttpStatus.NOT_FOUND);
        }
    }

}

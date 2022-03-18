package fr.esiea.blogApi.controllers;

import fr.esiea.blogApi.models.Category;
import fr.esiea.blogApi.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
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

}

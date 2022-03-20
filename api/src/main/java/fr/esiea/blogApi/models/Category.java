package fr.esiea.blogApi.models;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="categories")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public Long getId() {
        return this.id;
    }

    private String name;

    public String getName() {
        return this.name;
    }

    public Category setName(final String newName) {
        this.name = newName;

        // return self for chaining
        return this;
    }


    @OneToMany(mappedBy="category")
    @JsonBackReference
    private List<Article> articles;

    public List<Article> getArticles() {
        return articles;
    }

    public Category() {
    }
}

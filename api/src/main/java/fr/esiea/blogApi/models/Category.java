package fr.esiea.blogApi.models;

import javax.persistence.*;

@Entity
@Table(name="categories")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="category_id")
    private Long id;

    public Long getId() {
        return this.id;
    }

    @Column(name="category_name")
    private String name;

    public String getName() {
        return this.name;
    }

    public Category setName(final String newName) {
        this.name = newName;

        // return self for chaining
        return this;
    }

    public Category() {
    }
}

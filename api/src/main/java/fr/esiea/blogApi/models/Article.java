package fr.esiea.blogApi.models;

import javax.persistence.*;

@Entity
@Table(name="articles")
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="article_id")
    private Long id;

    public Long getId() {
        return this.id;
    }

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="category_id")
    private Category category;

    public Category getCategory() {
        return this.category;
    }

    public Article setCategory(final Category newCategory) {
        this.category = newCategory;

        // return self for chaining
        return this;
    }

    @Column(name="article_author")
    private String author;

    public String getAuthor() {
        return this.author;
    }

    public Article setAuthor(final String newAuthor) {
        this.author = newAuthor;

        // return self for chaining
        return this;
    }

    @Column(name="article_content")
    private String content;

    public String getContent() {
        return this.content;
    }

    public Article setContent(final String newContent) {
        this.content = newContent;

        // return self for chaining
        return this;
    }

    public Article() {
    }
}

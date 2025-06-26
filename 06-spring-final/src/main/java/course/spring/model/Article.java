package course.spring.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Objects;
import java.util.Set;
import java.util.StringJoiner;

@Entity
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String content;
    @ManyToOne
    @JsonIgnore
    private User author;
    private LocalDateTime publicationDate =  LocalDateTime.now();
    @ElementCollection(fetch = FetchType.EAGER)
    private Set<String> tags = Collections.emptySet();
    @ManyToMany
    private Set<Category> categories = Collections.emptySet() ;

    public Article() {
    }

    public Article(Long id) {
        this.id = id;
    }

    public Article(String title, String content, User author) {
        this.title = title;
        this.content = content;
        this.author = author;
    }

    public Article(String title, String content, Set<String> tags) {
        this.title = title;
        this.content = content;
        this.tags = tags;
    }

    public Article(String title, String content, User author, Set<String> tags, Set<Category> categories) {
        this.title = title;
        this.content = content;
        this.author = author;
        this.tags = tags;
        this.categories = categories;
    }

    public Article(String title, String content, User author, LocalDateTime publicationDate, Set<String> tags, Set<Category> categories) {
        this.title = title;
        this.content = content;
        this.author = author;
        this.publicationDate = publicationDate;
        this.tags = tags;
        this.categories = categories;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public Set<String> getTags() {
        return tags;
    }

    public void setTags(Set<String> tags) {
        this.tags = tags;
    }

    public Set<Category> getCategories() {
        return categories;
    }

    public void setCategories(Set<Category> categories) {
        this.categories = categories;
    }

    public LocalDateTime getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(LocalDateTime publicationDate) {
        this.publicationDate = publicationDate;
    }

    @Override
    public final boolean equals(Object o) {
        if (!(o instanceof Article article)) return false;
        return Objects.equals(id, article.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Article{");
        sb.append("id=").append(getId());
        sb.append(", title='").append(getTitle()).append('\'');
        sb.append(", content='").append(getContent()).append('\'');
        sb.append(", author=").append(getAuthor());
        sb.append(", publicationDate=").append(getPublicationDate());
        sb.append(", tags=").append(getTags());
        sb.append(", categories=").append(getCategories());
        sb.append('}');
        return sb.toString();
    }
}

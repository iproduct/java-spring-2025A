package course.spring.domain;

import course.spring.model.Article;

import java.util.List;
import java.util.Optional;

public interface ArticleService {
    List<Article> getAllArticles();
    Article getArticleById(Long id);
    Article getArticleByTile(String title);
    Article addArticle(Article article);
    Article updateArticle(Article article);
    Article deleteArticleById(Long id);
    long getCount();
}

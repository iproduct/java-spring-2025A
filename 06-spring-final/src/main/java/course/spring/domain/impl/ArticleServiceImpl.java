package course.spring.domain.impl;

import course.spring.dao.ArticleRepository;
import course.spring.domain.ArticleService;
import course.spring.model.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleServiceImpl  implements ArticleService {
    private ArticleRepository articleRepository;

    @Autowired
    public ArticleServiceImpl(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    @Override
    public List<Article> getAllArticles() {
        return List.of();
    }

    @Override
    public Article getArticleById(Long id) {
        return null;
    }

    @Override
    public Article getArticleByTile(String title) {
        return null;
    }

    @Override
    public Article addArticle(Article article) {
        return null;
    }

    @Override
    public Article updateArticle(Article article) {
        return null;
    }

    @Override
    public Article deleteArticleById(Long id) {
        return null;
    }

    @Override
    public long getCount() {
        return 0;
    }
}

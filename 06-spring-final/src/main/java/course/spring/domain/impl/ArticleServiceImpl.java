package course.spring.domain.impl;

import course.spring.dao.ArticleRepository;
import course.spring.dao.CategoryRepository;
import course.spring.dao.UserRepository;
import course.spring.domain.ArticleService;
import course.spring.exception.InvalidEntityDataException;
import course.spring.exception.NonexistingEntityException;
import course.spring.model.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ArticleServiceImpl implements ArticleService {
    private ArticleRepository articleRepository;
    private CategoryRepository categoryRepository;
    private UserRepository userRepository;

    @Autowired
    public ArticleServiceImpl(ArticleRepository articleRepository, CategoryRepository categoryRepository, UserRepository userRepository) {
        this.articleRepository = articleRepository;
        this.categoryRepository = categoryRepository;
        this.userRepository = userRepository;
    }




    @Override
    @Transactional(readOnly = true)
    public List<Article> getAllArticles() {
        return articleRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Article getArticleById(Long id) {
        return articleRepository.findById(id).orElseThrow(
                () -> new NonexistingEntityException(
                        String.format("Article with ID='%d' not found.", id)
                ));
    }

    @Override
    public Article getArticleByTile(String title) {
        return null;
    }

    @Override
    public Article addArticle(Article article) {
        var categories = article.getCategories();
        categories.stream().allMatch(
                cat ->cat != null && cat.getId() != null && categoryRepository.findById(cat.getId()).isPresent());
        if(article.getAuthor() == null || article.getAuthor().getId() == null) {
            throw new InvalidEntityDataException(String.format("Author & AuthorID can not be null."));
        }
        var author = userRepository.findById(article.getAuthor().getId()).orElseThrow(
                () -> new InvalidEntityDataException(
                        String.format("Author with ID='%d' not found.", article.getAuthor().getId())
                )
        );
        article.setAuthor(author);
        author.getArticles().add(article);
        return articleRepository.save(article);
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
        return articleRepository.count();
    }
}

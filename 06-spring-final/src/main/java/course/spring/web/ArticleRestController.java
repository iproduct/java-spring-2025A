package course.spring.web;

import course.spring.domain.ArticleService;
import course.spring.exception.InvalidEntityDataException;
import course.spring.model.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/api/articles")
public class ArticleRestController {
    @Autowired
    private ArticleService articleService;

    @GetMapping
    public List<Article> allArticles(){
        return articleService.getAllArticles();
    }

    @GetMapping("{id:\\d+}")
    public Article getArticleById(@PathVariable("id") Long id) {
        return articleService.getArticleById(id);
    }

    @GetMapping("count")
    public long getArticlesCount() {
        return articleService.getCount();
    }

    @GetMapping("/articlename/{name}")
    public List<Article> getArticleByName(@PathVariable("name") String name) {
        return articleService.getArticlesByTitle(name);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Article> addArticle(@RequestBody Article article) {
        var newArticle = articleService.addArticle(article);
        return ResponseEntity.created(
                ServletUriComponentsBuilder.fromCurrentRequest()
                        .pathSegment("{id}")
                        .buildAndExpand(newArticle.getId()).toUri()
        ).body(newArticle);
    }

    @PutMapping("{id}")
    public Article updateArticle(@PathVariable("id") Long id, @RequestBody Article article) {
        if(!id.equals(article.getId())) {
            throw new InvalidEntityDataException(
                    String.format("Non-matching IDs in path '%s' and in request body '%s'.", id, article.getId())
            );
        }
        return articleService.updateArticle(article);
    }

    @DeleteMapping("{id}")
    public Article deleteArticle(@PathVariable("id") Long id) {
        return articleService.deleteArticleById(id);
    }


}

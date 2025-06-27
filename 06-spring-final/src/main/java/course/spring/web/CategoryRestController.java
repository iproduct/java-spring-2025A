package course.spring.web;

import course.spring.domain.CategoryService;
import course.spring.exception.InvalidEntityDataException;
import course.spring.model.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/api/categorys")
public class CategoryRestController {
    @Autowired
    private CategoryService categoryService;

    @GetMapping
    public List<Category> allCategories(){
        return categoryService.getAllCategories();
    }

    @GetMapping("{id:\\d+}")
    public Category getCategoryById(@PathVariable("id") Long id) {
        return categoryService.getCategoryById(id);
    }

    @GetMapping("count")
    public long getCategorysCount() {
        return categoryService.getCount();
    }

    @GetMapping("/categoryname/{title}")
    public List<Category> getCategoryByName(@PathVariable("title") String title) {
        return categoryService.getCategoryByName(title);
    }



    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Category> addCategory(@RequestBody Category category) {
        var newCategory = categoryService.addCategory(category);
        return ResponseEntity.created(
                ServletUriComponentsBuilder.fromCurrentRequest()
                        .pathSegment("{id}")
                        .buildAndExpand(newCategory.getId()).toUri()
        ).body(newCategory);
    }

    @PutMapping("{id}")
    public Category updateCategory(@PathVariable("id") Long id, @RequestBody Category category) {
        if(!id.equals(category.getId())) {
            throw new InvalidEntityDataException(
                    String.format("Non-matching IDs in path '%s' and in request body '%s'.", id, category.getId())
            );
        }
        return categoryService.updateCategory(category);
    }

    @DeleteMapping("{id}")
    public Category deleteCategory(@PathVariable("id") Long id) {
        return categoryService.deleteCategoryById(id);
    }


}

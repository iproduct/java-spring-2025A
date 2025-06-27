package course.spring.domain.impl;

import course.spring.dao.CategoryRepository;
import course.spring.domain.CategoryService;
import course.spring.exception.NonexistingEntityException;
import course.spring.model.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {
    private CategoryRepository categoryRepository;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public Category getCategoryById(Long id) {
        return categoryRepository.findById(id).orElseThrow(
                () -> new NonexistingEntityException(
                        String.format("Category with ID='%d' not found.", id)
                ));
    }

    @Override
    public List<Category> getCategoryByName(String name) {
        return categoryRepository.findByNameContaining(name);
    }

    @Override
    public Category addCategory(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public Category updateCategory(Category category) {
        getCategoryById(category.getId());
        return categoryRepository.save(category);
    }

    @Override
    public Category deleteCategoryById(Long id) {
        return null;
    }

    @Override
    public long getCount() {
        return categoryRepository.count();
    }
}

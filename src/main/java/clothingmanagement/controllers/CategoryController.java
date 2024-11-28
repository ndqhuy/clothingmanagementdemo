package clothingmanagement.controllers;

import clothingmanagement.models.Category;
import clothingmanagement.services.CategoryService;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.*;

import java.util.Optional;

@Controller("/categories")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    // Lấy tất cả danh mục
    @Get
    public HttpResponse<Iterable<Category>> getAllCategories() {
        Iterable<Category> categories = categoryService.getAllCategories();
        return HttpResponse.ok(categories);
    }

    // Lấy danh mục theo ID
    @Get("/{id}")
    public HttpResponse<Category> getCategory(String id) {
        Optional<Category> category = categoryService.getCategoryById(id);
        return category.map(HttpResponse::ok).orElse(HttpResponse.notFound());
    }

    // Tạo danh mục mới
    @Post
    public HttpResponse<Category> createCategory(@Body Category category) {
        Category savedCategory = categoryService.saveCategory(category);
        return HttpResponse.created(savedCategory);
    }

    // Xóa danh mục theo ID
    @Delete("/{id}")
    public HttpResponse<?> deleteCategory(String id) {
        categoryService.deleteCategoryById(id);
        return HttpResponse.noContent();
    }
    // Cập nhật danh mục
    @Put("/{id}")
    public HttpResponse<Category> updateCategory(@PathVariable String id, @Body Category updatedCategory) {
        try {
            Category updated = categoryService.updateCategory(id, updatedCategory);
            return HttpResponse.ok(updated);
        } catch (RuntimeException e) {
            return HttpResponse.notFound();
        }
    }

}

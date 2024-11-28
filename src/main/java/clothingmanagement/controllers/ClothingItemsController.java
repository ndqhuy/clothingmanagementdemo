package clothingmanagement.controllers;

import clothingmanagement.models.Clothingitems;
import clothingmanagement.services.ClothingItemsService;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.*;

import java.util.Optional;

@Controller("/clothingitems")
public class ClothingItemsController {

    private final ClothingItemsService clothingItemsService;

    public ClothingItemsController(ClothingItemsService clothingItemsService) {
        this.clothingItemsService = clothingItemsService;
    }

    // Lấy tất cả sản phẩm
    @Get
    public HttpResponse<Iterable<Clothingitems>> getAllClothingItems() {
        Iterable<Clothingitems> clothingItems = clothingItemsService.getAllClothingItems();
        return HttpResponse.ok(clothingItems);
    }

    // Lấy sản phẩm theo ID
    @Get("/{id}")
    public HttpResponse<Clothingitems> getClothingItem(String id) {
        Optional<Clothingitems> clothingItem = clothingItemsService.getClothingItemById(id);
        return clothingItem.map(HttpResponse::ok).orElse(HttpResponse.notFound());
    }

    // Tạo sản phẩm mới
    @Post
    public HttpResponse<Clothingitems> createClothingItem(@Body Clothingitems clothingItem) {
        Clothingitems savedItem = clothingItemsService.saveClothingItem(clothingItem);
        return HttpResponse.created(savedItem);
    }

    // Xóa sản phẩm theo ID
    @Delete("/{id}")
    public HttpResponse<?> deleteClothingItem(String id) {
        clothingItemsService.deleteClothingItemById(id);
        return HttpResponse.noContent();
    }

    // Cập nhật sản phẩm
    @Put("/{id}")
    public HttpResponse<Clothingitems> updateClothingItem(@PathVariable String id, @Body Clothingitems updatedItem) {
        try {
            Clothingitems updated = clothingItemsService.updateClothingItem(id, updatedItem);
            return HttpResponse.ok(updated);
        } catch (RuntimeException e) {
            return HttpResponse.notFound();
        }
    }

}

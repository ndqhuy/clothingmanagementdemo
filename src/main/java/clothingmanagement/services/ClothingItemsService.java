package clothingmanagement.services;

import clothingmanagement.models.Clothingitems;
import clothingmanagement.repositories.ClothingItemsRepository;
import jakarta.inject.Singleton;

import java.util.Optional;

@Singleton
public class ClothingItemsService {

    private final ClothingItemsRepository clothingItemsRepository;

    public ClothingItemsService(ClothingItemsRepository clothingItemsRepository) {
        this.clothingItemsRepository = clothingItemsRepository;
    }

    // Lấy tất cả sản phẩm
    public Iterable<Clothingitems> getAllClothingItems() {
        return clothingItemsRepository.findAll();
    }

    // Lấy sản phẩm theo ID
    public Optional<Clothingitems> getClothingItemById(String id) {
        return clothingItemsRepository.findById(id);
    }

    // Tạo sản phẩm mới
    public Clothingitems saveClothingItem(Clothingitems clothingItem) {
        return clothingItemsRepository.save(clothingItem);
    }

    // Xóa sản phẩm theo ID
    public void deleteClothingItemById(String id) {
        clothingItemsRepository.deleteById(id);
    }

    // Cập nhật sản phẩm
public Clothingitems updateClothingItem(String id, Clothingitems updatedItem) {
    Optional<Clothingitems> existingItem = clothingItemsRepository.findById(id);

    if (existingItem.isPresent()) {
        Clothingitems itemToUpdate = existingItem.get();
        itemToUpdate.setName(updatedItem.getName());
        itemToUpdate.setSize(updatedItem.getSize());
        itemToUpdate.setColor(updatedItem.getColor());
        itemToUpdate.setPrice(updatedItem.getPrice());
        itemToUpdate.setCategoryId(updatedItem.getCategoryId());
        return clothingItemsRepository.update(itemToUpdate); // Lưu thay đổi
    } else {
        throw new RuntimeException("Clothing item with ID " + id + " not found");
    }
}

}

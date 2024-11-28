package clothingmanagement.repositories;

import clothingmanagement.models.Category;
import io.micronaut.data.annotation.Repository;
import io.micronaut.data.mongodb.annotation.MongoRepository;
import io.micronaut.data.repository.CrudRepository;

@Repository
@MongoRepository
public interface CategoryRepository extends CrudRepository<Category, String> {
}

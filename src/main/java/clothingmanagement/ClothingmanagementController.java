package clothingmanagement;

import io.micronaut.http.annotation.*;

@Controller("/clothingmanagement")
public class ClothingmanagementController {

    @Get(uri="/", produces="text/plain")
    public String index() {
        return "Example Response";
    }
}
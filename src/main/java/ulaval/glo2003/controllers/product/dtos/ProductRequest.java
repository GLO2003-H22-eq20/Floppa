package ulaval.glo2003.controllers.product.dtos;

import ulaval.glo2003.domain.ProductCategory;

import java.util.List;
import java.util.Optional;

public class ProductRequest {
    public String title;
    public String description;
    public Optional<Float> suggestedPrice;
    public List<ProductCategory> categories;
}

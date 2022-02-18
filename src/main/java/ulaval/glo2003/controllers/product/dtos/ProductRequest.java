package ulaval.glo2003.controllers.product.dtos;

import ulaval.glo2003.domain.ProductCategory;

import java.util.List;

public class ProductRequest {
    public String title;
    public String description;
    public Float suggestedPrice;
    public List<ProductCategory> categories;
}

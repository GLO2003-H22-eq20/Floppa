package ulaval.glo2003.product.domain;

public enum ProductCategory {
    SPORTS("sports"),
    ELECTRONICS("electronics"),
    APPAREL("apparel"),
    BEAUTY("beauty"),
    HOUSING("housing"),
    OTHER("other");

    public final String productCategoryName;

    ProductCategory(String productCategoryName) {
        this.productCategoryName = productCategoryName;
    }

    @Override
    public String toString() {
        return productCategoryName;
    }
}

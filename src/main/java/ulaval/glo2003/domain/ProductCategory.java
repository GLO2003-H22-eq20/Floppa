package ulaval.glo2003.domain;

public enum ProductCategory {
    sports("sports"),
    electronics("electronics"),
    apparel("apparel"),
    beauty("beauty"),
    housing("housing"),
    other("other");


    private final String category;

    ProductCategory(final String category){
        this.category = category;
    }

    public String getCategory() {return category;}
}

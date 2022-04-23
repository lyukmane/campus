package Product;

public class Product implements Comparable <Product> {
    String name;
    int price;
    String description;
    ProductCategories category;

    public Product(String name, int price, String description, ProductCategories category) {
        this.name = name;
        this.price = price;
        this.category = category;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public int getPrice(){
        return price;
    }

    public String getDescription(){
        return description;
    }

    public String getCategory(){
        return String.valueOf(category);
    }

    @Override
    public String toString() {
        return "Product{" +
                "name='" + name + '\'' +
                ", price=" + price +
                ", description='" + description + '\'' +
                ", category=" + category +
                '}';
    }

    @Override
    public int compareTo(Product o) {
        return this.price - o.price;
    }

}

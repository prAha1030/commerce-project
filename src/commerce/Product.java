package commerce;

public class Product {
    String name;
    String price;
    String description;
    int inventory;

    public Product(String name, String price, String description, int inventory) {
        this.name = name;
        this.price = price;
        this.description = description;
        this.inventory = inventory;
    }
}

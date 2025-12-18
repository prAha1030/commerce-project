package commerce;

public class Product {
    // 상품명, 가격, 설명, 재고
    private String name;
    private String price;
    private String description;
    private int inventory;

    public Product(String name, String price, String description, int inventory) {
        this.name = name;
        this.price = price;
        this.description = description;
        this.inventory = inventory;
    }
    // 게터로 필드 접근
    public String getName() {
        return name;
    }
    public String getPrice() {
        return price;
    }
    public String getDescription() {
        return description;
    }
    public int getInventory() {
        return inventory;
    }
}

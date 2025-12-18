package commerce;

public class Product {
    // 상품명, 가격, 설명, 재고
    private String name;
    private int price;
    private String description;
    private int inventory;
    private int inventoryToken;

    public Product(String name, int price, String description, int inventory) {
        this.name = name;
        this.price = price;
        this.description = description;
        this.inventory = inventory;
        this.inventoryToken = 0;
    }
    // 게터로 필드 접근
    public String getName() {
        return name;
    }
    public int getPrice() {
        return price;
    }
    public String getDescription() {
        return description;
    }
    public int getInventory() {
        return inventory;
    }
    public int getInventoryToken() {
        return inventoryToken;
    }
    // 가격 세번째마다 컴마 표시
    public String setPriceWon() {
        return String.format("%,d", price);
    }
    // 세터로 필드 수정
    public void setInventory(int inventory) {
        this.inventory = inventory;
    }
    public void setInventoryTokenZero() {
        this.inventoryToken = 0;
    }
}

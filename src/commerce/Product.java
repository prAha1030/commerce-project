package commerce;

public class Product {
    // 상품명, 가격, 설명, 재고, 장바구니에 담긴 수량
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
    // 세터로 필드 수정
    public void setPrice(int price) {
        this.price = price;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public void setInventory(int inventory) {
        this.inventory = inventory;
    }
    public void plusOneToken() {
        this.inventoryToken += 1;
    }
    public void setInventoryTokenZero() {
        this.inventoryToken = 0;
    }
    // 가격 세번째마다 컴마 표시
    public String setPriceWon() {
        return String.format("%,d", price);
    }
    // 상품명 | 가격 | 설명 | 재고 프리셋1
    public String presetNamPriDesInv() {
        return name + " | " + setPriceWon() + "원 | " + description + " | 재고: " + inventory + "개";
    }
    // 상품명 | 가격 | 설명 | 재고 프리셋1 -2
    public String presetNamTapPriDesInv() {
        return name + "\t| " + setPriceWon() + "원 | " + description + " | 재고: " + inventory + "개";
    }
    // 상품명 | 가격 | 설명 | 프리셋2
    public String presetNamPriDes() {
        return name + " | " + setPriceWon() + "원 | " + description;
    }
    // 상품명 | 가격 | 설명 | 프리셋2 -2
    public String presetNamTapPriDes() {
        return name + "\t| " + setPriceWon() + "원 | " + description;
    }
}

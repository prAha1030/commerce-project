package commerce;

import java.util.ArrayList;
import java.util.List;

public class Category {
    private String categoryName;

    public Category(String categoryName) {
        this.categoryName = categoryName;
    }

    // 상품 목록 관리
    private List<Product> products = new ArrayList<>();
    // 카테고리 조회
    public String getCategoryName() {
        return categoryName;
    }
    // 상품 추가
    public void addProduct(Product product) {
        products.add(product);
    }
    // 상품 조회
    public Product getProducts(int index) {
        return products.get(index);
    }
    // 상품 목록 길이
    public int getProductsCount() {
        return products.size();
    }
}
